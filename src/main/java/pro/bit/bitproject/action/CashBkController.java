package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.CashBookDaoImpl;
import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;
import pro.bit.bitproject.daoImpl.LendingSheduleDAOImpl;
import pro.bit.bitproject.daoImpl.PaymentResheduleDAOImpl;
import pro.bit.bitproject.domain.CashBook;
/**
 * Servlet implementation class CashBkController
 */
@WebServlet("/CashBkController")
public class CashBkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(CashBkController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CashBkController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		double paidAmount = 0.00;
		double billAmount = 0.00;
		String shedule_type = "NS";
		int customerId = Integer.parseInt(request.getParameter("customer"));
		String method = request.getParameter("method");
		
		if (request.getParameter("billAmt") != null){
	 		billAmount = Double.parseDouble(request.getParameter("billAmt"));
	 	}
	 	if (request.getParameter("payAmt") != null){
	 		paidAmount = Double.parseDouble(request.getParameter("payAmt"));
	 	}
	 	
		switch (method) {
		case "createCB":
			int userid = 1;
			String type = request.getParameter("paytype");
			LocalDateTime paiddate = LocalDateTime.now();
		 	LocalDateTime nextdue = paiddate.plusDays(14);
		 	String custcashbookid = getNic(customerId);
			int branchId = Integer.parseInt(request.getParameter("branch"));
			double availabletotarrearsforthecus=getcurrenttotarr(custcashbookid);
			
			//update cash book table,
			if (type.equals("P")) {
				double todayarr = 0.00;
			 	double extrayment = 0.00;
			 	if (billAmount == 0.00){
			 		todayarr = 0.00;
			 		extrayment = paidAmount;
			 	}else if (paidAmount>billAmount){
			 		todayarr = 0.00;
			 		extrayment = paidAmount-billAmount;
			 	}
			 	else{
			 		todayarr = billAmount-paidAmount;
			 		extrayment = 0.00;
			 	}
			 	try {
					boolean sts = checkAvalablityofArr(custcashbookid);
					if (sts == true){
						updateArrearsAmttogetTot(customerId,availabletotarrearsforthecus,todayarr,extrayment,paiddate);
						updateCustomer(customerId);
					}else{
						saveArrearsAmttogetTot(customerId,todayarr,paiddate);
						updateCustomer(customerId);
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				
			 	createCB (branchId,customerId,billAmount,paidAmount,paiddate,type,userid,custcashbookid,extrayment,shedule_type,response);
			 	savecurarr(customerId,todayarr,paiddate);
				
				try {
					json.put("nd", nextdue);
					json.put("todarr", todayarr);
					json.put("extrapay", extrayment);
					
					json.put("success", "Cash Book Updated Successfully..!");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			} else if (type.equals("I")){
				try {
					
					int noofins =0;
					int remainmode = 0;
					int paidmodescount = 0;
					if (request.getParameter("inspay") != null){
						paidAmount= Double.parseDouble(request.getParameter("inspay"));
				 	}
					if (request.getParameter("noofins") != null){
						noofins = Integer.parseInt(request.getParameter("noofins"));
						//noofins= getNoOfPaidInstallments(custcashbookid);
						System.out.println("noofins"+noofins);
				 	}
					createCB (branchId,customerId,billAmount,paidAmount,paiddate,type,userid,custcashbookid,0.00,shedule_type,response);
					updateArrearsAmttogetTot(customerId,availabletotarrearsforthecus,0.00,paidAmount ,paiddate);
					updateInsPay(custcashbookid,noofins);
					/*this assignments should happen after updating tables*/
					double remainpay = getcurrenttotarr(custcashbookid);
					paidmodescount = getNoOfPaidInstallments(custcashbookid);
					System.out.println(paidmodescount + "paidmodescount");
					remainmode = getmode(custcashbookid) - paidmodescount;
					json.put("re", remainmode);
					json.put("nd", nextdue);
					json.put("remainpay", remainpay);
					json.put("success", "Installment amount successfuly paid and arrears deducted ..!");
					updateCustomer(customerId);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		case "viewMP":
			Double mp;
			try {
				mp = getModePayment(customerId);
				Double totarr = getcurrentarr(customerId);
				json.put("mp", mp);
				json.put("totarr", totarr);
			} catch (SQLException | JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
			
		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	/* Update lend_shedule table with the payments*/
	private int updateInsPay(String custcashbookid, int noofins) throws Exception {
		LendingSheduleDAOImpl lendDao = new LendingSheduleDAOImpl();
		double totArr = getcurrenttotarr(custcashbookid);
		System.out.println(totArr+"total arrears from updating extra pay");
		int paidterms = getpaidscount(custcashbookid);
		int totnoofins = noofins+paidterms;
		String sts = null ;
		if (totArr == 0){
			sts = "NS";
		}else 
			sts = "S";
		lendDao.updateInsPay(custcashbookid,totnoofins,sts);
		return totnoofins;
	}

	/* get no of paid installments from lend_shedule table 
	 * if customer pays  multiple at once it is saved in table
	 * */
	private int getpaidscount(String custcashbookid) {
		LendingSheduleDAOImpl lendDao = new LendingSheduleDAOImpl();
		int paidcount = lendDao.getpaidscount(custcashbookid);
		return paidcount;
	}
	
	/* Daily arrears amounts are recored from this method 
	 * data save in cash_arrears table
	 * */
	
	private void savecurarr(int customerId, double todayarr, LocalDateTime paiddate) {
		System.out.println("savearr");
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(customerId);
		dao.saveArrearsAmt(custcashbookid,paiddate,todayarr);
	}

	/*Save data to tot_arrears table*/
	private void saveArrearsAmttogetTot (int customerId, double todayarr, LocalDateTime paiddate){
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(customerId);
		dao.saveArrearsAmttogetTot(custcashbookid, paiddate, todayarr);
	}
	
	private void updateArrearsAmttogetTot(int customerId, double availabletotarrearsforthecus, double todayarr,Double extrayment,LocalDateTime paiddate) throws Exception {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(customerId);
		dao.updateArrearsAmttogetTot(custcashbookid, paiddate,availabletotarrearsforthecus,extrayment,todayarr);
	}
	
	private double getcurrenttotarr(String customer_cash_book_Id) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		return dao.getcurrenttotarr(customer_cash_book_Id);
		
	}

	private boolean checkAvalablityofArr(String custcashbookid) throws Exception {
		CashBookDaoImpl cashDao = new CashBookDaoImpl();
		boolean sts = cashDao.checkAvalablityofArr(custcashbookid);
		return sts;
	}
	/* all transactions are saved from this method
	 * */
	public void createCB(int branchId,int customerId,double billAmount,double paidAmount,LocalDateTime paiddate,String type,int userid,String custcashbookid,Double extrayment,String shedule_type, HttpServletResponse response) {
		CashBook cb = new CashBook();
		CashBookDaoImpl cbdao = new CashBookDaoImpl();
	try{
		  cb.setBillAmount(billAmount);
		  cb.setBranchId(branchId);
		  cb.setCustomerId(customerId);
		  cb.setPaiddate(paiddate);
		  cb.setPaidAmount(paidAmount);
		  cb.setCustcashbookId(getNic(customerId));
		  cb.setPaytype(type);
		  cb.setUser(userid);
		  cb.setExtrapayment(extrayment);
		  cb.setShedule_status(shedule_type);
		  
		  cbdao.createCasahbook(cb);  
	  }catch(Exception e){
		 
		  e.printStackTrace();
	  }
	}
	
	public String getNic(int cusId) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		return dao.getcuscashbookid (cusId);
	}
	
	/* Sum of 
	 * */
	public double getcurrentarr(int cusId) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(cusId);
		return dao.getcurrentarr (custcashbookid);
	}

	public double getModePayment(int cusId) throws SQLException {
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		PaymentResheduleDAOImpl reSchDao = new PaymentResheduleDAOImpl();
		Double modePayment = 0.00;
		String custcashbookid = getNic(cusId);
		if (cashdao.getSchType(custcashbookid) .equals ("RS")){
			modePayment = reSchDao.getReschPaymentInstallment(custcashbookid);
		}else {
			modePayment = cashdao.getmodepayment (custcashbookid);
		}
		return modePayment;
	}
	
	public int getmode(String custcashbookid){
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		return cashdao.getmode(custcashbookid);
	}
	
	public String getSchType(String custcashbookid){
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		try {
			return cashdao.getSchType(custcashbookid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getNoOfPaidInstallments(String custcashbookid){
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		return cashdao.getNoOfPaidInstallments(custcashbookid);
	}

	public double calExtrapayment(int customerId, double billAmount,double paidAmount){
		double extrapyment = 0.00;
		if (billAmount == 0.00){
	 		extrapyment = paidAmount;
	 	}else if (paidAmount>billAmount){
	 		extrapyment = paidAmount-billAmount;
	 	}
	 	else{
	 		extrapyment = 0.00;
	 	}
		return extrapyment;
	}
	
	public void updateCustomer (int custId){
		CustomerRegistrationDAOImpl cusDAO = new CustomerRegistrationDAOImpl();
		double curArr =  getcurrentarr(custId);
		String sts = null;
		if (curArr > 100000){
			sts ="B";
		}else {
			sts="A";
		}
		cusDAO.updateCustomer(custId,sts);
	}
}
