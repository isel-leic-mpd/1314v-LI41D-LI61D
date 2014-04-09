package isel.mpd.binding;

import isel.mpd.typesystem.Primitives;
import isel.mpd.typesystem.util.SneakyUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class PropertiesBinder implements IBinderStrategy{

    private static Method getSetterMethod(Class<?> instanceClass, String key,
            Class<?> parameterClass) {
        for (Method m : instanceClass.getMethods()) {
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (parameterTypes.length == 1
                    && m.getName().equalsIgnoreCase("set" + key)
                    && Primitives.wrap(parameterTypes[0]).isAssignableFrom(parameterClass)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public  <T> boolean bindMember(T newT, String key, Object val) {
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
    
}
