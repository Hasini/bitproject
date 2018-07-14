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

import org.json.JSONArray;
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

    	JSONArray jsonArray=new JSONArray();
    	try {
    		if (chkVal.equals("create")){
    			createBranch(request, response);
    			json.put("success", request.getParameter("code"));
    			
    		}else if (chkVal .equals("view")){
    			jsonArray=view();    			
    			json.put("branchListObject", jsonArray);
    			
    		}else if(chkVal.equals("update")){
    			update(request,response);
    			json.put("success", request.getParameter("codeu"));
    		}else{
    			delete(request, response);
    			json.put("success", request.getParameter("coded"));
    		}
    	
			
		} catch (Exception e) {
			try {
				json.put("error", "Error Occured..!");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
    	response.setContentType("application/json");
		response.getWriter().write(json.toString());
		
    }
    
    

	public void createBranch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
		String code = request.getParameter("codeu");
		String descr = request.getParameter("descru");
		LocalDateTime createddate =  LocalDateTime.now();
		
		branch.setBranchCode(code);
		branch.setBranchDescr(descr);
		branch.setCreatedTime(createddate);
		bdaoimpl.updateBranch(branch);
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		String coded = request.getParameter("coded");
		String descrd = request.getParameter("descrd");
		bdaoimpl.deleteBranch(coded,descrd);
		
	}
	
	public JSONArray view() throws SQLException, Exception {
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		//List<String> branchlist = new ArrayList<>();
		JSONArray jsonArray=new JSONArray();
		jsonArray= bdaoimpl.viewBranchDetails();
		
		return jsonArray;
	 }

}
