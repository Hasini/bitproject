package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

public class DailyIncome {
	
	private int incomeId;
	private double amount;
    private int submittedUserid;
    private int enteredUserid;
    private LocalDateTime createdTime;
    private String remark;
    private int incomeType;
    private int billCode;
    
    
    
	public DailyIncome() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getSubmittedUserid() {
		return submittedUserid;
	}
	public void setSubmittedUserid(int submittedUserid) {
		this.submittedUserid = submittedUserid;
	}
	
	public int getEnteredUserid() {
		return enteredUserid;
	}
	public void setEnteredUserid(int enteredUserid) {
		this.enteredUserid = enteredUserid;
	}
	
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public int getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(int incomeType) {
		this.incomeType = incomeType;
	}
	
	public int getBillCode() {
		return billCode;
	}
	public void setBillCode(int billCode) {
		this.billCode = billCode;
	}
    
    
    
}
