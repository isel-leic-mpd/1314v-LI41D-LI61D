/**
 *
 */
package isel.mpd.typesystem;

import static org.junit.Assert.*;
import isel.mpd.binding.Binder;
import isel.mpd.binding.CompositeBinder;
import isel.mpd.binding.FieldsBinder;
import isel.mpd.binding.FormattersBinder;
import isel.mpd.binding.PropertiesBinder;
import isel.mpd.binding.ToUppercaseBinder;
import isel.mpd.binding.formatters.ToUpperCaseFormatter;
import isel.mpd.binding.formatters.TrimFormatter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author lfalcao
 *
 */
public class TestFormatters {

	@BeforeClass
	public static void beforeAllTests() {
		
	}
	
	
	@Before
	public void beforeEacheTest() {
		
	}
	
	@Test
	public void testFormattersBinder() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("  name  ", "  brand	", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = new Binder(new FormattersBinder(
				new CompositeBinder(new FieldsBinder(), 
				new PropertiesBinder()), new ToUpperCaseFormatter(), 
				new TrimFormatter()))
					.bindTo(Car.class, fieldsVals);

		
		// Assert
		assertEquals(v.getName().toUpperCase().trim(), c.getName());
		assertEquals(v.getBrand().toUpperCase().trim(), c.getBrand());
	}
	
	
	@Test
	public void testFormatterAnnotationInFields() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange - 
		Car v = new Car("  name  ", "  brand  ", "	model  ",  2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = new Binder(new FieldsBinder()).bindTo(Car.class, fieldsVals);

		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(v.getModel().toUpperCase().trim(), c.getModel());
	}
	
	@Test
	public void testFormatterAnnotationInMethods() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange - Check if the 
		Car v = new Car("  name  ", "  brand  ", "	model  ",  2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = new Binder(new PropertiesBinder()).bindTo(Car.class, fieldsVals);

		
		// Assert
		assertEquals(v.getName(), c.getName());
		assertEquals(v.getBrand(), c.getBrand());
		assertEquals(v.getModel().trim().trim(), c.getModel());
	}
}
