/**
 * 
 */
package pro.bit.bitproject.daoImpl;


import java.sql.PreparedStatement;
import java.sql.SQLException;


import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.UserTypeDAO;
import pro.bit.bitproject.domain.UserType;

/**
 * @author hasini
 *
 */
public class UserTypeDAOImpl implements UserTypeDAO{

	public void createUserType(UserType usertype) throws SQLException {
		String insertQuery = "INSERT INTO usertype(iduser_type,codeuser_type,descruser_type) VALUES (?,?,?)";
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
			ps.setString(1, String.valueOf(usertype.getUserTypeId()));
			ps.setString(2, usertype.getUserTypeCode());
			ps.setString(3, usertype.getUserTypeDescr());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
