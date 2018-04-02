package pro.bit.bitproject.dao;

import pro.bit.bitproject.domain.Branch;

public interface BranchDAO {
	public Branch createBranch (Branch branch) throws Exception;
	public Branch updateBranch (String branchdescr);
	public Branch deleteBranch (String branchdescr);
	

}
