/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import isel.mpd.evaluationsforms.form1.utestfw.AssertionFailedError;
import isel.mpd.evaluationsforms.form1.utestfw.ITestResult;
import isel.mpd.evaluationsforms.form1.utestfw.TestCase;

/**
 * @author lfalcao
 *
 */
public class ConsoleResult implements ITestResult {
	private static class TestResult {
		private static int NextOrderNumber = 0;
		int orderNumber;
		private TestCase testCase;
		
		public TestResult(TestCase testCase) {
			this.testCase = testCase;
			orderNumber = ++NextOrderNumber;
		}
	}
	
	
	Map<String, TestResult> results = new TreeMap<>();
	
	
	
	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#startTest(isel.mpd.evaluationsforms.form1.utestfw.TestCase)
	 */
	@Override
	public void startTest(TestCase testCase) {
		results.put(testCase.getName(), new TestResult(testCase));

	}

	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#endTest(isel.mpd.evaluationsforms.form1.utestfw.TestCase)
	 */
	@Override
	public void endTest(TestCase testCase) {
		TestResult result = results.get(testCase.getName());

	}

	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#addFailure(isel.mpd.evaluationsforms.form1.utestfw.TestCase, isel.mpd.evaluationsforms.form1.utestfw.AssertionFailedError)
	 */
	@Override
	public void addFailure(TestCase testCase, AssertionFailedError e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#addError(isel.mpd.evaluationsforms.form1.utestfw.TestCase, java.lang.RuntimeException)
	 */
	@Override
	public void addError(TestCase testCase, RuntimeException e) {
		// TODO Auto-generated method stub

	}
	
	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#printReport()
	 */
	@Override
	public void printReport() {
		// TODO Auto-generated method stub

	}

}
