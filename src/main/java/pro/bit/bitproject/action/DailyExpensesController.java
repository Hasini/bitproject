package pro.bit.bitproject.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.DailyExpenseDAOImpl;

/**
 * Servlet implementation class DailyExpenses
 */
@WebServlet("/DailyExpenses")
public class DailyExpensesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public DailyExpensesController() {
        super();
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        case "create":
			
			break;

		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}


	private JSONArray viewExpenseType() {
		// TODO Auto-generated method stub
		return null;
	}


	private JSONArray viewUsers() {
		JSONArray userjsonArr = new JSONArray();
		DailyExpenseDAOImpl exDaoImpl = new DailyExpenseDAOImpl();
		userjsonArr = exDaoImpl.getUserList();
		
		return userjsonArr;
	}

}
