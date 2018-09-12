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
import pro.bit.bitproject.daoImpl.IncomeTypeDAOImpl;

/**
 * Servlet implementation class BillInfoController
 */
@WebServlet("/BillInfoController")
public class BillInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillInfoController() {
        super();
        // TODO Auto-generated constructor stub
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
			case "viewIncomeTypes":
				jsonArray = viewIncomeType();
				try {
					json.put("incomeTypeObj", jsonArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
				
	        case "create":
				createBill (request,response);
				break;

			default:
				break;
			}
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		}


		private void createBill(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

		private JSONArray viewIncomeType() {
			JSONArray itjsonArr = new JSONArray();
			IncomeTypeDAOImpl it = new IncomeTypeDAOImpl();
			try {
				itjsonArr = it.viewIT();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
