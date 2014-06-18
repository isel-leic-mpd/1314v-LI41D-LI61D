/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import isel.mpd.tasklist.dataaccess.mappers.DataMapperException;

/**
 * @author lfalcao
 *
 */
public class RepositoryException extends Exception {

	/**
	 * @param e
	 */
	public RepositoryException(Throwable e) {
		super(e);
	}

}
