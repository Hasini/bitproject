package pro.bit.bitproject.dao;

import java.sql.SQLException;


import org.json.JSONArray;

import pro.bit.bitproject.domain.User;


public interface UserDAO{
	public User createUser (User user) throws SQLException, Exception;
	public String loginUser (String username,String password) throws SQLException, Exception;
	public JSONArray viewusertype () throws SQLException, Exception; 
	public void deleteUser (String username)throws SQLException;
	public String viewusertype(String userName) throws SQLException, Exception;
	void changepassword(String username, String password) throws SQLException, Exception;
}
