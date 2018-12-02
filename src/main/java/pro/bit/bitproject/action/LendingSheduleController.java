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

import pro.bit.bitproject.daoImpl.CashBookDaoImpl;
import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;
import pro.bit.bitproject.daoImpl.LendingSheuleDaoImpl;
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
		Double arr = 0.00;
		
		switch (method) {
		case "viewDet":
			try {
				arr = gettotarr(custcashbookid);
				System.out.println(arr+"arrears lend shedule");
				json.put("arr", arr);
			} catch (JSONException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "sheduleLoan":
			Boolean isSchedule=sheduleloan(request,response);
			if (isSchedule ==true){
				try {
					json.put("msg1", "Installement plan is added ..");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					System.out.println("else sch jason");
					json.put("msg", "Sorry..! You have added an installment plan alredy");
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
		LendingSheuleDaoImpl ldao = new LendingSheuleDaoImpl();
		double arramount;
		double modepayment =0.00;
		boolean isSchedule=false;
		
		String custcashbookid = request.getParameter("custcashbookid");
		int mode = Integer.parseInt(request.getParameter("mode"));
		LocalDateTime createdtime = LocalDateTime.now();
		LocalDateTime updatedtime = LocalDateTime.now();
		int caid = getcaid(custcashbookid);
		
		try {
			arramount = gettotarr(custcashbookid);
			modepayment = arramount/mode;
			if (arramount>=50000){
				//if (arramount>=50000 && getSheduledSts(custcashbookid)==false){
					ls.setCash_arrears_id(caid);
					ls.setCreatedtime(createdtime);
					ls.setMode(mode);
					ls.setModepayment(modepayment);
					ls.setCustcashbookid(custcashbookid);
					ls.setUpdatedtime(updatedtime);
					ls.setStatus("S");
					ldao.createls(ls);
					isSchedule=true;
			}else{
				System.out.println("elseeeeeeee sch");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return isSchedule;
	}
	
	private double gettotarr(String custcashbookid) throws SQLException{
		CashBookDaoImpl cDao = new CashBookDaoImpl();
		double tot = 0;
		tot=cDao.getcurrentarr(custcashbookid);
		return tot;
	}
	
	private int getcaid(String custcashbookid){
		LendingSheuleDaoImpl lendDao = new LendingSheuleDaoImpl();
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
	
	private boolean getSheduledSts(String custcashbookid){
		LendingSheuleDaoImpl lendDao = new LendingSheuleDaoImpl();
		String sts= "NS";
		sts=lendDao.getSheduledSts(custcashbookid);
		boolean boosts;
		boosts = sts .equals("S") ? true: false;
		System.out.println(boosts+"--");
		return boosts;
		
		
	}

}
