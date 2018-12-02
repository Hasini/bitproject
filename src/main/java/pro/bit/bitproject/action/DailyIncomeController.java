package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.FinanceDAOImpl;
import pro.bit.bitproject.domain.DailyIncome;

@WebServlet("/DailyIncomeController")
public class DailyIncomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DailyIncomeController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		String method = request.getParameter("method");
		
		switch (method) {
		
		case "viewIncomeTypes":
			jsonArray = viewIncomeType();
			try {
				json.put("incomeTypeObj", jsonArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		
        case "createIncomes":
        	try {
        		createIncomes (request,response);
        		json.put("suc", "Record created Successfully..!");
        	} catch (JSONException e) {
        		try {
					json.put("error", "Error Occured..Please Check the input values");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}catch (Exception ex) {
					try {
						json.put("error", "Error Occured..Please Check the input values");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			break;

		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

	private JSONArray viewIncomeType() {
		JSONArray jarr = new JSONArray();
		FinanceDAOImpl finDaoImpl = new FinanceDAOImpl();
		
		jarr = finDaoImpl.getIncomeTypeList();
		return jarr;
	}

	private void createIncomes(HttpServletRequest request, HttpServletResponse response) {
		DailyIncome dinc = new DailyIncome();
		FinanceDAOImpl finDao = new FinanceDAOImpl();
		try {
			double amount = 0.00;
			amount = Double.parseDouble(request.getParameter("amount"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate billDate = LocalDate.parse(request.getParameter("billdate"), formatter);
			LocalDate subdate = LocalDate.parse(request.getParameter("subdate"), formatter);
			
			dinc.setAmount(amount);
			dinc.setBillCode(request.getParameter("billcode"));
			dinc.setSubmittedUserid(Integer.parseInt(request.getParameter("submitteduser")));
			dinc.setBill_date(billDate);
			dinc.setSubmitted_date(subdate);
			dinc.setIncomeId(Integer.parseInt(request.getParameter("incometypeId")));
			dinc.setCustomer_id(Integer.parseInt(request.getParameter("customer")));
			dinc.setEnteredUserid(1);
			dinc.setEntered_time(LocalDateTime.now());
		
			finDao.createDailyIncome(dinc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
