package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		create(request, response);
	}
	
	public UserTypeController create(HttpServletRequest request,HttpServletResponse response) {
		
		UserTypeDAOImpl us = new UserTypeDAOImpl();
		UserType ut = new UserType();
		
		
		String code = request.getParameter("code");
		String descr = request.getParameter("descr");
		
		for (int usertypeid= 100 ; usertypeid == 1000 ;usertypeid ++){
			ut.setUserTypeId(usertypeid);
		}
		ut.setUserTypeCode(code);
		ut.setUserTypeDescr(descr);
		
		try {
			us.createUserType(ut);
		} catch (SQLException e) {
			System.out.println("sql");
			e.printStackTrace();
		}
		return null;
		
	}


}
