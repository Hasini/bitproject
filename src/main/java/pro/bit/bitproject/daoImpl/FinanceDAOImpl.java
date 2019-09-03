package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.FinanceDAO;
import pro.bit.bitproject.domain.DailyExpense;
import pro.bit.bitproject.domain.DailyIncome;

public class FinanceDAOImpl implements FinanceDAO {


	public void createDailyExpense(DailyExpense dex) {
		String insertQuery = "INSERT INTO daily_expences(amount,submitted_User,entered_by,created_time,expense_type,bill_code,"
				+ "submittedDate,billDate,branch_id) VALUES (?,?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
			ps.setDouble(1, (dex.getAmount())*(-1));
			ps.setInt(2, dex.getSubmittedUserid());
			ps.setInt(3, dex.getEnteredUserid());
			ps.setTimestamp(4, Timestamp.valueOf(dex.getCreatedTime()));
			ps.setInt(5, dex.getExpenseType());
			ps.setString(6, dex.getBillCode());
			ps.setObject(7, dex.getSubmittedDate());
			ps.setObject(8, dex.getBillDate());
			ps.setInt(9, dex.getBranchId());
			ps.executeUpdate();
		} catch (SQLException es) {
			es.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDailyIncome(DailyIncome dic) {
		String insertQuery = "INSERT INTO dailyincome(amount,submitted_user,entered_user,income_type,bill_code,bill_date,entered_date,"
				+ "submitted_date,branch_id) VALUES (?,?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
			ps.setDouble(1, (dic.getAmount()));
			ps.setInt(2, dic.getSubmittedUserid());
			ps.setInt(3, dic.getEnteredUserid());
			ps.setInt(4, dic.getIncomeType());
			ps.setString(5, dic.getBillCode());
			ps.setObject(6, dic.getBill_date());
			ps.setTimestamp(7, Timestamp.valueOf(dic.getEntered_time()));
			ps.setObject(8, dic.getSubmitted_date());
			ps.setInt(9, dic.getBranchId());
			ps.executeUpdate();
			
		} catch (SQLException es) {
			
			es.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		
	}
	public JSONArray getUserList() {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String userList = "select * from user";
		PreparedStatement ps;
		
		try {
			ps = ConnectionUtil.openConnection().prepareStatement(userList);
			ps.executeQuery();
			rs = ps.getResultSet();
			
			while (rs.next()){			
				 JSONObject userjsonObject=new JSONObject();
				
				 userjsonObject.put("id", rs.getString(1));
				 userjsonObject.put("username", rs.getString(2));
				
				 jsonArray.put(userjsonObject);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	public JSONArray getExpenseTypeList() {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String etList = "select * from expense_type";
		
		PreparedStatement ps;
		
		try {
			ps = ConnectionUtil.openConnection().prepareStatement(etList);
			ps.executeQuery();
			rs = ps.getResultSet();
			
			while (rs.next()){			
				 JSONObject etObj=new JSONObject();
				
				 etObj.put("et_id", rs.getString(1));
				 etObj.put("et_code", rs.getString(2));
				
				 jsonArray.put(etObj);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	
	public JSONArray getIncomeTypeList() {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String inList = "select * from income_type";
		
		PreparedStatement ps;
		
		try {
			ps = ConnectionUtil.openConnection().prepareStatement(inList);
			ps.executeQuery();
			rs = ps.getResultSet();
			
			while (rs.next()){			
				 JSONObject etObj=new JSONObject();
				
				 etObj.put("it_id", rs.getString(1));
				 etObj.put("it_code", rs.getString(2));
				
				 jsonArray.put(etObj);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public Double getTotalIncome(int branchId, LocalDateTime today) throws SQLException, Exception {
		ResultSet rs = null;
		Double totIncome = 0.00;
		String qry = "select sum(amount) as income from dailyincome where branch_id = ? and bill_date = ?";
		PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(qry);
		ps.setInt(1, branchId);
		ps.setTimestamp(2, Timestamp.valueOf(today));
		rs = ps.executeQuery();
		while (rs.next()) {
			totIncome = rs.getDouble(1);
		}
		return totIncome;
	}

	public Double getTotalExpences(int branchId, LocalDateTime today) throws SQLException, Exception{
		ResultSet rs = null;
		Double totExpense = 0.00;
		String qry = "select sum(amount) as expenses from daily_expences where branch_id = ? and billDate = ?";
		PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(qry);
		ps.setInt(1, branchId);
		ps.setTimestamp(2, Timestamp.valueOf(today));
		rs = ps.executeQuery();
		while (rs.next()) {
			totExpense = rs.getDouble(1);
		}
		return totExpense;
	}

}
