/**
 * 
 */
package pro.bit.bitproject.common;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

/**
 * @author JIT
 *
 */
public class MySqlConnector {
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DBNAME = "bit_project";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = " ";
	static final int INTERCONNECTION = 20;
	
	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER).newInstance();
		return (Connection) DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
		
	}
	

}
