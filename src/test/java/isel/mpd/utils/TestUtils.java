package isel.mpd.misc;

import junit.framework.TestCase;
import junit.framework.Assert;

public class TestUtils extends TestCase {
	  public void testGetInUpperCaseWithValidString() {
		Assert.assertEquals("saying in uppercase " + "slb".toUpperCase(), Utils.getInUppercase("slb"));
	  }
	  
	  public void testGetInUpperCaseWithNullString() {
		Assert.assertEquals("saying in uppercase " + "slb".toUpperCase(), Utils.getInUppercase(null));
	  }
	  
}
