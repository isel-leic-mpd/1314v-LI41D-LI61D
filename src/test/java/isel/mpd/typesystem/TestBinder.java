package isel.mpd.typesystem;

import isel.mpd.typesystem.Binder;
import isel.mpd.typesystem.Car;
import isel.mpd.typesystem.Vehicle;

import java.lang.reflect.InvocationTargetException;
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
	
	
	
	public void testFieldsBinder()
			throws IllegalAccessException {
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = new FieldsBinder().bindTo(Car.class, fieldsVals);
		
		Car c = new PropertiesBinder().bindTo(Car.class, fieldsVals);
		
		Car c = new PropertiesAndFieldsBinder().bindTo(Car.class, fieldsVals);

		// Act
		Map<String, Object> m = Binder.getFieldsValues(v);

		// Assert
		assertEquals(3, m.size());
		assertEquals("name", m.get("name"));
		assertEquals("brand", m.get("brand"));
		assertEquals(2000, m.get("year"));

	}
	
	public void testBindToForCarValues() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = Binder.bindTo(Car.class, fieldsVals);
		
		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(v.getYear(), c.getYear());
		

		
		
		
		
		
		
	}
	
	
	public void testTypeCompatibilities() throws ClassNotFoundException {
		Car c = new Car("name", "brand", 2000);
		
		Class<?> c1 = Class.forName("isel.mpd.typesystem.Vehicle");
		
		assertSame(c1, c.getClass().getSuperclass());
		assertSame(c1, Vehicle.class);
		assertTrue(Vehicle.class.isInstance(c));
		assertTrue(c instanceof Vehicle);
		assertTrue(Vehicle.class.isAssignableFrom(c.getClass()));
		assertTrue(Vehicle.class.isAssignableFrom(Car.class));
		
		assertSame(int.class, Integer.TYPE);
		assertNotSame(int.class, Integer.class);
	}
}
