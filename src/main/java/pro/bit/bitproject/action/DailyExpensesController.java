package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.FinanceDAOImpl;
import pro.bit.bitproject.domain.DailyExpense;


/**
 * Servlet implementation class DailyExpenses
 */
@WebServlet("/DailyExpensesController")
public class DailyExpensesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public DailyExpensesController() {
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
		
        case "createExpenses":
        	
        	try {
        		createExpenses (request,response);
        		json.put("suc", "Record Entered Successfully..!");
        	} catch (JSONException e) {
        		
			}catch (Exception e) {
				try {
					json.put("error", "Error Occured..Please Check the input values");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			break;

		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}


	public void createExpenses(HttpServletRequest request, HttpServletResponse response) {
		
		DailyExpense dex = new DailyExpense();
		FinanceDAOImpl dexDao = new FinanceDAOImpl();
		
		/*****Date Convertion******/
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String submitteddate = request.getParameter("subtime");
		String billdate = request.getParameter("billdate");
		
		//LocalDate submitteddateF = LocalDate.parse(submitteddate, formatter);
		//LocalDate billdateF = LocalDate.parse(billdate, formatter);
		
		
		int expenseType=0;
		int branchId=0;
		String dexDaoCode = request.getParameter("billcode");
		double dexDaoAmount = Double.parseDouble(request.getParameter("amount"));
		int submittedUserId = Integer.parseInt(request.getParameter("submitteduser"));
		LocalDateTime enteredTime = LocalDateTime.now();
		
		if (request.getParameter("expensetypeid")!=null){
			expenseType = Integer.parseInt(request.getParameter("expensetypeid"));
		}
		if (request.getParameter("branch")!=null){
			branchId = Integer.parseInt(request.getParameter("branch"));
		}
	
		try{
			dex.setBillCode(dexDaoCode);
			dex.setAmount(dexDaoAmount);
			dex.setSubmittedDate(submitteddate);
			dex.setCreatedTime(enteredTime);
			dex.setSubmittedUserid(submittedUserId);
			dex.setBillDate(billdate);
			
			if (expenseType != 0){
				dex.setExpenseType(expenseType);
			}
			
			if (branchId != 0){
				dex.setBranchId(branchId);
			}
			dexDao.createDailyExpense(dex);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private JSONArray viewExpenseType() {
		JSONArray etjsonArr = new JSONArray();
		FinanceDAOImpl exDaoImpl = new FinanceDAOImpl();
		
		etjsonArr = exDaoImpl.getExpenseTypeList();
		return etjsonArr;
	}

	private JSONArray viewUsers() {
		JSONArray userjsonArr = new JSONArray();
		FinanceDAOImpl exDaoImpl = new FinanceDAOImpl();
		userjsonArr = exDaoImpl.getUserList();
		
		return userjsonArr;
	}
}
