package isel.mpd.typesystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
		
		Field[] fields = getAllFields(o.getClass());
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
	
	
	public static <T> T bindTo(Class<T> klass, Map<String, Object> fieldsVals) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<T> c = klass.getConstructor();
		//T newT = klass.newInstance();
		T newT = c.newInstance();
		Field[] fields = getAllFields(klass);
		for (Field field : fields) {
			Object o = fieldsVals.get(field.getName());
			if(o != null) {
				field.setAccessible(true);
				field.set(newT, o);
			}
		}
		
		return newT;
	}

	public static <T> T bindToFieldsAndProps(Class<T> klass, Map<String, Object> vals) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		T newT = klass.newInstance();
		
		for( Map.Entry<String, Object> entry : vals.entrySet()) {
			Method m = getSetterMethod(klass, entry.getKey(), entry.getValue().getClass());
			if(m != null) {
				m.invoke(newT, entry.getValue());				
				continue;
			} 
			
			Field f = getField(klass, entry.getKey(), entry.getValue().getClass());
			if(f != null) {
				f.set(newT, entry.getValue());
			}
		}
		return newT;
	}
	
	private static Method getSetterMethod(Class<?> instanceClass, String key, Class<?> parameterClass) {
		for(Method m : instanceClass.getMethods()) {
			Class<?>[] parameterTypes = m.getParameterTypes();
			if(parameterTypes.length == 1  
				&& m.getName().equalsIgnoreCase("set" + key) 
				&& parameterTypes[0].isAssignableFrom(parameterClass)) {
				return m;
			}
		}
		return null;
	}
	
	private static Field getField(Class<?> instanceClass, String key, Class<?> parameterClass) {
		for(Field f : getAllFields(instanceClass)) {
			if(f.getName().equals(key) && f.getType().isAssignableFrom(parameterClass)) {
				f.setAccessible(true);
				return f;
			}
		}
		return null;
	}
	
	private static Field[] getAllFields(Class<?> c) {
		ArrayList<Field> allFields = new ArrayList<>();
		
		while(c != Object.class) {
			Field[] fields = c.getDeclaredFields();
			allFields.addAll(Arrays.asList(fields));
			
			c = c.getSuperclass();
		}
		
		return allFields.toArray(new Field[] {});
	}
}
