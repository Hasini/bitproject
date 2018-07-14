package pro.bit.bitproject.domain;

public class UserType {
	private int userTypeId;
	private String userTypeCode;
	private String userTypeDescr;
	
	public UserType() {
		// TODO Auto-generated constructor stub
	}

	
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		
		this.userTypeId = userTypeId;
	}

	
	public String getUserTypeCode() {
		return userTypeCode;
	}
	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}

	
	public String getUserTypeDescr() {
		return userTypeDescr;
	}

	public void setUserTypeDescr(String userTypeDescr) {
		this.userTypeDescr = userTypeDescr;
	}
}
