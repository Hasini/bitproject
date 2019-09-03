/**
 * 
 */
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

/**
 * @author Hasini
 *
 */
public class IncomeTypeDAOImpl implements IncomeTypeDAO{

	public void createIT(String code, String descr, LocalDateTime createddate) {
		String insertQuery = "INSERT INTO income_type (it_code,it_descr,createddate) VALUES (?,?,?)";
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
	public IncomeType updateIT(IncomeType it) throws SQLException, Exception {
		PreparedStatement ps;
		Character userstatus = 'U';
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("update income_type set it_descr=?,it_status=?,it_updated_time=? where it_id=?");
			ps.setString(1, it.getIncometypeDescr());
			ps.setString(2, Character.toString(userstatus));
			ps.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
			ps.setInt(4, it.getIncomeTypeId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IncomeType deleteIT(int incomeTypeId){
		PreparedStatement ps;
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("delete from income_type where it_id = ?");
			ps.setInt(1, incomeTypeId);
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
		String viewIncomeType = "select * from income_type";
		PreparedStatement ps;
		ps = ConnectionUtil.openConnection().prepareStatement(viewIncomeType);
		ps.executeQuery();
		rs = ps.getResultSet();
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("id", rs.getInt(1));
			 jsonObject.put("code", rs.getString(2));
			 jsonObject.put("desc", rs.getString(3));
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}

	@Override
	public ExpenseType updateET(ExpenseType et) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createET(String code, String descr, LocalDateTime createddate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONArray viewET() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
