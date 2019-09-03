package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.json.JSONArray;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.LendingDAO;
import pro.bit.bitproject.domain.CashBook;
import pro.bit.bitproject.domain.LendingShedule;

public class PaymentResheduleDAOImpl implements LendingDAO {

	@Override
	public CashBook createCasahbook(CashBook cb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getcuscashbookid(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getmodepayment(String custcashbookid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveArrearsAmt(String custcashbookid, LocalDateTime paiddate, double currentarr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double gettotArr(String custcashbookid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getcaid(String custcashbookid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void createls(LendingShedule ls) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONArray viewCustomerDetails(String cus_nic, String cus_home_tel) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getLendDetailsByNIC(String cus_nic) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getLendDetailsByNo(String cus_no) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSheduledSts(String custcashbookid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInsPay(String custcashbookid, int noofins, String sts) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getpaidscount(String custcashbookid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void createLendReschdule(String custCashBookId, int noOfIns, double newInstallment,
			LocalDateTime created_date, LocalDateTime created_date2, int previousMode,
			double previousInstallmentPayment) throws SQLException {
			String reSchQuery = "INSERT INTO lend_reschdule (new_mode,previous_mode,"
		+ "new_installment_amount,previous_installment_amount,created_time,updated_time,cust_cash_book_id,status) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(reSchQuery);
			ps.setInt(1, noOfIns);
			ps.setInt(2, previousMode);
			ps.setDouble(3, newInstallment);
			ps.setDouble(4, previousInstallmentPayment);
			ps.setTimestamp(5, Timestamp.valueOf(created_date));
			ps.setTimestamp(6, Timestamp.valueOf(created_date2));
			ps.setString(7, custCashBookId);
			ps.setString(8, "RS");
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String getReSheduledSts(String custcashbookid) {
		String sts = null;
		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select status from lend_reschdule where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				sts = rs.getString("status");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sts;
	}
	
	public double getReschPaymentInstallment(String custcashbookid) {
		double reSchMp = 0.00;

		try {
			PreparedStatement ps = ConnectionUtil.openConnection()
					.prepareStatement("select new_installment_amount from lend_reschdule where cust_cash_book_id = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reSchMp = rs.getDouble("new_installment_amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reSchMp;
	}

	public void updateCashBook(String custCashBookId) throws SQLException {
		PreparedStatement ps;
		try {
			ps = ConnectionUtil.openConnection().prepareStatement(
					"update cash_book set shedule_type=? where custcashbookid=?");
			ps.setString(1, "RS");
			ps.setString(2, custCashBookId);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	
	
	
	

}
