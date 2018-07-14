package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

public class ExpenseType {
	private String expensetypecode;
	private String expensetypeDescr;
	private LocalDateTime createdtime;
	
	public ExpenseType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getExpensetypecode() {
		return expensetypecode;
	}
	public void setExpensetypecode(String expensetypecode) {
		this.expensetypecode = expensetypecode;
	}
	
	public String getExpensetypeDescr() {
		return expensetypeDescr;
	}
	public void setExpensetypeDescr(String expensetypeDescr) {
		this.expensetypeDescr = expensetypeDescr;
	}
	
	public LocalDateTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalDateTime createdtime) {
		this.createdtime = createdtime;
	}
	
	
	

}
