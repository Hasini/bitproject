/**
 * 
 */
package pro.bit.bitproject.common;

import java.sql.Connection;
import pro.bit.bitproject.common.MySqlConnector;;



/**
 * @author hasini
 *
 */
public class ConnectionUtil {
	private static final ThreadLocal<Connection> connectionPool = new ThreadLocal<Connection>();
    
	static{
    	int x=0;
    	while(x++<=MySqlConnector.INTERCONNECTION){
    		try {
				Connection con=MySqlConnector.getConnection();	
				if(con.isValid(0)){
					connectionPool.set(con);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    }
    
    public static synchronized Connection openConnection() throws Exception{
    	Connection conn;
    	conn=connectionPool.get();
    	if(conn==null){ 
			conn=MySqlConnector.getConnection();	
			if(conn.isValid(0))
				connectionPool.set(conn);
						    		
    	}else if (!conn.isValid(0)){
    		connectionPool.remove();
       	 	Connection con=MySqlConnector.getConnection();
       	 	if(con.isValid(0))
				connectionPool.set(con);
       	 	try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
       	 	conn=con;
    	}
    	
    	return conn;
    	
    } 
    
    public static void closeConnection() throws Exception{
    	Connection conn;
    	conn=connectionPool.get();
    	if(conn!=null){ 
			conn.close();
			connectionPool.set(null);			    		
    	}	
    }
    	

}
