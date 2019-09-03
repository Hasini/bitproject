/**
 * 
 */
package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

/**
 * @author Hasini
 *
 */
public class IncomeType {
	private int incomeTypeId;
	private String incometypecode;
	private String incometypeDescr;
	private LocalDateTime createdtime;
	
	public int getIncomeTypeId() {
		return incomeTypeId;
	}
	public void setIncomeTypeId(int incomeTypeId) {
		this.incomeTypeId = incomeTypeId;
	}
	
	public String getIncometypecode() {
		return incometypecode;
	}
	public void setIncometypecode(String incometypecode) {
		this.incometypecode = incometypecode;
	}
	
	
	public String getIncometypeDescr() {
		return incometypeDescr;
	}
	public void setIncometypeDescr(String incometypeDescr) {
		this.incometypeDescr = incometypeDescr;
	}
	
	
	public LocalDateTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalDateTime createdtime) {
		this.createdtime = createdtime;
	}
	
	
	

}
