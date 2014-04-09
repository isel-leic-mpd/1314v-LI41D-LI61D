/**
 *
 */
package isel.mpd.typesystem;

import static org.junit.Assert.*;
import isel.mpd.binding.Binder;
import isel.mpd.binding.FieldsBinder;
import isel.mpd.binding.FormattersBinder;
import isel.mpd.binding.ToUppercaseBinder;
import isel.mpd.binding.formatters.ToUpperCaseFormatter;

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
	public void beforeAllTests() {
		
	}
	
	
	@Before
	public void beforeEacheTest() {
		
	}
	
	@Test
	public void testToUppercaseformatter() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		// Arrange
		Car v = new Car("name", "brand", 2000);
		Map<String, Object> fieldsVals = Binder.getFieldsValues(v);

		// Act
		Car c = new Binder(new FormattersBinder(new FieldBinders(), new ToUpperCaseFormatter()));
					.bindTo(Car.class, fieldsVals);

		
		// Assert
		assertEquals(v.getName().toUpperCase(), c.getName());
		assertEquals(v.getBrand().toUpperCase(), c.getBrand());
	}

	
	@Test
	public void test1() {
		fail("Not yet implemented");
	}

}
