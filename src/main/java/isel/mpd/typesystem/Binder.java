package isel.mpd.typesystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Binder {
	/**
	 * Returns a map with all field names and corresponding values in a
	 * {@code Map}
	 * 
	 * @param o
	 *            - The object to return its fields as a pair in the returned
	 *            map.
	 * @return the map with the field name an value pairs
	 * @throws IllegalAccessException
	 * 
	 * @exception IllegalArgumentException  if {@link o} is null.
	 */
	public static Map<String, Object> getFieldsValues(Object o)
			throws IllegalAccessException {
		if (o == null) {
			throw new IllegalArgumentException("o");
		}

		Field[] fields = getAllFields(o.getClass());
		Map<String, Object> m = new HashMap<String, Object>();

		for (Field field : fields) {
			int modifiers = field.getModifiers();
			if (!Modifier.isStatic(modifiers)) {
				field.setAccessible(true);
				m.put(field.getName(), field.get(o));
			}
		}

		return m;
	}

	public static <T> T bindTo(Class<T> klass, Map<String, Object> fieldsVals)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		
		// Refactor this code to extract common code with bindTo
		Constructor<T> c = klass.getConstructor();
		// T newT = klass.newInstance();
		T newT = c.newInstance();
		for (Map.Entry<String, Object> entry : fieldsVals.entrySet()) {
			Object o = entry.getValue();
			Field field = getField(klass, entry.getKey(), o.getClass());
			
			if (field != null) {
				if (isAssignable(field.getType(), o.getClass())) {
					field.setAccessible(true);
					field.set(newT, o);
				}
			}
		}

		return newT;
	}

	
	public static <T> T bindToFieldsAndProps(Class<T> klass,
			Map<String, Object> vals) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		T newT = klass.newInstance();

		for (Map.Entry<String, Object> entry : vals.entrySet()) {
			// Refactor this code to extract common code with bindTo  
			Object val = entry.getValue();
			Class<?> valType = val.getClass();
			Method m = getSetterMethod(klass, entry.getKey(), valType);
			
			
			if (m != null && isAssignable(m.getParameterTypes()[0], valType)) {
				m.invoke(newT, val);
				continue;
			}

			Field f = getField(klass, entry.getKey(), valType);
			if (f != null && isAssignable(f.getType(), valType)) {
				f.set(newT, val);
			}
		}
		return newT;
	}

	private static boolean isAssignable(Class<?> dstType,	Class<?> srcType) {
		return Primitives.wrap(dstType).isAssignableFrom(srcType);
	}
	
	private static Method getSetterMethod(Class<?> instanceClass, String key,
			Class<?> parameterClass) {
		for (Method m : instanceClass.getMethods()) {
			Class<?>[] parameterTypes = m.getParameterTypes();
			if (parameterTypes.length == 1
					&& m.getName().equalsIgnoreCase("set" + key)
					&& isAssignable(parameterTypes[0], parameterClass)) {
				return m;
			}
		}
		return null;
	}

	private static Field getField(Class<?> instanceClass, String key,
			Class<?> parameterClass) {
		for (Field f : getAllFields(instanceClass)) {
			if (f.getName().equals(key)
					&& isAssignable(f.getType(), parameterClass)) {
				f.setAccessible(true);
				return f;
			}
		}
		return null;
	}

	private static Field[] getAllFields(Class<?> c) {
		ArrayList<Field> allFields = new ArrayList<>();

		while (c != Object.class) {
			Field[] fields = c.getDeclaredFields();
			allFields.addAll(Arrays.asList(fields));

			c = c.getSuperclass();
		}

		return allFields.toArray(new Field[] {});
	}
}
