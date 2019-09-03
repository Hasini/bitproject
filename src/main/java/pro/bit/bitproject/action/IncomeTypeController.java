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

import pro.bit.bitproject.daoImpl.IncomeTypeDAOImpl;
import pro.bit.bitproject.domain.IncomeType;


@WebServlet("/incomeTypeController")
public class IncomeTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IncomeTypeController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonobj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		String chkVal = request.getParameter("checkboxVal");
		try {
			if (chkVal.equals("create")){
				create(request, response);
				jsonobj.put("success", "Record Successfully created..!");
			}else if (chkVal.equals("view")){
	    		jsonArray=view();    			
	    		jsonobj.put("ListObject", jsonArray);
	    	}else if(chkVal.equals("update")){
    			update(request,response);
    			jsonobj.put("success", "Record successfully updated..!");
    			jsonobj.put("error", "Error occured while updating..!");
    		}else{
    			delete(request, response);
    			jsonobj.put("success", "Record is no more valid..!");
    		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().write(jsonobj.toString());
	}
    
    public JSONArray view() throws SQLException, Exception {
		IncomeTypeDAOImpl itdaoImpl = new IncomeTypeDAOImpl();
		JSONArray jsonArray=new JSONArray();
		jsonArray=itdaoImpl.viewIT();
		return jsonArray;
	}


	public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		IncomeTypeDAOImpl itDAOImpl = new IncomeTypeDAOImpl();
		int incomeTypeId = Integer.parseInt(request.getParameter("incomeTypeId"));
		itDAOImpl.deleteIT(incomeTypeId);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		IncomeTypeDAOImpl itDAOImpl = new IncomeTypeDAOImpl();
		IncomeType it = new IncomeType();
		try {
			int id = Integer.parseInt(request.getParameter("incomeTypeId"));
			String descr = request.getParameter("descru");
			LocalDateTime createddate =  LocalDateTime.now();
			it.setIncomeTypeId(id);
			it.setIncometypeDescr(descr);
			it.setCreatedtime(createddate);
			itDAOImpl.updateIT(it);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public IncomeTypeController create(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		IncomeTypeDAOImpl itdaoimpl = new IncomeTypeDAOImpl();
		IncomeType it = new IncomeType();
		
		String code = request.getParameter("code");
		String descr= request.getParameter("descr");
		LocalDateTime createddate =  LocalDateTime.now();
		
		it.setIncometypecode(code);
		it.setIncometypeDescr(descr);
		it.setCreatedtime(createddate);
		
		
		itdaoimpl.createIT(code,descr,createddate);
		return null;
		
	}
}
