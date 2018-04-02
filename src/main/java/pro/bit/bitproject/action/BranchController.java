/**
 * 
 */
package pro.bit.bitproject.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.bit.bitproject.daoImpl.BranchDAOImpl;
import pro.bit.bitproject.domain.Branch;

/**
 * @author Hasini
 *
 */
@WebServlet("/cudbranchdet")
public class BranchController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    public BranchController() {
        super();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			create(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update(request,response);
		delete(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		Branch branch = new Branch();
		
		String code = request.getParameter("code");
		String descr = request.getParameter("descr");
		
		bdaoimpl.createBranch(branch);
		
	}

	
private void update(HttpServletRequest request, HttpServletResponse response) {
		
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		Branch branch = new Branch();
		
		String code = request.getParameter("code");
		String descr = request.getParameter("descr");
		
		bdaoimpl.updateBranch(descr);
		
	}


private void delete(HttpServletRequest request, HttpServletResponse response) {
	
	BranchDAOImpl bdaoimpl = new BranchDAOImpl();
	Branch branch = new Branch();
	
	//String code = request.getParameter("code");
	String descr = request.getParameter("descr");
	
	bdaoimpl.deleteBranch(descr);
	
}
}
