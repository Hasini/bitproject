package pro.bit.bitproject.action;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.CashBookDaoImpl;
import pro.bit.bitproject.daoImpl.LendingSheuleDaoImpl;
import pro.bit.bitproject.domain.CashBook;
/**
 * Servlet implementation class CashBkController
 */
@WebServlet("/CashBkController")
public class CashBkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			
			if (type.equals("P")) {
				System.out.println("asasas");
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
					System.out.println(sts+"stsstsstsstsstssts");
					if (sts == true){
						updateArrearsAmttogetTot(customerId,availabletotarrearsforthecus,todayarr,extrayment,paiddate);
					}else{
						saveArrearsAmttogetTot(customerId,todayarr,paiddate);
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				
			 	createCB (branchId,customerId,billAmount,paidAmount,paiddate,type,userid,custcashbookid,extrayment,response);
			 	savecurarr(customerId,todayarr,paiddate);
				
				try {
					json.put("nd", nextdue);
					json.put("todarr", todayarr);
					json.put("extrapay", extrayment);
					
					json.put("success", "Cash Book Updated Successfully..!");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			} else {
				
				int noofins =0;
				int remainmode = 0;
				if (request.getParameter("inspay") != null){
					paidAmount= Double.parseDouble(request.getParameter("inspay"));
			 	}
				if (request.getParameter("noofins") != null){
					noofins= Integer.parseInt(request.getParameter("noofins"));
			 	}
				createCB (branchId,customerId,billAmount,paidAmount,paiddate,type,userid,custcashbookid,0.00,response);
				//todoooooooooooooooooooooooooo
				remainmode = getmode(custcashbookid)-noofins;
				try {
					updateInsPay(custcashbookid,noofins);
					json.put("re", remainmode);
					json.put("nd", nextdue);
					json.put("success", "Installment payment successfuly paid..!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		case "viewMP":
			
			Double mp = getModePayment(customerId);
			Double totarr = getcurrentarr(customerId);
			System.out.println(totarr+"tatatata");
			try {
				json.put("mp", mp);
				json.put("totarr", totarr);
			} catch (JSONException e) {
				e.printStackTrace();
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
		LendingSheuleDaoImpl lendDao = new LendingSheuleDaoImpl();
		int paidterms = getpaidscount(custcashbookid);
		int totnoofins = noofins+paidterms;
		lendDao.updateInsPay(custcashbookid,totnoofins);
		return totnoofins;
	}

	/* get no of paid installments from lend_shedule table 
	 * if customer pays  multiple at once it is saved in table
	 * */
	private int getpaidscount(String custcashbookid) {
		LendingSheuleDaoImpl lendDao = new LendingSheuleDaoImpl();
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
	/* all transactions are saved from this menthod
	 * */
	public void createCB(int branchId,int customerId,double billAmount,double paidAmount,LocalDateTime paiddate,String type,int userid,String custcashbookid,Double extrayment,HttpServletResponse response) {
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

	public double getModePayment(int cusId) {
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		String custcashbookid = getNic(cusId);
		return cashdao.getmodepayment (custcashbookid);
	}
	
	public int getmode(String custcashbookid){
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		return cashdao.getmode(custcashbookid);
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
}
