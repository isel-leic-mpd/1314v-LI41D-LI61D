/**
 *
 */
package isel.mpd.binding;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import isel.mpd.binding.formatters.Formatter;
import isel.mpd.binding.formatters.IFormatter;
import isel.mpd.typesystem.Primitives;
import isel.mpd.typesystem.util.SneakyUtils;

/**
 * @author lfalcao
 *
 */
public abstract class MemberBinder implements IBinderStrategy {
	Map<Object, IFormatter> formatters = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see isel.mpd.binding.IBinderStrategy#bindMember(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> boolean bindMember(T newT, String key, Object value) {
		value = format(newT, key, value);
		return bindMemberInternal(newT, key, value);
	}
	
	protected abstract <T> boolean bindMemberInternal(T newT, String key, Object value);
	
	
	private <T> Object format(T newT, String key, Object value) {
		IFormatter f;
		try {
			f = getFormmatter(newT.getClass(), key, value.getClass());
		} catch (InstantiationException | IllegalAccessException e) {
			f = null;
		}
		if(f != null) {
			value = f.format(value);
		}
		return value;
	}
	
	/**
	 * @param class1
	 * @param key
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private <T> IFormatter getFormmatter(Class<T> targetClass, String key, Class<?>valueClass) throws InstantiationException, IllegalAccessException {
		IFormatter f = null;
		if(formatters.containsKey(key)) {
			return formatters.get(getMemberKey(targetClass,key));
		}
		
		return createFormatter(targetClass, key, valueClass);
	}

	/**
	 * Create a new IFormmatter based on the Formatter annotarions ant puts 
	 * it in the formatters map
	 * @param fAnnotation the Formatter annotation
	 * @param key 
	 * @return  the created IFormatter associated wit the given annotation
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private <T> IFormatter createFormatter(final T newT, String key, Class<?>valueClass) throws InstantiationException, IllegalAccessException {
		Class<?> targetClass = newT.getClass();
		Formatter fAnnotation = (Formatter)getMember(targetClass, key).getAnnotation(Formatter.class);
		
		IFormatter f = null;
		final Method m = MemberBinder.getMethod(targetClass, key, valueClass);
		if(fAnnotation != null) {
			// Create formatter instance
			if(fAnnotation.formatterClass() != IFormatter.class) {
				f = fAnnotation.formatterClass().newInstance();
			} else {
				// Create formatter for method
				f = new IFormatter() {
					@Override
					public Object format(Object value) {
						try {
							return m.invoke(newT, value);
						} catch (IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException e) {
						}
						return value;
						
					}
				};
				
						
			}
			
		}
		formatters.put(getMemberKey(targetClass, key), f);
		return f;
		
	}
	
	/**
	 * @param targetClass
	 * @param key
	 * @return
	 */
	private <T> Object getMemberKey(Class<T> targetClass, String key) {
		return targetClass.getName()+key;
	}

	protected abstract <T> AccessibleObject getMember(Class<T> targetClass, String key);
	
	
	protected static Method getMethod(Class<?> instanceClass, String key,
			Class<?> parameterClass, String prefix) {
		for (Method m : instanceClass.getMethods()) {
			Class<?>[] parameterTypes = m.getParameterTypes();
			if (parameterTypes.length == 1
					&& m.getName().equalsIgnoreCase(prefix + key)
					&& Primitives.wrap(parameterTypes[0]).isAssignableFrom(
							parameterClass)) {
				return m;
			}
		}
		return null;
	}

	protected static Method getMethod(Class<?> instanceClass, String key,
            Class<?> parameterClass) {
		return getMethod(instanceClass, key, parameterClass, "");
	}
	

}
