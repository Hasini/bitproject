package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.util.List;

import pro.bit.bitproject.domain.User;
import pro.bit.bitproject.domain.UserType;

public interface UserDAO{
	
	
	public User createUser (User user) throws SQLException, Exception;
	public User deleteUser(User user) throws SQLException,Exception;
	public String loginUser (String username,String password,int usertypeid) throws SQLException, Exception;
	//public UserType getUserTypeCode (String userTypeCode);
	public List<UserType> getUserTypeCode(int usertypeid);

}
