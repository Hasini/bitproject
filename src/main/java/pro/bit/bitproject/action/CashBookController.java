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

import pro.bit.bitproject.daoImpl.CashBookDAOImpl;
import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;
import pro.bit.bitproject.domain.CashBook;
import pro.bit.bitproject.domain.CustomerRegistration;


@WebServlet("/CashBookController")
public class CashBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CashBookController() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		JSONObject json = new JSONObject();
		int customerId = Integer.parseInt(request.getParameter("customer"));
		
		switch (method) {
		case "createCB":
			double billAmount =0.00;
		 	double inspay = 0.00;
		 	double paidAmount = 0.00;
		 	
		 	if (request.getParameter("mp") != null){
		 		inspay= Double.parseDouble(request.getParameter("mp"));
		 	}
		 	if (request.getParameter("billAmt") != null){
		 		billAmount = Double.parseDouble(request.getParameter("billAmt"));
		 	}
		 	if (request.getParameter("billAmt") != null){
		 		paidAmount = Double.parseDouble(request.getParameter("payAmt"));
		 	}
		 	int branchId = Integer.parseInt(request.getParameter("branch"));
		 	LocalDateTime paiddate = LocalDateTime.now();
			calTotArrearsAmt(customerId,billAmount,paidAmount,paiddate);
			createCB (branchId,customerId,billAmount,paidAmount,paiddate,inspay,response);
			try {
				json.put("success", "Cash Book Updated Successfully..!");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		case "viewMP":
			
			Double mp = getModePayment(customerId);
			Double ta = getcurrentarr(customerId);
			try {
				json.put("mp", mp);
				json.put("ta", ta);
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

	public void createCB(int branchId,int customerId,double billAmount,double paidAmount,LocalDateTime paiddate,double inspay, HttpServletResponse response) {
			CashBook cb = new CashBook();
			CashBookDAOImpl dao = new CashBookDAOImpl();
		try{
			  cb.setBillAmount(billAmount);
			  cb.setBranchId(branchId);
			  cb.setCustomerId(customerId);
			  cb.setPaiddate(paiddate);
			  cb.setPaidAmount(paidAmount);
			  cb.setCustcashbookId(getNic(customerId));
			  cb.setInstallmentpaymnet(inspay);
			  
			  dao.createCasahbook(cb);  
		  }catch(Exception e){
			 
			  e.printStackTrace();
		  }
	}
	
	/*Calculating arrears amount for particular customer 
	 * 
	 * function :add mode payment to day bill amount and deduct day payment
	 * 
	 * */
	public double calTotArrearsAmt(int customerId, double billAmount, double paidAmount, LocalDateTime paiddate) {
		CashBookDAOImpl dao = new CashBookDAOImpl();
		CustomerRegistrationDAOImpl cusDao = new CustomerRegistrationDAOImpl();
		CustomerRegistration cus = new CustomerRegistration();
		
		double currentarr = Math.ceil((billAmount + getcurrentarr(customerId)+getModePayment(customerId)) - paidAmount);
		String custcashbookid = getNic(customerId);
		Character sts = 'A';
		System.out.println("currentarr"+currentarr);
		if (currentarr> 100000){
			sts = 'B';
		}
		
		cus.setCusId(customerId);
		cus.setStatus(sts);
		dao.saveArrearsAmt(custcashbookid,paiddate,currentarr);
		cusDao.updateCusSts(cus);
		
		return currentarr;
	}
	

	public String getNic(int cusId) {
		CashBookDAOImpl dao = new CashBookDAOImpl();
		return dao.getcuscashbookid (cusId);
	}
	

	public double getModePayment(int cusId) {
		CashBookDAOImpl dao = new CashBookDAOImpl();
		String custcashbookid = getNic(cusId);
		return dao.getmodepayment (custcashbookid);
	}
	
	public double getcurrentarr(int cusId) {
		CashBookDAOImpl dao = new CashBookDAOImpl();
		String custcashbookid = getNic(cusId);
		return dao.getcurrentarr (custcashbookid);
	}

}
