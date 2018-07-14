/**
 * 
 */
package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.UserDAO;
import pro.bit.bitproject.domain.User;



/**
 * @author hasini
 *
 */
public class UserDAOImpl implements UserDAO {
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
	public String loginUser(String username,String password,int usertypeid) throws Exception {
		String sts="false"; 
		String loginQuery = "SELECT * FROM user WHERE user.username = ? AND user.password = ? AND user.usertypeid = ? AND user.userstatus NOT IN ('D')";
		PreparedStatement ps;
		ResultSet rs;
			try {
				ps = ConnectionUtil.openConnection().prepareStatement(loginQuery);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, String.valueOf(usertypeid));
				//ps.setString(4, String.valueOf(user.getUserstatus()));
				
				rs= ps.executeQuery();
				System.out.println(rs);
				
				if(rs.next() == false){
					//rs.getString(userstatus);
					System.out.println("false");
				}else {
					sts = "true";
				}
			    rs.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return sts;
	}


	public JSONArray viewusertype() throws SQLException, Exception {

		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String viewUsertype = "select * from user_type";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(viewUsertype);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			
			 jsonObject.put("id", rs.getString(1));
			 jsonObject.put("descr", rs.getString(3));
			 
			 
			 System.out.println("id"+rs.getString(1));
			 System.out.println("descr"+rs.getString(3));
			 jsonArray.put(jsonObject);
			
			
		}
		rs.close();
		return jsonArray;
	}

	@Override
	public void deleteUser(String username) throws SQLException {
		PreparedStatement ps;
		Character userstatus = 'D';
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("update user set userstatus=?,updatedtime=? where username=?");
			
			
			ps.setString(1, Character.toString(userstatus));
			ps.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(3, username);
		
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public void changepassword(String username ,String password) throws SQLException {
		PreparedStatement ps;
		Character userstatus = 'U';
		try {
			ps = ConnectionUtil.openConnection().prepareStatement("update user set userstatus=?,updatedtime=?,password=? where username=?");
			
			
			ps.setString(1, Character.toString(userstatus));
			ps.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(3, password);
			ps.setString(4, username);
		
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}

