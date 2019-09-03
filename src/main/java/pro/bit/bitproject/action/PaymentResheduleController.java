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

import pro.bit.bitproject.daoImpl.PaymentResheduleDAOImpl;

/**
 * Servlet implementation class PaymentResheduleController
 */
@WebServlet("/PaymentResheduleController")
public class PaymentResheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentResheduleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		CashBkController cashBookObj = new CashBkController();
		
		String method = request.getParameter("method");
		int customerId = Integer.parseInt(request.getParameter("cusid"));
		int noOfIns = Integer.parseInt(request.getParameter("mode"));
		double newInstallment = Double.parseDouble(request.getParameter("newins"));
		LocalDateTime created_date = LocalDateTime.now();
		String custCashBookId = cashBookObj.getNic(customerId);
		int previousMode = cashBookObj.getmode(custCashBookId);
		double previousInstallmentPayment = 0.00;
		
		try {
			previousInstallmentPayment = cashBookObj.getModePayment(customerId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		switch (method) {
		case "createLendReschdule":
			try {
				createLendReschdule(custCashBookId,noOfIns,newInstallment,created_date,created_date,previousMode,previousInstallmentPayment);
				updateCashBook (custCashBookId);
				json.put("success", "Cash Book Updated Successfully..!");
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	private void createLendReschdule(String custCashBookId, int noOfIns, double newInstallment,
			LocalDateTime created_date, LocalDateTime created_date2, int previousMode,
			double previousInstallmentPayment) throws SQLException{
		PaymentResheduleDAOImpl reschduleDaoImpl = new PaymentResheduleDAOImpl();
		reschduleDaoImpl.createLendReschdule(custCashBookId, noOfIns, newInstallment, created_date, created_date2, previousMode, previousInstallmentPayment);
		// TODO Auto-generated method stub
		
	}
	
	private void updateCashBook(String custCashBookId) {
		PaymentResheduleDAOImpl reschduleDaoImpl = new PaymentResheduleDAOImpl();
		try {
			reschduleDaoImpl.updateCashBook(custCashBookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
