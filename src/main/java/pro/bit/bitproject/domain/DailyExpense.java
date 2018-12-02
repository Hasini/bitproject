package pro.bit.bitproject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyExpense {
	
	private int expenseId;
	private double amount;
    private int submittedUserid;
    private int enteredUserid;
    private LocalDateTime createdTime;
    private String remarks;
    private int expenseType;
    private String billCode;
    private LocalDate submittedDate;
    private LocalDate billDate;
    private int customerId;
    
    
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(int expenseType) {
		this.expenseType = expenseType;
	}
	
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public LocalDate getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}
	public LocalDate getBillDate() {
		return billDate;
	}
	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	
	
	
}
