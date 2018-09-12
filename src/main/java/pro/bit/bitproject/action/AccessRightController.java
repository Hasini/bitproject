/**
 * 
 */
package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.bit.bitproject.daoImpl.AccessRightDAOImpl;
import pro.bit.bitproject.domain.AccessRight;

/**
 * @author Hasini
 *
 */
@WebServlet("/accessright")
public class AccessRightController extends HttpServlet {
	
private static final long serialVersionUID = 1L;
    
    public AccessRightController() {
        super();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	create (request,response);
    }

	private void create(HttpServletRequest request, HttpServletResponse response) {
		AccessRight ac = new AccessRight();
		AccessRightDAOImpl acd = new AccessRightDAOImpl();
		
		String ProgramCode = request.getParameter("code");
		String ProgramDescr = request.getParameter("descr");
		int accessLevel = Integer.parseInt(request.getParameter("checkitem"));
		LocalDateTime createddate =  LocalDateTime.now();
		System.out.println(accessLevel + "*****************");
		//int accessLevel
		ac.setProgramCode(ProgramCode);
		ac.setProgramDescr(ProgramDescr);
		ac.setAccessLevel(accessLevel);
		ac.setCreatedTime(createddate);
		
		try {
			acd.grantAccesslevels(ac);
		} catch (SQLException e) {
			System.out.println("create exception");
			e.printStackTrace();
		}
		
	}

}
