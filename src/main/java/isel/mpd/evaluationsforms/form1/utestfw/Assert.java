/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw;

/**
 * @author lfalcao
 *
 */
public class Assert {

	/**
	 * @param i
	 * @param add
	 * @throws AssertionFailedError 
	 */
	public static void assertEquals(Object o1, Object o2) throws AssertionFailedError {
		if(!o1.equals(o2))
			throw new AssertionFailedError(o1 + " is noe equal to " + o2);
		
	}
	
	public static void assertEquals(int o1, int o2) throws AssertionFailedError {
		if(o1 != o2)
			throw new AssertionFailedError(o1 + " is noe equal to " + o2);
		
	}

}
