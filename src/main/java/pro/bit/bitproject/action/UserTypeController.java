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
import pro.bit.bitproject.domain.UserType;

/**
 * Servlet implementation class UserTypeController
 */
@WebServlet("/usertype")
public class UserTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserTypeController() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		
		
		try {
			//String codeut = request.getParameter("codeut");
			create(request, response);
			json.put("success", "User type");
		} catch (JSONException e) {
			try {
				json.put("error", "User type");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	public UserTypeController create(HttpServletRequest request,HttpServletResponse response) {
		
		UserTypeDAOImpl us = new UserTypeDAOImpl();
		UserType ut = new UserType();
		
		String codeut = request.getParameter("codeut");
		String descrut = request.getParameter("descrut");
		
		
		ut.setUserTypeCode(codeut);
		ut.setUserTypeDescr(descrut);
		
		System.out.println(ut+codeut+descrut);
		try {
			us.createUserType(ut);
		} catch (SQLException e) {
			System.out.println("sql");
			e.printStackTrace();
		}
		return null;
		
	}


}
