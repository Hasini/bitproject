/**
 * 
 */
package pro.bit.bitproject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Hasini
 *
 */
public class Branch {
	private int branchId;
	private String branchCode;
	private String branchDescr;
	private LocalDateTime createdTime;
	private LocalDateTime updatedDate;		
	
	
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
	
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createddate) {
		this.createdTime = createddate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
}
