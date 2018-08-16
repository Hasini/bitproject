/**
 * 
 */
package pro.bit.bitproject.dao;

import java.sql.SQLException;

import org.json.JSONArray;

import pro.bit.bitproject.domain.CustomerRegistration;

/**
 * @author Hasini
 *
 */
public interface CustomerRegistrationDAO {
	
	public CustomerRegistration createCus(CustomerRegistration cus);
	JSONArray viewBranches() throws SQLException, Exception;
	public boolean checkNIC(String nic) throws SQLException, Exception;
	public JSONArray viewAllUsers()throws SQLException, Exception;
}
