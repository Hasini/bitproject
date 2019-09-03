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

import pro.bit.bitproject.daoImpl.ExpenseTypeDAOImpl;
import pro.bit.bitproject.domain.ExpenseType;


@WebServlet("/expenseTypeController")
public class ExpenseTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExpenseTypeController() {
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
    			jsonobj.put("success", "Record successfully Updated..!");
    			jsonobj.put("error", "Error occured while updating the record");
    		}else{
    			delete(request, response);
    			jsonobj.put("success", "Record no more valid");
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
		ExpenseTypeDAOImpl itdaoImpl = new ExpenseTypeDAOImpl();
		JSONArray jsonArray=new JSONArray();
		jsonArray=itdaoImpl.viewET();
		return jsonArray;
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		ExpenseTypeDAOImpl itDAOImpl = new ExpenseTypeDAOImpl();
		int expenseTypeId = Integer.parseInt(request.getParameter("expenseTypeId"));
		itDAOImpl.deleteIT(expenseTypeId);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		try{
			ExpenseTypeDAOImpl itDAOImpl = new ExpenseTypeDAOImpl();
			ExpenseType et = new ExpenseType();
			int id = Integer.parseInt(request.getParameter("expenseTypeId"));
			String descr = request.getParameter("descru");
			LocalDateTime createddate =  LocalDateTime.now();
			et.setExpenseTypeId(id);
			et.setExpensetypeDescr(descr);
			et.setCreatedtime(createddate);
			itDAOImpl.updateET(et);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExpenseTypeController create(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		ExpenseTypeDAOImpl itdaoimpl = new ExpenseTypeDAOImpl();
		ExpenseType et = new ExpenseType();
		
		String code = request.getParameter("code");
		String descr= request.getParameter("descr");
		LocalDateTime createddate =  LocalDateTime.now();
		
		et.setExpensetypecode(code);
		et.setExpensetypeDescr(descr);
		et.setCreatedtime(createddate);
		itdaoimpl.createET(code,descr,createddate);
		return null;
	}
}
