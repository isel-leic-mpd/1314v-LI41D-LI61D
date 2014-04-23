/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw.tests;

import isel.mpd.evaluationsforms.form1.utestfw.Assert;
import isel.mpd.evaluationsforms.form1.utestfw.AssertionFailedError;
import isel.mpd.evaluationsforms.form1.utestfw.TestCase;
import isel.mpd.evaluationsforms.form1.utestfw.TestRunner;

/**
 * @author lfalcao
 *
 */
class TestAdd extends TestCase {
	public TestAdd() {
		super("Test Add function");
	}

	@Override
	public void runTest() throws AssertionFailedError {
		Assert.assertEquals(7, Calculator.add(3, 4));
	}
}

class TestSum extends TestCase {
	public TestSum() {
		super("Test Sub function");
	}

	@Override
	public void runTest() throws AssertionFailedError {
		Assert.assertEquals(-3, Calculator.sub(3, 7));
	}
}

public class AppTestCalculator {
	public static void main(String[] args) {
		TestRunner tr = new TestRunner(new ConsoleResult());
		tr.doRun(new TestAdd());
		tr.doRun(new TestSum());
		tr.printReport();
	}
}