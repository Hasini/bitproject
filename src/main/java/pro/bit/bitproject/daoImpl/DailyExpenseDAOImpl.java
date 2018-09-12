package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.IncomeExpenseDAO;


public class DailyExpenseDAOImpl implements IncomeExpenseDAO{

	@Override
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

	@Override
	public JSONArray getExpenseorIncomeTypeList() {
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

	@Override
	public void createRecord(int type, int billId, double amount, int submittedUserId, int enteredUserId) {
		// TODO Auto-generated method stub
		
	}

}
