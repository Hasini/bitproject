/**
 * 
 */
package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		String insertQuery = "INSERT INTO branch_det (branch_code,branch_descr) VALUES (?,?)";
		PreparedStatement ps;
		try {
			ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
			ps.setString(1, branch.getBranchCode());
			ps.setString(2, branch.getBranchDescr());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public Branch updateBranch(String branchdescr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Branch deleteBranch(String branchdescr) {
		// TODO Auto-generated method stub
		return null;
	}

}
