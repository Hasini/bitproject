package pro.bit.bitproject.dao;

import java.sql.SQLException;

import org.json.JSONArray;

import pro.bit.bitproject.domain.Branch;

	public interface BranchDAO {
		public Branch createBranch (Branch branch) throws SQLException,Exception;
		public JSONArray viewBranchDetails() throws SQLException, Exception;
		public Branch updateBranch (Branch branch) throws SQLException, Exception;
		public Branch deleteBranch (int id) throws SQLException, Exception;
		JSONArray viewBranchDetailsWithCode(int branchId) throws SQLException, Exception;
		
	}
