package pro.bit.bitproject.domain;


import java.time.LocalDateTime;

public class CashBook {
	 private int cashbookId;
	 private int branchId;
	 private int customerId;
	 private double billAmount;
	 private double paidAmount;
	 private LocalDateTime paiddate;
	 private String custcashbookId;
	 private String paytype;
	 private int user;
	 private double extrapayment;
	 
	public int getCashbookId() {
		return cashbookId;
	}
	public void setCashbookId(int cashbookId) {
		this.cashbookId = cashbookId;
	}
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	public LocalDateTime getPaiddate() {
		return paiddate;
	}
	public void setPaiddate(LocalDateTime paiddate) {
		this.paiddate = paiddate;
	}
	
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	
	public String getCustcashbookId() {
		return custcashbookId;
	}
	public void setCustcashbookId(String custcashbookId) {
		this.custcashbookId = custcashbookId;
	}
	
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	public double getExtrapayment() {
		return extrapayment;
	}
	public void setExtrapayment(double extrapayment) {
		this.extrapayment = extrapayment;
	}
	
	
}
