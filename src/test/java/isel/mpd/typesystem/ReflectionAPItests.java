package isel.mpd.typesystem;

import java.lang.reflect.Field;

import junit.framework.TestCase;

public class ReflectionAPItests extends TestCase {
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
	
	
	public void testIfMemberInstancesAreCopies() throws ClassNotFoundException {
		// Arrange
		Field []fields = Car.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
		}
		
		
		// Act 
		Field []fieldsCopies = Car.class.getDeclaredFields();
		
		// Assert
		assertTrue(fieldsCopies.length > 0);
		for (int i = 0; i < fields.length; ++i) {
			assertNotSame(fieldsCopies[i], fields[i]);
			assertEquals(fieldsCopies[i].getName(), fields[i].getName());
			assertFalse(fieldsCopies[i].isAccessible() == fields[i].isAccessible());
			assertFalse(fieldsCopies[i].isAccessible() == fields[i].isAccessible());
		}
	}
}





