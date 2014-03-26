package isel.mpd.exceptionHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class SqlMapper<T> implements IMapper<T> {

	private String connectionString;

	SqlMapper(String connectionString) {
		this.connectionString = connectionString;
		
	}
	
	@Override
	public Collection<T> getAll() {
		try {
			return getAllNew();
		} catch (MapperException e) {
			// This is a poor solution, but it's the best one when we don't have access 
			// to the base class or interface source code and still want yo use it.  
			throw new RuntimeException(e);
		}
	}

	@Override
	public Collection<T> getAllNew() throws MapperException {
		try {
			// This is dummy code just to illustrate exception handling
			FileInputStream fs = new FileInputStream("");
			DriverManager.getConnection(connectionString);
		} catch (IOException | SQLException e) {
			throw new MapperException(e);
		}
		return null;

	}

}
