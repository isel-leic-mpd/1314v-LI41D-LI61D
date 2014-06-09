/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * @author lfalcao
 *
 */
public class JDBCTests {
	final String connectionUrl = 
			"jdbc:sqlserver://AGUIA\\SQLEXPRESS:1433;" +
			"databaseName=Northwind;" + 
			"user=mpduser;password=mpd";
	private Connection conn;
	
	@Before
	public void setupTest() throws SQLException {
//		SQLServerDataSource ds = new SQLServerDataSource();
//      ds.setUser("mpduser");
//      ds.setPassword("mpd");
//      ds.setServerName("AGUIA");
//      //ds.setPortNumber(1433);
//      ds.setInstanceName("SQLEXPRESS");
//      ds.setDatabaseName("Northwind");
		
		conn = DriverManager.getConnection(connectionUrl);
	}
	
	@After
	public void teardownTest() throws SQLException {
		conn.close();
	}
	
	@Test
	public void shouldBeConnectedToDatabase() throws SQLException {
		// Arrange
		
		
		// Act
		
		// Assert
		assertFalse(conn.isClosed());
	}
	
	
	@Test
	public void shouldExecuteASelectStatement() throws SQLException {
		// Arrange
		PreparedStatement stmt = conn.prepareStatement("select ProductId  from Products where UnitPrice > ?");
		stmt.setInt(1, 20);
		
		
		// NEVER USE!!!!
		// Statement stmt1 = conn.createStatement();
		//stmt.executeQuery("select * from Products where UnitPrice = " + unitPrice);
		
		
		// Act
		ResultSet res = stmt.executeQuery();
		
		// Assert
		assertTrue(res.next());
		assertTrue(res.getInt("ProductId") > 0);
		
		
	}

}
