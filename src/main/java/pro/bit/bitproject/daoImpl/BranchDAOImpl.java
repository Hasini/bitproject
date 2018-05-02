/**
 * 
 */
package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
		String updateQuery = "Update branch_det set (branch_descr , created_time) VALUES (?,?) where branch_code = ?";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(updateQuery);
		ps.setString(2, branch.getBranchCode());
		ps.setTimestamp(3, Timestamp.valueOf(branch.getCreatedTime()));
		ps.executeUpdate();
		return null;
	}

	@Override
	public Branch deleteBranch(String code) throws SQLException, Exception {
		String delQuery = "delete * from branch_det where branch_code = ?";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(delQuery);
		ps.setString(2, code);
		ps.executeUpdate();
		return null;
	}

	@Override
	public List<Object> viewBranchDetails() throws SQLException, Exception {
		ResultSet rs = null;
        List<Object> list = new ArrayList<>();
        
		String viewBranchDet = "select * from branch_det";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(viewBranchDet);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){
			list.add(rs.getInt(1));
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			
		}
		rs.close();
		
		return list;
	}

}
