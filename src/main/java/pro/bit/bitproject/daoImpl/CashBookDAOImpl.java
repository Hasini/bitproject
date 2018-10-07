package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.LendingDAO;
import pro.bit.bitproject.domain.CashBook;
import pro.bit.bitproject.domain.LendingShedule;

public class CashBookDAOImpl implements LendingDAO{

	public CashBook createCasahbook(CashBook cb) {
		String cbcreatequery = "INSERT INTO cash_book (branch_id,customer_id,bill_amount,paid_amount,paid_date,custcashbookid,installment_payment) VALUES (?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(cbcreatequery);
			ps.setInt(1, cb.getBranchId());
			ps.setInt(2, cb.getCustomerId());
			ps.setDouble(3, cb.getBillAmount());
			ps.setDouble(4, cb.getPaidAmount());
			ps.setTimestamp(5, Timestamp.valueOf(cb.getPaiddate()));
			ps.setString(6, cb.getCustcashbookId());
			ps.setDouble(7, cb.getInstallmentpaymnet());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public String getcuscashbookid(int customerId) {
		String nic = null;
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select cus_nic from customer_details where customer_id = ?");
			ps.setInt(1, customerId);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				nic = rs.getString("cus_nic");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return nic;
		
	}

	public double getmodepayment(String custcashbookid) {
		double mp = 0.00;
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select mode_payment from lend_shedule where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				mp = rs.getDouble("mode_payment");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return mp;
	}

	
	public void saveArrearsAmt(String custcashbookid, LocalDateTime paiddate, double currentarr) {
		System.out.println("savveeee");
		String createarrquery = "INSERT INTO cash_arrears (custcashbookid,arrsamt,createddate) VALUES (?,?,?)";
		
		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(createarrquery);
			ps.setString(1, custcashbookid);
			ps.setDouble(2, currentarr);
			ps.setTimestamp(3, Timestamp.valueOf(paiddate));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getcurrentarr(String custcashbookid) {
		double ca = 0.00;
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select arrsamt from cash_arrears where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				ca = rs.getDouble("arrsamt");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ca;
	}

	@Override
	public double gettotArr(String custcashbookid) throws SQLException {
		return 0;
		// TODO Auto-generated method stub
		
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
}

