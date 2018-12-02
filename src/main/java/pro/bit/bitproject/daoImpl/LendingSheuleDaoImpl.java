package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.LendSchDao;
import pro.bit.bitproject.domain.LendingShedule;


public class LendingSheuleDaoImpl implements LendSchDao{

	@Override
	public double gettotArr(String custcashbookid) throws SQLException {
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

	@Override
	public int getcaid(String custcashbookid) throws SQLException {
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
	public String getSheduledSts(String custcashbookid) {
		String sts = null;
		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select sts from lend_shedule where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				sts = rs.getString("sts");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sts;
	}

	@Override
	public void updateInsPay(String custcashbookid, int noofins) throws Exception {
		PreparedStatement ps;
		LocalDateTime updatedtime = LocalDateTime.now();
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("update lend_shedule set no_of_paid_ins=?,updated_time=? where custcashbookid=?");
			
			
			ps.setInt(1,noofins);
			ps.setTimestamp(2, Timestamp.valueOf(updatedtime));
			ps.setString(3, custcashbookid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public int getpaidscount(String custcashbookid) {
		int noofpaidins=0;
		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select no_of_paid_ins from lend_shedule where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				noofpaidins = rs.getInt("no_of_paid_ins");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noofpaidins;
	}

	@Override
	public void createls(LendingShedule ls) throws SQLException {
		String lendschquery = "INSERT INTO lend_shedule (cash_arrears_id,mode,created_time,mode_payment,custcashbookid,no_of_paid_ins,updated_time,sts) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(lendschquery);
			ps.setInt(1, ls.getCash_arrears_id());
			ps.setInt(2, ls.getMode());
			ps.setTimestamp(3, Timestamp.valueOf(ls.getCreatedtime()));
			ps.setDouble(4, ls.getModepayment());
			ps.setString(5, ls.getCustcashbookid());
			ps.setInt(6, ls.getNumberOfPaidIns());
			ps.setTimestamp(7, Timestamp.valueOf(ls.getUpdatedtime()));
			ps.setString(8, ls.getStatus());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}

}
