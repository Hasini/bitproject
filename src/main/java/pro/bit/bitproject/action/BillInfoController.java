package pro.bit.bitproject.action;

import java.io.IOException;
import java.time.LocalDateTime;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.BillInfoDAOImpl;
import pro.bit.bitproject.daoImpl.DailyExpenseDAOImpl;
import pro.bit.bitproject.daoImpl.IncomeTypeDAOImpl;
import pro.bit.bitproject.domain.BillInfo;

/**
 * Servlet implementation class BillInfoController
 */
@WebServlet("/BillInfoController")
public class BillInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BillInfoController() {
        super();
       
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			JSONArray jsonArray = new JSONArray();
			JSONObject json = new JSONObject();
			String method = request.getParameter("method");
			
			switch (method) {
			case "viewUsers":
				jsonArray = viewUsers();
				try {
					json.put("userObj", jsonArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			case "viewExpenseTypes":
				jsonArray = viewExpenseType();
				try {
					json.put("expenseTypeObj", jsonArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			case "viewIncomeTypes":
				jsonArray = viewIncomeType();
				try {
					json.put("incomeTypeObj", jsonArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
				
	        case "createBill":
	        	
	        	try {
	        		createBill (request,response);
	        		json.put("suc", "Record Entered Successfully..!");
	        		System.out.println("Bill created");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;

			default:
				break;
			}
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		}


		public void createBill(HttpServletRequest request, HttpServletResponse response) {
			HttpSession httpSession = request.getSession();
			//String username = httpSession.getAttribute()
			BillInfo billInfo = new BillInfo();
			BillInfoDAOImpl bill = new BillInfoDAOImpl();
			
			/*****Date Convertion******/
			/*String submittedtime = request.getParameter("subtime");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime sdateTime = LocalDateTime.parse(submittedtime, format);
			
			String billdate = request.getParameter("billdate");
			DateTimeFormatter bformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime bdateTime = LocalDateTime.parse(billdate, bformat);*/
			//--------------------------------//
			int incomeType =Integer.parseInt(request.getParameter("incometypeId"));;
			int expenseType=0;
			int customerid=0;
			String billCode = request.getParameter("billcode");
			double billAmount = Double.parseDouble(request.getParameter("amount"));
			int submittedUserId = Integer.parseInt(request.getParameter("submitteduser"));
			//int enteredUserId = httpSession.getAttribute(username);
			//int enteredUserId = get user by session
			//LocalDateTime submitTime = sdateTime ;
			LocalDateTime enteredTime = LocalDateTime.now();
			//LocalDateTime billDate = bdateTime;
			//temp
			LocalDateTime submitTime = LocalDateTime.now();
			LocalDateTime billDate = LocalDateTime.now();
			
			if (request.getParameter("expensetypeid")!=null){
				expenseType = Integer.parseInt(request.getParameter("expensetypeid"));
			}
			
			if (request.getParameter("incometypeId")!=null){
				
				System.out.println(incomeType+"####");
			}
			
			if (request.getParameter("customer")!=null){
				customerid = Integer.parseInt(request.getParameter("customer"));
			}
		
			try{
				billInfo.setBillCode(billCode);
				billInfo.setBillAmount(billAmount);
				billInfo.setSubmitTime(submitTime);
				billInfo.setEnteredTime(enteredTime);
				billInfo.setSubmittedUserId(submittedUserId);
				billInfo.setBillDate(billDate);
				
				if (expenseType != 0){
					billInfo.setExpenseType(expenseType);
				}
				if (incomeType != 0){
					billInfo.setIncomeType(incomeType);
				}
				if (customerid != 0){
					billInfo.setCustId(customerid);
				}
				
				System.out.println("**********"+incomeType);
				bill.createBillInfo(billInfo);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

		private JSONArray viewIncomeType() {
			JSONArray itjsonArr = new JSONArray();
			IncomeTypeDAOImpl it = new IncomeTypeDAOImpl();
			try {
				itjsonArr = it.viewIT();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return itjsonArr;
		}
		
		private JSONArray viewExpenseType() {
			JSONArray etjsonArr = new JSONArray();
			DailyExpenseDAOImpl exDaoImpl = new DailyExpenseDAOImpl();
			
			etjsonArr = exDaoImpl.getExpenseorIncomeTypeList();
			return etjsonArr;
		}


		private JSONArray viewUsers() {
			JSONArray userjsonArr = new JSONArray();
			DailyExpenseDAOImpl exDaoImpl = new DailyExpenseDAOImpl();
			userjsonArr = exDaoImpl.getUserList();
			
			return userjsonArr;
		}
}
