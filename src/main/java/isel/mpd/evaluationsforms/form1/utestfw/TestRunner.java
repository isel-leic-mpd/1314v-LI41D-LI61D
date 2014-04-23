/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw;

/**
 * @author lfalcao
 *
 */
public class TestRunner {
	private final ITestResult result;

	public TestRunner(ITestResult view) {
		this.result = view;
	}

	public void doRun(ITest test) {
		test.run(result);
	}

	public void printReport() {
		result.printReport();
	}
}
