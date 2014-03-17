package isel.mpd.typesystem;

import isel.mpd.typesystem.Binder;
import isel.mpd.typesystem.Car;
import isel.mpd.typesystem.Vehicle;

import java.util.Map;

import junit.framework.TestCase;

public class TestBinder extends TestCase {
	public void testGetFieldValuesForNullArgument() throws IllegalAccessException	{
		// Arrange
		

		// Act
		try {
			Map<String, Object> m = Binder.getFieldsValues(null);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("IllegalArgumentException should be thrown");

		// Assert
		
	}
	
	
	public void testGetFieldValuesForDefaultVeichle()
			throws IllegalAccessException {
		// Arrange
		Vehicle v = new Vehicle();

		// Act
		Map<String, Object> m = Binder.getFieldsValues(v);

		// Assert
		assertEquals(2, m.size());
		assertEquals("default", m.get("name"));
		assertEquals("default", m.get("brand"));

	}
	
	
	public void testGetFieldValuesForCar()
			throws IllegalAccessException {
		// Arrange
		Vehicle v = new Car("name", "brand", 2000);

		// Act
		Map<String, Object> m = Binder.getFieldsValues(v);

		// Assert
		assertEquals(3, m.size());
		assertEquals("name", m.get("name"));
		assertEquals("brand", m.get("brand"));
		assertEquals(2000, m.get("year"));

	}

}
