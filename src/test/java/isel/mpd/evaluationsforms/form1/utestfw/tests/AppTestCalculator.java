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

class TestSub extends TestCase {
	public TestSub() {
		super("Test Sub function");
	}

	@Override
	public void runTest() throws AssertionFailedError {
		Assert.assertEquals(-3, Calculator.sub(3, 7));
	}
}

@Expect(ArithmeticException.class)
class TestDiv1 extends TestCase {
	public TestDiv1() {
		super("Test Div1 function");
	}

	@Override
	public void runTest() {
		Calculator.div(3, 0);
	}
}


class TestDiv2 extends TestCase {
	public TestDiv2() {
		super("Test Div2 function");
	}

	@Override
	public void runTest() {
		Calculator.div(3, 0);
	}
}

public class AppTestCalculator {
	public static void main(String[] args) {
		TestRunner tr = new TestRunner(new ConsoleResult());
//		tr.doRun(new TestAdd());
//		tr.doRun(new TestSub());
//		tr.doRun(new TestDiv1());
		tr.doRun(new TestDiv2());
		tr.printReport();
	}
}