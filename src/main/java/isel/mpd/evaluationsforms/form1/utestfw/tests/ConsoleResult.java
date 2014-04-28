/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw.tests;

import isel.mpd.evaluationsforms.form1.utestfw.AssertionFailedError;
import isel.mpd.evaluationsforms.form1.utestfw.ITestResult;
import isel.mpd.evaluationsforms.form1.utestfw.TestCase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lfalcao
 *
 */
public class ConsoleResult implements ITestResult {
	private static class TestResult implements Comparable<TestResult> {
		public enum State {
			STARTED("STARTED"), SUCCEED("SUCCEED"), ERROR("ERROR"), FAILURE("FAILURE");
			
			private String stateName;

			private State(String stateName) {
				this.stateName = stateName;
			}
			
			@Override
			public String toString() {
				return stateName;
			}
		}
		
		private static int NextOrderNumber = 0;
		
		int orderNumber;
		private TestCase testCase;
		private State testState = State.STARTED;
		private Throwable exception; 
		
		public TestResult(TestCase testCase) {
			this.testCase = testCase;
			orderNumber = ++NextOrderNumber;
		}

		/**
		 * 
		 */
		public void testSucceeded() {
			testState = State.SUCCEED;
		}

		/**
		 * @param e
		 */
		public void testFailed(AssertionFailedError e) {
			testState = State.FAILURE;
			exception = e;
			
		}

		/**
		 * @param e
		 */
		public void testError(Throwable e) {
			testState = State.ERROR;
			exception = e;
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(TestResult that) {
			return this.orderNumber - that.orderNumber;		
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(testCase.getName() + testState.toString());
			return sb.toString();
		}
	}
	
	
//	Set<TestResult> results = new TreeSet<TestResult>(
//			new Comparator<TestResult>() {
//		@Override
//		public int compare(TestResult o1, TestResult o2) {
//			return o1.orderNumber - o2.orderNumber;
//		}
//	});
	Map<String, TestResult> results = new HashMap<>();
	
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
		result.testSucceeded();
		

	}

	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#addFailure(isel.mpd.evaluationsforms.form1.utestfw.TestCase, isel.mpd.evaluationsforms.form1.utestfw.AssertionFailedError)
	 */
	@Override
	public void addFailure(TestCase testCase, AssertionFailedError e) {
		TestResult result = results.get(testCase.getName());
		result.testFailed(e);

	}

	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#addError(isel.mpd.evaluationsforms.form1.utestfw.TestCase, java.lang.RuntimeException)
	 */
	@Override
	public void addError(TestCase testCase, Throwable e) {
		TestResult result = results.get(testCase.getName());
		result.testError(e);
	}
	
	/* (non-Javadoc)
	 * @see isel.mpd.evaluationsforms.form1.utestfw.ITestResult#printReport()
	 */
	@Override
	public void printReport() {
		for(TestResult tr : getOrderedResults()) {
			System.out.println(tr);
		}
	}

	/**
	 * @return
	 */
	private TestResult[] getOrderedResults() {
		TestResult []res = results.values().toArray(new TestResult[results.size()]);
		Arrays.sort(res);
		return res;
	}

}
