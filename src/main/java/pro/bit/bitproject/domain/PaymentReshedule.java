package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

public class PaymentReshedule {
	private int lend_reschdule_id;
	private int cash_arrears_id;
	private int mode;
	private double modepayment;
	private String custcashbookid;
	private int numberOfPaidIns;
	private LocalDateTime createdtime;
	private LocalDateTime updatedtime;
	private String status;
	
	public int getLend_reschdule_id() {
		return lend_reschdule_id;
	}
	public void setLend_reschdule_id(int lend_reschdule_id) {
		this.lend_reschdule_id = lend_reschdule_id;
	}
	
	public int getCash_arrears_id() {
		return cash_arrears_id;
	}
	public void setCash_arrears_id(int cash_arrears_id) {
		this.cash_arrears_id = cash_arrears_id;
	}
	
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public double getModepayment() {
		return modepayment;
	}
	public void setModepayment(double modepayment) {
		this.modepayment = modepayment;
	}
	
	public String getCustcashbookid() {
		return custcashbookid;
	}
	public void setCustcashbookid(String custcashbookid) {
		this.custcashbookid = custcashbookid;
	}
	
	public int getNumberOfPaidIns() {
		return numberOfPaidIns;
	}
	public void setNumberOfPaidIns(int numberOfPaidIns) {
		this.numberOfPaidIns = numberOfPaidIns;
	}
	
	public LocalDateTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalDateTime createdtime) {
		this.createdtime = createdtime;
	}
	
	public LocalDateTime getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(LocalDateTime updatedtime) {
		this.updatedtime = updatedtime;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
