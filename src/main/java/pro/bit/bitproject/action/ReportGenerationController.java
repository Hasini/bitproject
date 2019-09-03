package pro.bit.bitproject.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;

/**
 * Servlet implementation class ReportGenerationController
 */
@WebServlet("/ReportGenerationController")
public class ReportGenerationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGenerationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		switch (method) {
		case "viewUsers":
			try {
				jsonArray = viewBlacklistedCustomers();
				json.put("blkListedObj", jsonArray);
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

	private JSONArray viewBlacklistedCustomers() {
		JSONArray cusarr = new JSONArray();
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		
		try {
			cusarr = cusdaoimpl.viewBlacklistedCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cusarr;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
