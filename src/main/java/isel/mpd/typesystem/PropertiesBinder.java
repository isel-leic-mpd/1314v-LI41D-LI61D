package isel.mpd.typesystem;

import isel.mpd.typesystem.util.SneakyUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertiesBinder implements BinderStrategy{

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

    @Override
    public  <T> boolean bindMember(T newT, String key, Object val) {
        Class<?> valType = val.getClass();
        Method m = getSetterMethod(newT.getClass(), key, valType);

        if (m != null && isAssignable(m.getParameterTypes()[0], valType)) {
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
    
    protected static boolean isAssignable(Class<?> dstType, Class<?> srcType) {
	return Primitives.wrap(dstType).isAssignableFrom(srcType);
    }

}
