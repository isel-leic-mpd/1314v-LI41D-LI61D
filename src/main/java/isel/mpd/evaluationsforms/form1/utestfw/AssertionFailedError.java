/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw;

/**
 * @author lfalcao
 *
 */
public class AssertionFailedError extends Exception {
	/**
	 * @param string
	 */
	public AssertionFailedError(String msg) {
		super(msg);
	}
	
	public AssertionFailedError(String msg, Throwable cause) {
		super(msg, cause);
	}

}
