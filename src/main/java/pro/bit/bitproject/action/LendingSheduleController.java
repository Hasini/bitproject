package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;
import pro.bit.bitproject.daoImpl.LendingSheduleDAOImpl;

import pro.bit.bitproject.domain.LendingShedule;

@WebServlet("/LendingSheduleController")
public class LendingSheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LendingSheduleController() {
        super();
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String method = request.getParameter("method");
		String custcashbookid =request.getParameter("custcashbookid");
		//JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		
		switch (method) {
		case "viewDet":
			
			Double arr = gettotarr(custcashbookid);
			try {
				json.put("arr", arr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "sheduleLoan":
			Boolean isSchedule=sheduleloan(request,response);
			if (isSchedule ==true){
				System.out.println("truee");
				try {
					json.put("msg1", "Installment plan added ..");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					System.out.println("else sch jason");
					json.put("msg", "Sorry..! Arrears amount should be above 50000 to shedule installments");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

	private boolean sheduleloan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject js = new JSONObject();
		LendingShedule ls = new LendingShedule();
		LendingSheduleDAOImpl ldao = new LendingSheduleDAOImpl();
		String custcashbookid = request.getParameter("custcashbookid");
		int mode = Integer.parseInt(request.getParameter("mode"));
		double arramount = gettotarr(custcashbookid);
		double modepayment = arramount/mode;
		LocalDateTime createdtime = LocalDateTime.now();
		int caid = getcaid(custcashbookid);
		//String sts = getCusById(custcashbookid);
		boolean isSchedule=false;
		
		try{
			if (arramount>=50000){
				//System.out.println("asasasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				
					ls.setCash_arrears_id(caid);
					ls.setCreatedtime(createdtime);
					ls.setMode(mode);
					ls.setModepayment(modepayment);
					//ls.setStstus(sts);
					ls.setCustcashbookid(custcashbookid);
					
					ldao.createls(ls);
					isSchedule=true;
			}else{
				System.out.println("elseeeeeeee sch");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return isSchedule;
	}
	
	private double gettotarr(String custcashbookid){
		LendingSheduleDAOImpl lendDao = new LendingSheduleDAOImpl();
		double tot = 0;
		try {
			tot=lendDao.gettotArr(custcashbookid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tot;
	}
	
	private int getcaid(String custcashbookid){
		LendingSheduleDAOImpl lendDao = new LendingSheduleDAOImpl();
		int casid = 0;
		try {
			casid=lendDao.getcaid(custcashbookid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return casid;
	}
	
	private String getCusById(String custcashbookid){
		CustomerRegistrationDAOImpl cusDao = new CustomerRegistrationDAOImpl();
		String cussts = cusDao.getCusSTSById(custcashbookid);
		return cussts;
	}
	

}
