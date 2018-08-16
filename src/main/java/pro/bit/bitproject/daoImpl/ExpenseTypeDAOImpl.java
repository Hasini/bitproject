package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.IncomeTypeDAO;
import pro.bit.bitproject.domain.ExpenseType;
import pro.bit.bitproject.domain.IncomeType;

public class ExpenseTypeDAOImpl implements IncomeTypeDAO {

	@Override
	public void createET(String code, String descr, LocalDateTime createddate) {
		String insertQuery = "INSERT INTO expense_type (et_code,et_descr,et_createdtime) VALUES (?,?,?)";
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
			ps.setString(1, code);
			ps.setString(2, descr);
			ps.setTimestamp(3, Timestamp.valueOf(createddate));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public IncomeType deleteIT(String coded) throws SQLException, Exception {
		PreparedStatement ps;
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("delete from expense_type where et_code = ?");
			ps.setString(1, coded);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONArray viewIT() throws SQLException, Exception {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String viewDet = "select * from expense_type";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(viewDet);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("code", rs.getString(2));
			 jsonObject.put("desc", rs.getString(3));
			 
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}

	@Override
	public ExpenseType updateET(ExpenseType et) throws SQLException, Exception {
		PreparedStatement ps;
		Character userstatus = 'U';
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("update expense_type set et_descr=?,et_status=?,et_updated_time=? where et_code=?");
			
			
			ps.setString(1, et.getExpensetypeDescr());
			ps.setString(2, Character.toString(userstatus));
			ps.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(4, et.getExpensetypecode());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public IncomeType updateIT(IncomeType it) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
