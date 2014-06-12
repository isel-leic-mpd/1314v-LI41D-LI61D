/**
 *
 */
package isel.mpd.tasklist.dataaccess.mappers;

import java.sql.SQLException;

/**
 * @author lfalcao
 *
 */
public class DataMapperException extends Exception {

	/**
	 * @param e
	 */
	public DataMapperException(Throwable e) {
		super(e);
	}

	/**
	 * @param string
	 */
	public DataMapperException(String message) {
		super(message);
	}
	
}
