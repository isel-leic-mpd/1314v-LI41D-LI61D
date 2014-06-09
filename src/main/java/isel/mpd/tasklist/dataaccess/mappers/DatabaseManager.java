/**
 *
 */
package isel.mpd.tasklist.dataaccess.mappers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author lfalcao
 *
 */
public class DatabaseManager {
	final String connectionUrl = 
			"jdbc:sqlserver://AGUIA\\SQLEXPRESS:1433;" +
			"databaseName=Northwind;" + 
			"user=mpduser;password=mpd";
	
	Connection connection;
	
	
	DatabaseManager() throws SQLException {
		connection = DriverManager.getConnection(connectionUrl);
	}
	
	/**
	 * @param selectString
	 * @return 
	 * @throws SQLException 
	 */
	public PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
		return connection.prepareStatement(sqlQuery);
		
	}

}
