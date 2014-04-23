/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw;

/**
 * @author lfalcao
 *
 */
public abstract class TestCase implements ITest {
	private String testName;

	public TestCase(String testName) {
		this.testName = testName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITest#getName()
	 */
	@Override
	public String getName() {
		return testName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * isel.mpd.evaluationsforms.form1.utestfw.ITest#run(isel.mpd.evaluationsforms
	 * .form1.utestfw.ITestResult)
	 */
	@Override
	public void run(ITestResult result) {
		try {
			result.startTest(this);
			this.runTest();
			result.endTest(this);
		} catch (AssertionFailedError e) {
			result.addFailure(this, e);
		} catch (RuntimeException e) {
			result.addError(this, e);
			throw e;
		}
	}

	/**
	 * 
	 */
	protected abstract void runTest() throws AssertionFailedError;

}
