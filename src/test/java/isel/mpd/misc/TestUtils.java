package isel.mpd.misc;

import junit.framework.TestCase;

public class TestUtils extends TestCase {
	  public void testGetInUpperCaseWithValidString() {
		assertEquals("saying in uppercase " + "slb".toUpperCase(), Utils.getInUppercase("slb"));
	  }
	  
	  public void testGetInUpperCaseWithNullString() {
		assertEquals("saying in uppercase " + "slb".toUpperCase(), Utils.getInUppercase(null));
	  }
	  
}
