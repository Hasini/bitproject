package pro.bit.bitproject.action;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
		case "getBranchIncome":
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

		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	private double calculateBranchwiseDailyProfit(int branchId) {
		LocalDateTime today = LocalDateTime.now();
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
	
	/*private double calculateTotalDailyProfit(double dailyIncome,double dailyExpences,Date today){
		double totIncome = getTotalIncome ();
		double totExpences = getTotExpences();
		double profit = totIncome - totExpences;
		return profit;
	}
	
	private double calculateBranchwiseDailyProfit() {
		return 0;
	}*/
	
	
	

}
