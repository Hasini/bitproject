package pro.bit.bitproject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyIncome {
	
	private int incomeId;
	private double amount;
    private int submittedUserid;
    private int enteredUserid;
    private LocalDateTime entered_time;
    private int incomeType;
    private String billCode;
    private String submitted_date;
    private String bill_date;
    private int branchId;
    
    
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
	
	public LocalDateTime getEntered_time() {
		return entered_time;
	}

	public void setEntered_time(LocalDateTime entered_time) {
		this.entered_time = entered_time;
	}

	public String getSubmitted_date() {
		return submitted_date;
	}

	public void setSubmitted_date(String submitted_date) {
		this.submitted_date = submitted_date;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public int getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(int incomeType) {
		this.incomeType = incomeType;
	}
	
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
    
    
    
}
