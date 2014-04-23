/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw;

/**
 * @author lfalcao
 *
 */
public interface ITestResult {

	/**
	 * 
	 */
	public void printReport();

	/**
	 * @param testCase
	 */
	public void startTest(TestCase testCase);

	/**
	 * @param testCase
	 */
	public void endTest(TestCase testCase);

	/**
	 * @param testCase
	 * @param e
	 */
	public void addFailure(TestCase testCase, AssertionFailedError e);

	/**
	 * @param testCase
	 * @param e
	 */
	public void addError(TestCase testCase, RuntimeException e);
}
