package isel.mpd.binding;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Binder {

	private final IBinderStrategy[] binderStrats;

	public Binder(IBinderStrategy... binderStrat) {
		this.binderStrats = binderStrat;
	}

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
	 * @exception IllegalArgumentException
	 *                if {@link o} is null.
	 */
	public static Map<String, Object> getFieldsValues(Object o)
			throws IllegalAccessException {
		if (o == null) {
			throw new IllegalArgumentException("o");
		}

		Field[] fields = FieldsBinder.getAllFields(o.getClass());
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

	public <T> T bindTo(Class<T> klass, Map<String, Object> vals)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		T newT = klass.newInstance();

		for (Map.Entry<String, Object> entry : vals.entrySet()) {
			for (IBinderStrategy bs : binderStrats) {
				if (bs.bindMember(newT, entry.getKey(), entry.getValue()))
					break;
			}
		}
		return newT;
	}

}
