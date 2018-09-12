package pro.bit.bitproject.dao;

import java.sql.SQLException;

import pro.bit.bitproject.domain.AccessRight;

public interface AccessRightDAO {
	public AccessRight grantAccesslevels(AccessRight ar) throws SQLException;
}
