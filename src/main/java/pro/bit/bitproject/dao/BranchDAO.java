package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.util.List;

import pro.bit.bitproject.domain.Branch;

	public interface BranchDAO {
		public Branch createBranch (Branch branch) throws Exception;
		public List<Object> viewBranchDetails() throws SQLException, Exception;
		public Branch updateBranch (Branch branch) throws SQLException, Exception;
		public Branch deleteBranch (String code) throws SQLException, Exception;
		
	}
