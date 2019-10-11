package pro.bit.bitproject.action;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.FinanceDAOImpl;

/**
 * Servlet implementation class DayEndController
 */
@WebServlet("/DayEndController")
public class DayEndController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DayEndController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		int branchId = Integer.parseInt(request.getParameter("branch"));
		JSONObject json = new JSONObject();
		
		switch (method) {
		case "getBranchProfit":
			double branchProfit = 0.00;
			try {
				
				branchProfit = calculateBranchwiseDailyProfit(branchId);
				json.put("profit", branchProfit);
				json.put("success", "Profit calculated..!");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "getBranchIncome":
			double branchInome = 0.00;
			try {
				
				branchInome = calculateBranchwiseDailyIncome(branchId);
				json.put("branchInome", branchInome);
				json.put("success", "branchInome calculated..!");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "getBranchExpense":
			double branchExpense = 0.00;
			try {
				
				branchExpense = calculateBranchwiseDailyExpense(branchId);
				json.put("branchExpense", branchExpense);
				json.put("success", "branchExpense calculated..!");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	private double calculateBranchwiseDailyProfit(int branchId) {
		Date today = Calendar.getInstance().getTime();
		FinanceDAOImpl financeDAO = new FinanceDAOImpl();
		Double profit = 0.00;
		try {
			Double totIncome = financeDAO.getTotalIncome(branchId,today);
			Double totExpences = financeDAO.getTotalExpences(branchId,today);
			profit = totIncome + (totExpences);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profit;
	}
	
	private double calculateBranchwiseDailyIncome(int branchId) {
		//LocalDateTime today = LocalDateTime.now();
		Date today = Calendar.getInstance().getTime();
		FinanceDAOImpl financeDAO = new FinanceDAOImpl();
		Double totIncome = 0.00;
		try {
			totIncome = financeDAO.getTotalIncome(branchId,today);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totIncome;
	}
	
	private double calculateBranchwiseDailyExpense(int branchId) {
		//LocalDateTime today = LocalDateTime.now();
		Date today = Calendar.getInstance().getTime();
		FinanceDAOImpl financeDAO = new FinanceDAOImpl();
		Double totExpense = 0.00;
		try {
			totExpense = financeDAO.getTotalExpences(branchId,today);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totExpense;
	}
	
	private void calulateWalletAmount () {
		
	}
	
	
	
	
	

}
