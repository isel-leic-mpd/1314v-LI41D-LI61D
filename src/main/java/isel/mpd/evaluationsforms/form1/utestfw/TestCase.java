/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw;

import java.util.HashMap;
import java.util.Map;

import isel.mpd.evaluationsforms.form1.utestfw.tests.Expect;

/**
 * @author lfalcao
 *
 */
public abstract class TestCase implements ITest {
	private String testName;
	
	private static Map<Class<? extends TestCase>, Class<? extends Throwable>> expectedExceptions = new HashMap<>();

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
		} catch (Throwable e) {
			if(e.getClass().isAssignableFrom(getExpectedException())) {
				result.endTest(this);
			} else {
				result.addError(this, e);
				throw e;
			}
		}
	}

	/**
	 * @return
	 */
	private Class<? extends Throwable> getExpectedException() {
		if(expectedExceptions.containsKey(this.getClass())) {
			return expectedExceptions.get(this.getClass());
		}
		
		Class<? extends Throwable> expClass;
		Expect e = this.getClass().getAnnotation(Expect.class);
		expClass = e == null ? null : e.value();
		expectedExceptions.put(this.getClass(), expClass);

		return expClass;
	}

	/**
	 * 
	 */
	protected abstract void runTest() throws AssertionFailedError;

}
