/**
 * 
 */
package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.BranchDAO;
import pro.bit.bitproject.domain.Branch;

/**
 * @author Hasini
 *
 */
public class BranchDAOImpl implements BranchDAO {
	
	@Override
	public Branch createBranch(Branch branch) throws Exception {

		String insertQuery = "INSERT INTO branch_det (branch_code,branch_descr,created_time) VALUES (?,?,?)";
		PreparedStatement ps;
		
			ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
			ps.setString(1, branch.getBranchCode());
			ps.setString(2, branch.getBranchDescr());
			ps.setTimestamp(3, Timestamp.valueOf(branch.getCreatedTime()));
			ps.executeUpdate();

		return null;
	}

	@Override
	public Branch updateBranch(Branch branch) throws SQLException, Exception {
		//String updateQuery = ;
		//String updateQuery = "update branch_det set (branch_code,branch_descr,created_time) VALUES (?,?,?) ";
		PreparedStatement ps;
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("update branch_det set branch_descr=?,created_time=? where branch_code=?");
			
			
			ps.setString(1, branch.getBranchDescr());
			ps.setTimestamp(2, Timestamp.valueOf(branch.getCreatedTime()));
			ps.setString(3, branch.getBranchCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public Branch deleteBranch(String coded,String descrd) throws SQLException, Exception {
		//String delQuery = "delete branch_det where branch_code = ?";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement("delete from branch_det where branch_code = ?");
		ps.setString(1, coded);
		ps.executeUpdate();
		return null;
	}

	@Override
	public JSONArray viewBranchDetails() throws SQLException, Exception {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String viewBranchDet = "select * from branch_det";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(viewBranchDet);
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

}
