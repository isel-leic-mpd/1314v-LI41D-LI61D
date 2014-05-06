package isel.mpd.binding;

import isel.mpd.typesystem.Primitives;
import isel.mpd.typesystem.util.SneakyUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class PropertiesBinder extends MemberBinder {

	
    private static Method getSetterMethod(Class<?> instanceClass, String key,
            Class<?> parameterClass) {
    	return getMethod(instanceClass, key, parameterClass, "set");
        
    }
    

    @Override
    protected <T> boolean bindMemberInternal(T newT, String key, Object val) {
        Class<?> valType = val.getClass();
        Method m = getSetterMethod(newT.getClass(), key, valType);

        if (m != null && Primitives.wrap(m.getParameterTypes()[0]).isAssignableFrom(valType)) {
            try {
                m.invoke(newT, val);
                return true;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                // throw new RuntimeException(ex);
                SneakyUtils.throwAsRTException(ex);
            }
        }
        return false;
    }

	/* (non-Javadoc)
	 * @see isel.mpd.binding.MemberBinder#getMember(java.lang.Class, java.lang.String)
	 */
	@Override
	protected <T> AccessibleObject getMember(Class<T> targetClass, String key) {
		return null;
	}
}
