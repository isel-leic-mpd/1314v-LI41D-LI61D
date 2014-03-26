package isel.mpd.exceptionHandling;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;


public class TestExceptions extends TestCase {
	public void testExceptionSyntaxAndSemanthics() {
//		throw new Exception(); 
//		
//		Exception e = new Exception();
//		throw e;
//		
//		throw m();
		
		
	}

	public void testFileMapperExceptionStackTrace() {
		List l = new ArrayList<Number>();
	    List<String> ls = l;       // unchecked warning
	    l.add(0, new Integer(42)); // another unchecked warning
	    String s = ls.get(0);      // ClassCastException is thrown

		
		
		// Arrange
		FileMapper<Integer> fm = new FileMapper<>("");
		
		// Act 
		try {
			fm.anotherGetAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
