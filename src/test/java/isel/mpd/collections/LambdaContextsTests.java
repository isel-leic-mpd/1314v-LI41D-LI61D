/**
 *
 */
package isel.mpd.collections;

import static org.junit.Assert.*;
import isel.mpd.typesystem.Car;

import java.util.function.Supplier;

import org.junit.Test;

/**
 * @author lfalcao
 *
 */
public class LambdaContextsTests {

	private <T> T callSupplier(Supplier<T> supplier) {
		return supplier.get();
	}
	
	
	@Test
	public void testLambdasWithoutLocalContext() {
		
		// Act
		String s = callSupplier(() -> "SLB  - 3!!!!");
		
		// Assert
		assertEquals("SLB  - 3!!!!", s);
		
	}
	
	@Test
	public void testLambdasWithLocalContext() {
		// Arrange 
		final Car originalCar = new Car("myCar", "Mini", 2010);
		int originalYear = originalCar.getYear();
		
		// Act
		Car c = callSupplier(() -> originalCar);
		
		originalCar.setYear(2011);
		
		// Assert
		assertEquals(originalYear, c.getYear());
		
	}

}
