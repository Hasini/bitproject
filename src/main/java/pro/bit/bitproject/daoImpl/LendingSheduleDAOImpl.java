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

public class LendingSheduleDAOImpl implements LendingDAO{

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

	public double gettotArr(String custcashbookid) throws SQLException  {
		double totarr = 0;
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select ca_id,arrsamt from cash_arrears where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				totarr = rs.getDouble("arrsamt");
				System.out.println(totarr+"totamount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totarr;
	}
	
	
	public int getcaid(String custcashbookid) throws SQLException  {
		
		int casharrid = 0;
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select ca_id from cash_arrears where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				casharrid =rs.getInt("ca_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return casharrid;
	}

	@Override
	public void createls(LendingShedule ls) throws SQLException {
		String cbcreatequery = "INSERT INTO lend_shedule (cash_arrears_id,mode,created_time,mode_payment,custcashbookid) VALUES (?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(cbcreatequery);
			ps.setInt(1, ls.getCash_arrears_id());
			ps.setInt(2, ls.getMode());
			ps.setTimestamp(3, Timestamp.valueOf(ls.getCreatedtime()));
			ps.setDouble(4, ls.getModepayment());
			ps.setString(5, ls.getCustcashbookid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}

}
