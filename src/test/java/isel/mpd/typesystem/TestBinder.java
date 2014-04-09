package isel.mpd.typesystem;

import isel.mpd.binding.Binder;
import isel.mpd.binding.FieldsBinder;
import isel.mpd.binding.NonNullFieldsBinder;
import isel.mpd.binding.PropertiesBinder;
import isel.mpd.binding.ToUppercaseBinder;

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
	
	
	
	public void testBindToForCarValues() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = new Binder(new FieldsBinder()).bindTo(Car.class, fieldsVals);
		
		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(v.getYear(), c.getYear());
		assertEquals(v.getAge(), c.getAge());
	}

	
	
	public void testBindToFieldsAndPropsForCarValues() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);
		fieldsVals.remove("year");
		fieldsVals.put("age", 10);

		// Act
		Car c = new Binder(new PropertiesBinder(), new FieldsBinder()).bindTo(Car.class, fieldsVals);
		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(10, c.getAge());
		assertEquals(v.getYear()+v.getAge()-10, c.getYear());
	}

	public void testBindToFieldsAndPropsPriorityForProperties() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);
		fieldsVals.put("year", -1);

		// Act
		try {
			new Binder(new  PropertiesBinder(), new FieldsBinder()).bindTo(Car.class, fieldsVals);
		} catch(InvocationTargetException e) {
			if(e.getCause() instanceof IllegalArgumentException)
				return;
		}
		
		// Assert
		fail("InvocationTargetException should have been thrown");
	}

	
	
	public void testFieldBinder() throws IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);
		fieldsVals.put("year", -1);
		

		// Act
		Car c = new Binder(new FieldsBinder()).bindTo(Car.class, fieldsVals);
		
		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(-1, c.getYear());
		assertEquals(v.getYear() + v.getAge() + 1, c.getAge());
	}
	
        public void testNonNullFieldBinder() throws IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car(null, "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);
		fieldsVals.put("year", -1);
		

		// Act
		Car c = new Binder(new NonNullFieldsBinder()).bindTo(Car.class, fieldsVals);
		
		
		// Assert
		assertEquals("default", c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(-1, c.getYear());
		assertEquals(v.getYear() + v.getAge() + 1, c.getAge());
	}
	
	public void testPropertiesBinder() throws IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2004);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);
		fieldsVals.put("age", 10);

		// Act
		Car c = new Binder(new PropertiesBinder()).bindTo(Car.class, fieldsVals);
		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(10, c.getAge());
		assertEquals(v.getYear()+v.getAge()-10, c.getYear());
	}
	
	
	public void testPropertiesAndFieldsBinder() throws Exception {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);
		fieldsVals.remove("year");
		fieldsVals.put("age", 10);

		// Act
		Car c = new Binder(new  PropertiesBinder(), new FieldsBinder()).bindTo(Car.class, fieldsVals);
		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(10, c.getAge());
		assertEquals(v.getYear()+v.getAge()-10, c.getYear());
	}

	
	public void testPropertiesAndFieldsBinderPriorityForProperties() throws IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);
		fieldsVals.put("year", -1);

		// Act
		try {
			new Binder (new  PropertiesBinder(), new FieldsBinder()).bindTo(Car.class, fieldsVals);
		} catch(Exception e) {
			if(e.getCause() instanceof IllegalArgumentException)
				return;
		}
		
		// Assert
		fail("InvocationTargetException should have been thrown");
	}
	
	
	public void testToUppercaseBinderStrategy() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = new Binder(new ToUppercaseBinder(new FieldsBinder()))
					.bindTo(Car.class, fieldsVals);

		
		// Assert
		assertEquals(v.getName().toUpperCase(), c.getName());
		assertEquals(v.getBrand().toUpperCase(), c.getBrand());
	}
}
