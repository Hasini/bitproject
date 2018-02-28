package pro.bit.bitproject.dao;

import java.util.List;

import pro.bit.bitproject.domain.UserType;

public interface UserDAO {
	
	
	//public UserType getUserTypeCode (String userTypeCode);
	public List<UserType> getUserTypeCode();

}
