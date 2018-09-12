package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.AccessRightDAO;
import pro.bit.bitproject.domain.AccessRight;

public class AccessRightDAOImpl implements AccessRightDAO{

	@Override
	public AccessRight grantAccesslevels(AccessRight ar) throws SQLException {
		String insertQuery = "INSERT INTO programs (program_name,program_code,access_level,created_time) VALUES (?,?,?,?)";
		PreparedStatement ps;
		
			try {
				ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
				ps.setString(1, ar.getProgramCode());
				ps.setString(2, ar.getProgramDescr());
				ps.setInt(3, ar.getAccessLevel());
				ps.setTimestamp(4, Timestamp.valueOf(ar.getCreatedTime()));
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		return null;
		
	}

}
