/**
 * 
 */
package pro.bit.bitproject.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Hasini
 *
 */
public class MySqlConnector {
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DBNAME = "bit_project";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	static final int INTERCONNECTION = 20;
	
	
	public static Connection getConnection() throws Exception {
		Connection con= null;
		try {
			Class.forName(DRIVER).newInstance();
			con=(Connection) DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	

}
