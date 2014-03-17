package isel.mpd.typesystem;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Binder {
	/**
	 * Returns a map with all field names and corresponding values in a {@code Map} 
	 * 
	 * @param o - The object to return its fields as a pair in the returned map.
	 * @return the map with the field name an value pairs
	 * @throws IllegalAccessException 
	 * 
	 * @exception IllegalArgumentException if {@link o} is null.
	 */
	public static Map<String, Object> getFieldsValues(Object o) throws IllegalAccessException {
		if(o == null) {
			throw new IllegalArgumentException("o");
		}
		
		Field[] fields = getAllFields(o);
		Map<String, Object> m = new HashMap<String, Object>();
		
		for (Field field : fields) {
			int modifiers = field.getModifiers();
			if(!Modifier.isStatic(modifiers)) {
				field.setAccessible(true);
				m.put(field.getName(), field.get(o));
			}
		}
		
		return m;
	}

	private static Field[] getAllFields(Object o) {
		ArrayList<Field> allFields = new ArrayList<>();
		Class<?> c = o.getClass();
		while(c != Object.class) {
			Field[] fields = c.getDeclaredFields();
			allFields.addAll(Arrays.asList(fields));
			
			c = c.getSuperclass();
		}
		
		return allFields.toArray(new Field[] {});
	}
}
