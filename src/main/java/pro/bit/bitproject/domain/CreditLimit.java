package pro.bit.bitproject.domain;

public class CreditLimit {
	private int customerType;
	private Long creditLimit;
	
	public CreditLimit() {
		
	}

	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}

	public long getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(long creditLimit) {
		this.creditLimit = creditLimit;
	}
}
