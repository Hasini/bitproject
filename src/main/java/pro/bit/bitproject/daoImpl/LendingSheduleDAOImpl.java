package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.LendingDAO;
import pro.bit.bitproject.domain.CashBook;
import pro.bit.bitproject.domain.LendingShedule;

public class LendingSheduleDAOImpl implements LendingDAO{

	public double gettotArr(String custcashbookid) throws SQLException  {
		double totarr = 0;
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement
					("select ca_id,arrsamt from cash_arrears where custcashbookid = ?");
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
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement
					("select ca_id from cash_arrears where custcashbookid = ?");
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
	
	public JSONArray viewCustomerDetails(String cus_nic,String cus_home_tel) throws SQLException, Exception {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String viewCusDet = "select cd.cus_home_tel,cd.cus_nic,bd.branch_descr,cd.status ,ta.tot_arrears"+
							"from customer_details cd left join branch_det bd on cd.branch_id = bd.branch_id left join tot_arrears ta"
							+ "on cd.cus_nic= ta.cust_cash_booid"
							+ "where cd.cus_nic= ? or cd.cus_home_tel=?";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(viewCusDet);
		ps.setString(1, cus_nic);
		ps.setString(2, cus_home_tel);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("cus_fullname", rs.getString(1));
			 jsonObject.put("cus_nic", rs.getString(2));
			 jsonObject.put("branch_descr", rs.getString(3));
			 jsonObject.put("status", rs.getString(4));
			 jsonObject.put("tot_arrears", rs.getString(5));
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}

	public JSONArray getLendDetailsByNIC(String cus_nic) throws SQLException, Exception {
		ResultSet rs = null;
		JSONArray cusjsonArray=new JSONArray();
		String listusers = "select customer_id,cus_nic from customer_details ";
		
		String qry = "select a.customer_id,sum(cb.paid_amount) as total_paid,sum(cb.extra_pay) total_extraPay, "
		+ "sum(cb.bill_amount) total_bill,(sum(cb.bill_amount) -(sum(cb.paid_amount)+sum(cb.extra_pay))) as arr" 
		+ "from customer_details a "
		+ "inner join cash_book cb "
		+ "on a.customer_id = cb.customer_id"
		+ "where a.cus_nic = ?"
		+ "group by a.customer_id";
				
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(listusers);
		ps.executeQuery();
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject cusObjWhenNIC=new JSONObject();
			 cusObjWhenNIC.put("customer_id", rs.getInt(1));
			 cusObjWhenNIC.put("total_paid", rs.getDouble(2));
			 cusObjWhenNIC.put("total_extraPay", rs.getDouble(3));
			 cusObjWhenNIC.put("arr", rs.getDouble(4));
			 //cusjsonObject.put("cus_nic", rs.getString(2));
			 cusjsonArray.put(cusObjWhenNIC);
		}
		rs.close();
		return cusjsonArray; 
		
	}

	@Override
	public JSONArray getLendDetailsByNo(String cus_no) throws SQLException, Exception {
		ResultSet rs = null;
		JSONArray cusjsonArray=new JSONArray();
		String qry = "select a.cus_nic,sum(cb.paid_amount) as total_paid,"
				+ "sum(cb.extra_pay) total_extraPay," 
				+"sum(cb.bill_amount) total_bill,(sum(cb.bill_amount) -(sum(cb.paid_amount)+sum(cb.extra_pay))) as arr "
				+ "from customer_details a "
				+"inner join cash_book cb "
				+"on a.cus_nic = cb.custcashbookid "
				+"where a.cus_mobile= ? "
				+"group by a.cus_nic " ;
				
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(qry);
		ps.setString(1, cus_no);
		ps.executeQuery();
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject cusObjWhenMobile=new JSONObject();
			 cusObjWhenMobile.put("customer_id", rs.getInt(1));
			 cusObjWhenMobile.put("total_paid", rs.getDouble(2));
			 cusObjWhenMobile.put("total_extraPay", rs.getDouble(3));
			 cusObjWhenMobile.put("arr", rs.getDouble(4));
			 //cusjsonObject.put("cus_nic", rs.getString(2));
			 cusjsonArray.put(cusObjWhenMobile);
		}
		rs.close();
		return cusjsonArray; 
		
		
		
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
	public void updateInsPay(String custcashbookid, int noofins,String sts) throws Exception {
		PreparedStatement ps;
		LocalDateTime updatedtime = LocalDateTime.now();
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("update lend_shedule set no_of_paid_ins=?,updated_time=?,sts=? where custcashbookid=?");
			
			ps.setInt(1,noofins);
			ps.setTimestamp(2, Timestamp.valueOf(updatedtime));
			ps.setString(3, sts);
			ps.setString(4, custcashbookid);
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
	}
	
	public void updateCashBook(String custCashBookId) throws SQLException {
		PreparedStatement ps;
		try {
			ps = ConnectionUtil.openConnection().prepareStatement(
					"update cash_book set shedule_type=? where custcashbookid=?");
			ps.setString(1, "S");
			ps.setString(2, custCashBookId);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void createLendReschdule(String custCashBookId, int noOfIns, double newInstallment,
			LocalDateTime created_date, LocalDateTime created_date2, int previousMode,
			double previousInstallmentPayment) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getReSheduledSts(String custcashbookid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getReschPaymentInstallment(String custcashbookid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
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

	
	
}
