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
    		} else if (chkVal .equals ("view")){
    			jsonArray=view();    			
    			json.put("branchListObject", jsonArray);
    		}  else if (chkVal .equals ("viewWithCode")){
    			int branchId = Integer.parseInt(request.getParameter("branchId"));
    			jsonArray=viewBranchDetailsWithCode(branchId);    			
    			json.put("branchListObject", jsonArray);
    			/*//json.put("desc", jsonArray.getString(2));
    			json.put("address", jsonArray.getString(4));
    			json.put("contact", jsonArray.getString(5));*/
    		} else if(chkVal.equals("update")){
    			update(request,response);
    			json.put("success", "Successfully Updated the branch code");
    			json.put("error", "Error occured while updating..!");
    		} else{
    			delete(request, response);
    			json.put("success", "Record is no more valid..!");
    		}	
		} catch (Exception e) {
			try {
				json.put("error", "Error Occured while updating the record..!");
			} catch (JSONException e1) {
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
		String add = request.getParameter("add");
		String contact = request.getParameter("contact");
		LocalDateTime createddate =  LocalDateTime.now();
		
		branch.setBranchCode(code);
		branch.setBranchDescr(descr);
		branch.setAddress(add);
		branch.setContact(contact);
		branch.setCreatedTime(createddate);
		
		bdaoimpl.createBranch(branch);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		try {
			BranchDAOImpl bdaoimpl = new BranchDAOImpl();
			Branch branch = new Branch();
			int id = Integer.parseInt(request.getParameter("id"));
			String descr = request.getParameter("descru");
			LocalDateTime updatedTime =  LocalDateTime.now();
			branch.setBranchId(id);
			branch.setBranchDescr(descr);
			branch.setUpdatedDate(updatedTime);
			bdaoimpl.updateBranch(branch);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		int id = Integer.parseInt(request.getParameter("id"));
		bdaoimpl.deleteBranch(id);
	}
	
	public JSONArray view() throws SQLException, Exception {
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		JSONArray jsonArray=new JSONArray();
		jsonArray= bdaoimpl.viewBranchDetails();
		return jsonArray;
	 }
	
	public JSONArray viewBranchDetailsWithCode(int branchId) throws SQLException, Exception {
		BranchDAOImpl bdaoimpl = new BranchDAOImpl();
		JSONArray jsonArray=new JSONArray();
		jsonArray= bdaoimpl.viewBranchDetailsWithCode(branchId);
		return jsonArray;
	 }
	
	

}
