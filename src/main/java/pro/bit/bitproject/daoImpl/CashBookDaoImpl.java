package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.CashBookDao;
import pro.bit.bitproject.domain.CashBook;

public class CashBookDaoImpl implements CashBookDao {

	public CashBook createCasahbook(CashBook cb) {
		String cbcreatequery = "INSERT INTO cash_book (branch_id,customer_id,bill_amount,paid_amount,paid_date,entered_user,custcashbookid,pay_type,extra_pay,shedule_type) VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {

			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(cbcreatequery);
			ps.setInt(1, cb.getBranchId());
			ps.setInt(2, cb.getCustomerId());
			ps.setDouble(3, cb.getBillAmount());
			ps.setDouble(4, cb.getPaidAmount());
			ps.setTimestamp(5, Timestamp.valueOf(cb.getPaiddate()));
			ps.setInt(6, cb.getUser());
			ps.setString(7, cb.getCustcashbookId());
			ps.setString(8, cb.getPaytype());
			ps.setDouble(9, cb.getExtrapayment());
			ps.setString(10, cb.getShedule_status());
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
			ResultSet rs = ps.executeQuery();
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
			PreparedStatement ps = ConnectionUtil.openConnection()
					.prepareStatement("select mode_payment from lend_shedule where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mp = rs.getDouble("mode_payment");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return mp;
	}

	public int getmode(String custcashbookid) {
		int mode = 0;

		try {
			PreparedStatement ps = ConnectionUtil.openConnection()
					.prepareStatement("select mode from lend_shedule where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mode = rs.getInt("mode");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mode;
	}
	
	public String getSchType(String custcashbookid) throws SQLException {
		String schType = "NS";
		try {
			PreparedStatement ps = ConnectionUtil.openConnection()
					.prepareStatement("select shedule_type from cash_book where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				schType = rs.getString("shedule_type");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schType;
	}
	
	
	public int getNoOfPaidInstallments(String custcashbookid) {
		int no_of_paid_ins = 0;

		try {
			PreparedStatement ps = ConnectionUtil.openConnection()
					.prepareStatement("select no_of_paid_ins from lend_shedule where custcashbookid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				no_of_paid_ins = rs.getInt("no_of_paid_ins");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(no_of_paid_ins+"no_of_paid_ins");
		return no_of_paid_ins;
	}

	
	public void saveArrearsAmt(String custcashbookid, LocalDateTime paiddate, double currentarr) {
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

	public void saveArrearsAmttogetTot(String custcashbookid, LocalDateTime paiddate, double currentarr) {
		String createtotarrquery = "INSERT INTO tot_arrears (cust_cash_booid,tot_arrears,last_updated_date) VALUES (?,?,?)";

		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(createtotarrquery);
			ps.setString(1, custcashbookid);
			ps.setDouble(2, currentarr);
			ps.setTimestamp(3, Timestamp.valueOf(paiddate));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkAvalablityofArr(String custcashbookid) {

		boolean sts = false;
		PreparedStatement st;
		try {
			st = ConnectionUtil.openConnection()
					.prepareStatement("select cust_cash_booid from tot_arrears where cust_cash_booid = ?");
			st.setString(1, custcashbookid);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(custcashbookid)) {
					sts = true;
				} else {
					sts = false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sts;

	}

	public void updateArrearsAmttogetTot(String custcashbookid, LocalDateTime paiddate,
			double availabletotarrearsforthecus, double extrayment, double todayarr) throws Exception {
		PreparedStatement ps;

		try {
			ps = ConnectionUtil.openConnection().prepareStatement(
					"update tot_arrears set tot_arrears=?,last_updated_date=? where cust_cash_booid=?");
			ps.setDouble(1, ((todayarr + availabletotarrearsforthecus) - extrayment));
			ps.setTimestamp(2, Timestamp.valueOf(paiddate));
			ps.setString(3, custcashbookid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public double getcurrenttotarr(String custcashbookid) {
		double availabletotarr = 0.00;
		try {

			PreparedStatement ps = ConnectionUtil.openConnection()
					.prepareStatement("select cust_cash_booid,tot_arrears from tot_arrears where cust_cash_booid = ? ");
			ps.setString(1, custcashbookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				availabletotarr = rs.getDouble(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return availabletotarr;
	}

	public double getcurrentarr(String custcashbookid) {
		double ca = 0.00;

		try {
			PreparedStatement ps = ConnectionUtil.openConnection()
					.prepareStatement("select cust_cash_booid,tot_arrears from tot_arrears where cust_cash_booid = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ca = rs.getDouble(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ca;
	}

}
