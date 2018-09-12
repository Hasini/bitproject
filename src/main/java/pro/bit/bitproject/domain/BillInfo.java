package pro.bit.bitproject.domain;

import java.sql.Blob;
import java.time.LocalDateTime;

public class BillInfo {
	private int billId;
	private String billCode;
	private double billAmount;
	private int submittedUserId;
	private int enteredUserId;
	private LocalDateTime submitTime;
	private LocalDateTime enteredTime;
	private LocalDateTime billDate;
	private Blob billImage;
	
	public BillInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	
	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	
	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	
	public int getSubmittedUserId() {
		return submittedUserId;
	}

	public void setSubmittedUserId(int submittedUserId) {
		this.submittedUserId = submittedUserId;
	}

	
	public int getEnteredUserId() {
		return enteredUserId;
	}

	public void setEnteredUserId(int enteredUserId) {
		this.enteredUserId = enteredUserId;
	}

	
	public LocalDateTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}

	
	public LocalDateTime getEnteredTime() {
		return enteredTime;
	}

	public void setEnteredTime(LocalDateTime enteredTime) {
		this.enteredTime = enteredTime;
	}

	
	public LocalDateTime getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}

	
	public Blob getBillImage() {
		return billImage;
	}

	public void setBillImage(Blob billImage) {
		this.billImage = billImage;
	}
	
	
	
	
	
	

}
