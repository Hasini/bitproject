package pro.bit.bitproject.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.LendingSheduleDAOImpl;

/**
 * Servlet implementation class Lend_Details_View_Controller
 */
@WebServlet("/Lend_Details_View_Controller")
public class Lend_Details_View_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Lend_Details_View_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonArray = new JSONArray();
		JSONArray cusJsonArr = new JSONArray();
		JSONObject json = new JSONObject();
		
		String cusMobile=request.getParameter("cus_no");
		String cus_nic=request.getParameter("cus_nic_txt");
		
		
		if(!(cusMobile == null)){
			cusMobile = request.getParameter("cus_no");
			jsonArray = getLendDetailsByNo(cusMobile);
			try {
				json.put("cusObj", jsonArray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			cus_nic = request.getParameter("cus_nic_txt");
			cusJsonArr = getLendDetailsByNIC(cus_nic);
			try {
				json.put("cusObjWhenNIC", cusJsonArr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	private JSONArray getLendDetailsByNIC(String cus_nic) {
		JSONArray cusLendDetArrByNIC = new JSONArray();
		LendingSheduleDAOImpl lendingSheduleDAOImpl = new LendingSheduleDAOImpl();
		try {
			cusLendDetArrByNIC = lendingSheduleDAOImpl.getLendDetailsByNIC(cus_nic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cusLendDetArrByNIC;
		
	}

	private JSONArray getLendDetailsByNo(String cus_no) {
		JSONArray cusLendDetArrByNo = new JSONArray();
		LendingSheduleDAOImpl lendingSheduleDAOImpl = new LendingSheduleDAOImpl();
		try {
			cusLendDetArrByNo = lendingSheduleDAOImpl.getLendDetailsByNo(cus_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cusLendDetArrByNo;
	}

	public JSONArray getCusDet(String cus_name,String cus_nic) {
		JSONArray cusDetArr = new JSONArray();
		LendingSheduleDAOImpl lendingSheduleDAOImpl = new LendingSheduleDAOImpl();
		try {
			cusDetArr = lendingSheduleDAOImpl.viewCustomerDetails(cus_nic, cus_nic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cusDetArr;
		
	}
	
}
