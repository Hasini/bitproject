package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.UserTypeDAOImpl;
import pro.bit.bitproject.domain.CreditLimit;
import pro.bit.bitproject.domain.UserType;

/**
 * Servlet implementation class CreditLimitController
 */
@WebServlet("/CreditLimitController")
public class CreditLimitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreditLimitController() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		try {
			create(request, response);
			json.put("success", "Credit Limit");
		} catch (JSONException e) {
			try {
				json.put("error", "Error Credit Limit");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	public CreditLimitController create(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		UserTypeDAOImpl us = new UserTypeDAOImpl();
		CreditLimit creditLimit = new CreditLimit();
		int customerType = Integer.parseInt(request.getParameter("cusTypeID"));
		long creditLimitamount = Long.parseLong(request.getParameter("creditLimit"));
		creditLimit.setCustomerType(customerType);
		creditLimit.setCreditLimit(creditLimitamount);
		
		us.createCreditLimit(creditLimit);
		return null;
	}

}
