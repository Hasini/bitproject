package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

public class AccessRight {
	private String ProgramId;
	private String ProgramCode;
	private String ProgramDescr;
	private int accessLevel;
	private LocalDateTime createdTime;
	
	
	public AccessRight() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProgramId() {
		return ProgramId;
	}
	public void setProgramId(String programId) {
		ProgramId = programId;
	}
	
	public String getProgramCode() {
		return ProgramCode;
	}
	public void setProgramCode(String programCode) {
		ProgramCode = programCode;
	}
	
	public int getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	
	public String getProgramDescr() {
		return ProgramDescr;
	}
	public void setProgramDescr(String programDescr) {
		ProgramDescr = programDescr;
	}
	
	
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	

	
}
