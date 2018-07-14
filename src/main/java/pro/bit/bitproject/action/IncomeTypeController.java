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
			}else if (chkVal .equals("view")){
	    			jsonArray=view();    			
	    			jsonobj.put("ListObject", jsonArray);
	    	}else if(chkVal.equals("update")){
    			update(request,response);
    			jsonobj.put("success", request.getParameter("codeu"));
    		}else{
    			delete(request, response);
    			jsonobj.put("success", request.getParameter("coded"));
    		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		String coded = request.getParameter("coded");
		itDAOImpl.deleteIT(coded);
		
		
	}


	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		IncomeTypeDAOImpl itDAOImpl = new IncomeTypeDAOImpl();
		IncomeType it = new IncomeType();
		
		String code = request.getParameter("codeu");
		String descr = request.getParameter("descru");
		LocalDateTime createddate =  LocalDateTime.now();
		
		it.setIncometypecode(code);
		it.setIncometypeDescr(descr);
		it.setCreatedtime(createddate);
		
		itDAOImpl.updateIT(it);
		
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
