package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

public class LendingShedule {
	private int lend_sch_id;
	private int cash_arrears_id;
	private int mode;
	private double modepayment;
	private String custcashbookid;
	private int numberOfPaidIns;
	private LocalDateTime createdtime;
	private LocalDateTime updatedtime;
	private String status;
	
	public LendingShedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public int getLend_sch_id() {
		return lend_sch_id;
	}
	public void setLend_sch_id(int lend_sch_id) {
		this.lend_sch_id = lend_sch_id;
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

	
	public LocalDateTime getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(LocalDateTime createdtime) {
		this.createdtime = createdtime;
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
