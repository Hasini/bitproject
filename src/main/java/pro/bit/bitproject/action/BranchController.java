/**
 * 
 */
package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.BranchDAOImpl;
import pro.bit.bitproject.domain.Branch;



/**
 * @author Hasini
 *
 */
@WebServlet("/createaction")
public class BranchController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    public BranchController() {
        super();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	JSONObject json = new JSONObject();
    	String chkVal = request.getParameter("checkboxVal");
    	
    	try {
    		if (chkVal.equals("create")){
    			createBranch(request, response);
    			json.put("success", "success");
    		}else if (chkVal .equals("update")){
    			//update(request,response);
    			view(request,response);
    			json.put("error", "Error occured");
    		}else 
    			delete(request, response);
			
		} catch (Exception e) {
			try {
				json.put("error", e);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			
		}
    	response.setContentType("application/json");
		response.getWriter().write(json.toString());
		System.out.println(json.toString());
		
  }
    
    

	private void createBranch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		Branch branch = new Branch();
		
		String code = request.getParameter("code");
		String descr = request.getParameter("descr");
		LocalDateTime createddate =  LocalDateTime.now();
		
		branch.setBranchCode(code);
		branch.setBranchDescr(descr);
		branch.setCreatedTime(createddate);
		bdaoimpl.createBranch(branch);
	}

		
	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
			
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		Branch branch = new Branch();
		String code = request.getParameter("code");
		String descr = request.getParameter("descr");
		bdaoimpl.updateBranch(branch);
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		//Branch branch = new Branch();
		String code = request.getParameter("code");
		//String descr = request.getParameter("descr");
		
		bdaoimpl.deleteBranch(code);
		
	}
	
	public void view(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		JSONObject json = new JSONObject();
		
		try {
			BranchDAOImpl bdaoimpl = new BranchDAOImpl();
			List<Object> branchlist = new ArrayList<>();
			branchlist= bdaoimpl.viewBranchDetails();
			//request.setAttribute("branchlist", branchlist);
			
			//json.put("code", branchlist.get(0));
			json.put("descr", branchlist.get(1));
		} catch (Exception e) {
			json.put("error", "Error occured");
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	 }

}
