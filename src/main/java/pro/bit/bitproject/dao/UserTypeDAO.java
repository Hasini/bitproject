/**
 * 
 */
package pro.bit.bitproject.dao;

import java.sql.SQLException;

import pro.bit.bitproject.domain.UserType;

/**
 * @author hasini
 *
 */
public interface UserTypeDAO {
	public void createUserType(UserType usertype) throws SQLException;

}
