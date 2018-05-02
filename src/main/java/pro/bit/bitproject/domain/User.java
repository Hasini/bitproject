/**
 * 
 */
package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

/**
 * @author hasini
 *
 */
public class User {
	private int userid;
	private String username;
	private String password;
	private int usertypeid;
	private LocalDateTime createdtime;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public int getUsertypeid() {
		return usertypeid;
	}
	public void setUsertypeid(int usertypeid) {
		this.usertypeid = usertypeid;
	}


	public LocalDateTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalDateTime createdtime) {
		this.createdtime = createdtime;
	}
	
	
	

}
