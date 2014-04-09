package isel.mpd.typesystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * This class is a replica from Primitives class from Guava library
 * @see http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/primitives/Primitives.html
 * 
 * This class was implemented here just for aducational purposes. 
 * @author lfalcao
 *
 *
 */
public final class Primitives {
	private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS;
	
	static {
		Map<Class<?>, Class<?>> primitiveToWrappers	= new HashMap<Class<?>, Class<?>>();
		primitiveToWrappers.put(boolean.class, Boolean.class);
		primitiveToWrappers.put(byte.class, Byte.class);
		primitiveToWrappers.put(char.class, Character.class);
		primitiveToWrappers.put(double.class, Double.class);
		primitiveToWrappers.put(float.class, Float.class);
		primitiveToWrappers.put(int.class, Integer.class);
		primitiveToWrappers.put(long.class, Long.class);
		primitiveToWrappers.put(short.class, Short.class);
		primitiveToWrappers.put(void.class, Void.class);
		
		PRIMITIVES_TO_WRAPPERS = Collections.unmodifiableMap(primitiveToWrappers);
	}
	    
	
	private Primitives() { }
	
	/**
	 * @return Returns an immutable set of all nine primitive types (including void).
	 */
	public static Set<Class<?>> allPrimitiveTypes() {
		return PRIMITIVES_TO_WRAPPERS.keySet();
	}
	
	/**
	 * 
	 * @return Returns an immutable set of all nine primitive-wrapper types (including Void).
	 */
	public static Set<Class<?>> allWrapperTypes() {
		return new HashSet<Class<?>>(PRIMITIVES_TO_WRAPPERS.values());
	}
	
	/**
	 * @param type the type to evaluate if it is a wrapper type.
	 * @return Returns true if type is one of the nine primitive-wrapper types, such as Integer.
	 */
	static boolean	isWrapperType(Class<?> type) {
		return PRIMITIVES_TO_WRAPPERS.containsValue(type);
	}
	
	/**
	 * 
	 * @param type
	 * @return Returns the corresponding primitive type of type if it is a wrapper type; otherwise returns type itself.
	 */
	public static Class<?>	unwrap(Class<?> type) {
		Set<Entry<Class<?>, Class<?>>> entrySet = PRIMITIVES_TO_WRAPPERS.entrySet();
		for (Entry<Class<?>, Class<?>> entry : entrySet) {
			if(entry.getValue() == type)
				return entry.getKey();
		}
		return type;
	}
	
	public static Class<?>	wrap(Class<?> type) {
		return type.isPrimitive() ? PRIMITIVES_TO_WRAPPERS.get(type) : type;
	}
}
