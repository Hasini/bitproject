/**
 * 
 */
package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.UserDAO;
import pro.bit.bitproject.domain.User;
import pro.bit.bitproject.domain.UserType;

/**
 * @author hasini
 *
 */
public class UserDAOImpl implements UserDAO {

	public UserType getUserTypeCode(String userTypeCode) {
		
		return null;
	}

//	public List<UserType> getUserTypeCode() throws SQLException {
//		String viewQuery = "SELECT descruser_typecol from usertype where usertypeid = ?";
//		
//		PreparedStatement ps;
//		ps = ConnectionUtil.openConnection().prepareStatement(viewQuery);
//		ps.executeUpdate();
//		return null;
//	}

	@Override
	public User createUser(User user) throws Exception{
		String insertQuery = "INSERT INTO user (username,password,usertypeid,createdtime) VALUES (?,?,?,?)";
			
		PreparedStatement ps;
		ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getUsertypeid());
		ps.setTimestamp(4, Timestamp.valueOf(user.getCreatedtime()));
		ps.executeUpdate();
			
		return null;
			
	}

	@Override
	public List<UserType> getUserTypeCode(int usertypeid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginUser(String username, String password, int usertypeid) throws Exception {
		String sts="false"; 
		String loginQuery = "SELECT * FROM user WHERE user.username = ? AND user.password = ? AND user.usertypeid = ?";
		PreparedStatement ps;
		ResultSet rs;
		
		
			try {
				ps = ConnectionUtil.openConnection().prepareStatement(loginQuery);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, String.valueOf(usertypeid));
				
				rs= ps.executeQuery();
				
				if(rs.next()== false){
					System.out.println("ffffffffffff");
				}else{
					sts = "true";
				    
			    }
			    rs.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return sts;
	}

	@Override
	public User deleteUser(User user) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
