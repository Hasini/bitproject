/**
 * 
 */
package pro.bit.bitproject.domain;

/**
 * @author Hasini
 *
 */
public class Branch {
	private int branchId;
	private String branchCode;
	private String branchDescr;
	
	
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getBranchDescr() {
		return branchDescr;
	}
	public void setBranchDescr(String branchDescr) {
		this.branchDescr = branchDescr;
	}
	
	

}
