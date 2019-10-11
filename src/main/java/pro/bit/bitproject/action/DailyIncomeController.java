package pro.bit.bitproject.action;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
		InputStream inputStream = null;
		try {
			double amount = 0.00;
			amount = Double.parseDouble(request.getParameter("amount"));
			String billDate = request.getParameter("billdate");
			String subdate = request.getParameter("subdate");
			Part filePart = request.getPart("billimg");
			dinc.setAmount(amount);
			dinc.setBillCode(request.getParameter("billcode"));
			dinc.setSubmittedUserid(Integer.parseInt(request.getParameter("submitteduser")));
			dinc.setBill_date(billDate);
			dinc.setSubmitted_date(subdate);
			dinc.setIncomeId(Integer.parseInt(request.getParameter("incometypeId")));
			dinc.setBranchId(Integer.parseInt(request.getParameter("branch")));
			dinc.setEnteredUserid(1);
			dinc.setEntered_time(LocalDateTime.now());
			inputStream = filePart.getInputStream();
		
			finDao.createDailyIncome(dinc, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
