package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.daoImpl.CashBookDaoImpl;
import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;
import pro.bit.bitproject.daoImpl.LendingSheduleDAOImpl;
import pro.bit.bitproject.daoImpl.PaymentResheduleDAOImpl;
import pro.bit.bitproject.domain.CashBook;

/**
 * Servlet implementation class CshbookCotroller
 */
@WebServlet("/CshbookCotroller")
public class CshbookCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CshbookCotroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		JSONObject json = new JSONObject();
		double paidAmount = 0.00;
		double billAmount = 0.00;
		String shedule_type = "NS";
		int nextDeliveryAfter = 14;
		int customerId = Integer.parseInt(request.getParameter("customer"));
		if (request.getParameter("billAmt") != null) {
			billAmount = Double.parseDouble(request.getParameter("billAmt"));
		}
		if (request.getParameter("payAmt") != null) {
			paidAmount = Double.parseDouble(request.getParameter("payAmt"));
		}
		String type = request.getParameter("paytype");
		LocalDateTime paiddate = LocalDateTime.now();
		LocalDateTime nextdue = paiddate.plusDays(nextDeliveryAfter).withHour(0).withSecond(0).withNano(0);
		String custcashbookid = getNic(customerId);
		//look
		int branchId = 0;
		int userId = 1;
		boolean sts = false;

		switch (method) {
		case "createCB":
			double totalArrears = getcurrenttotarr(custcashbookid);
			branchId = Integer.parseInt(request.getParameter("branch"));
			// update cash book table,
			if (type.equals("P")) {
				double todayarr = 0.00;
				double extrayment = 0.00;
				if (billAmount == 0.00) {
					todayarr = 0.00;
					extrayment = paidAmount;
				} else if (paidAmount > billAmount) {
					todayarr = 0.00;
					extrayment = paidAmount - billAmount;
				} else {
					todayarr = billAmount - paidAmount;
					extrayment = 0.00;
				}

				createCB(branchId, customerId, billAmount, paidAmount, paiddate, type, userId, custcashbookid,
						extrayment, shedule_type, response);
				savecurarr(customerId, todayarr, paiddate);
				try {
					sts = checkAvalablityofArr(custcashbookid);
					if (sts == true) {
						updateArrearsAmttogetTot(customerId, totalArrears, todayarr, extrayment, paiddate);
						updateCustomer(customerId);
					} else {
						saveArrearsAmttogetTot(customerId, todayarr, paiddate);
						updateCustomer(customerId);
					}
					shedule_type = "todoooo";
					calculateAndDeductArrearsFromExtraPay(custcashbookid, extrayment, totalArrears, customerId, shedule_type);

					json.put("nd", nextdue);
					json.put("todarr", todayarr);
					System.out.println("todayarr" + todayarr);
					json.put("extrapay", extrayment);

					json.put("success", "Cash Book Updated Successfully..!");
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (type.equals("I")){
				branchId = Integer.parseInt(request.getParameter("branch"));
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
					createCB (branchId,customerId,billAmount,paidAmount,paiddate,type,userId,custcashbookid,0.00,shedule_type,response);
					updateArrearsAmttogetTot(customerId,totalArrears,0.00,paidAmount ,paiddate);
					updateInsPay(custcashbookid,noofins);
					/*this assignments should happen after updating tables*/
					double remainpay = getcurrenttotarr(custcashbookid);
					paidmodescount = getNoOfPaidInstallments(custcashbookid);
					System.out.println(paidmodescount + "paidmodescount");
					remainmode = getmode(custcashbookid) - paidmodescount;
					if (remainmode == 1  && (getWalletValue(custcashbookid) == getModePayment(custcashbookid))) {
						json.put("WalletValue", getWalletValue(custcashbookid));
						System.out.println("Do you want to deduct last istallmet from wallet? JSPPP");
						
					}
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
				e1.printStackTrace();
			}
			
			break;
		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

	/*
	 * 
	 * Common methods
	 * 
	 */
	private String getNic(int cusId) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		return dao.getcuscashbookid(cusId);
	}

	/*
	 * 
	 * extra pay deduction procedure
	 * if customer has exceeds the credit limit he should schedule a pay plan
	 * when pay plan is added his extra payment save in wallet until it equals to one installment 
	 * if credit limit not reached extra pay will deduct from arrears on time 
	 * 
	 */
	private void calculateAndDeductArrearsFromExtraPay(String custcashbookid, double extraPay, double totalArrears, int customerId, String shedule_type) throws SQLException {
		int creditLimit = getCreditLimit (customerId);
		System.out.print("creditLimit....." + creditLimit);
		try{
			if (shedule_type.equals("S")) {
				updateExtraPayInWallet(custcashbookid, extraPay) ;
			} else {
				deductArrearsFromExtraPay(custcashbookid, extraPay, totalArrears) ;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private double deductArrearsFromExtraPay(String custcashbookid, double extraPay, double totalArrears) {
		CashBookDaoImpl cashBookDaoImpl = new CashBookDaoImpl();
		double deductedArrears = 0;
		LocalDateTime currentDate = LocalDateTime.now();
		extraPay = extraPay * (-1);
		try {
			cashBookDaoImpl.saveArrearsAmt (custcashbookid, currentDate, extraPay);
			deductedArrears = cashBookDaoImpl.deductTotalArr (custcashbookid, extraPay);
			System.out.print("deductedArrears ....." + deductedArrears);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deductedArrears;
	}


	private void updateExtraPayInWallet(String custcashbookid, double extraPay) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 
	 * get customer credit limit
	 * 
	 */

	private int getCreditLimit(int customerId) throws SQLException {
		CashBookDaoImpl cashBookDaoImpl = new CashBookDaoImpl();
		int custype = cashBookDaoImpl.getCusType(customerId);
		int creditLimit = cashBookDaoImpl.getCreditLimit(custype);
		return creditLimit;

	}

	/*
	 * all transactions are saved from this method
	 * 
	 */
	public void createCB(int branchId, int customerId, double billAmount, double paidAmount, LocalDateTime paiddate,
			String type, int userid, String custcashbookid, Double extrayment, String shedule_type,
			HttpServletResponse response) {
		CashBook cb = new CashBook();
		CashBookDaoImpl cbdao = new CashBookDaoImpl();
		try {
			cb.setBillAmount(billAmount);
			cb.setBranchId(branchId);
			cb.setCustomerId(customerId);
			cb.setPaiddate(paiddate);
			cb.setPaidAmount(paidAmount);
			cb.setCustcashbookId(getNic(customerId));
			cb.setPaytype(type);
			// todo
			cb.setUser(1);
			cb.setExtrapayment(extrayment);
			cb.setShedule_status(shedule_type);

			cbdao.createCasahbook(cb);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/*
	 * Daily arrears amounts are recored from this method data save in
	 * cash_arrears table
	 * 
	 */

	private void savecurarr(int customerId, double todayarr, LocalDateTime paiddate) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(customerId);
		dao.saveArrearsAmt(custcashbookid, paiddate, todayarr);
	}

	private boolean checkAvalablityofArr(String custcashbookid) throws Exception {
		CashBookDaoImpl cashDao = new CashBookDaoImpl();
		boolean sts = cashDao.checkAvalablityofArr(custcashbookid);
		return sts;
	}

	private double getcurrenttotarr(String customer_cash_book_Id) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		return dao.getcurrenttotarr(customer_cash_book_Id);

	}

	private void updateArrearsAmttogetTot(int customerId, double availabletotarrearsforthecus, double todayarr,
			Double extrayment, LocalDateTime paiddate) throws Exception {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(customerId);
		dao.updateArrearsAmttogetTot(custcashbookid, paiddate, availabletotarrearsforthecus, extrayment, todayarr);
	}

	private void updateCustomer(int custId) {
		CustomerRegistrationDAOImpl cusDAO = new CustomerRegistrationDAOImpl();
		double curArr = getcurrentarr(custId);
		String sts = null;
		/*
		 * "B" - Blacklisted , "A" - Active
		 * */
		if (curArr > 100000) {
			sts = "B";
		} else {
			sts = "A";
		}
		cusDAO.updateCustomer(custId, sts);
	}

	/* Save data to tot_arrears table */
	private void saveArrearsAmttogetTot(int customerId, double todayarr, LocalDateTime paiddate) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(customerId);
		dao.saveArrearsAmttogetTot(custcashbookid, paiddate, todayarr);
	}

	private double getcurrentarr(int cusId) {
		CashBookDaoImpl dao = new CashBookDaoImpl();
		String custcashbookid = getNic(cusId);
		return dao.getcurrentarr(custcashbookid);
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
	
	public int getNoOfPaidInstallments(String custcashbookid){
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		return cashdao.getNoOfPaidInstallments(custcashbookid);
	}
	
	public int getmode(String custcashbookid){
		CashBookDaoImpl cashdao = new CashBookDaoImpl();
		return cashdao.getmode(custcashbookid);
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

	/* get no of paid installments from lend_shedule table 
	 * if customer pays  multiple at once it is saved in table
	 * */
	private int getpaidscount(String custcashbookid) {
		LendingSheduleDAOImpl lendDao = new LendingSheduleDAOImpl();
		int paidcount = lendDao.getpaidscount(custcashbookid);
		return paidcount;
	}
	
	/*
	 * 
	 * get wallet amout
	 * */
	private double getWalletValue (String custcashbookid) {
		LendingSheduleDAOImpl lendDao = new LendingSheduleDAOImpl();
		try {
			return lendDao.getWalletAmout(custcashbookid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/*
	 * 
	 * get modepaymet (installment amount)
	 * */
	private double getModePayment (String custcashbookid) throws SQLException {
		CashBookDaoImpl cashBookDaoImpl = new CashBookDaoImpl();
		return cashBookDaoImpl.getmodepayment(custcashbookid);
	}

}
