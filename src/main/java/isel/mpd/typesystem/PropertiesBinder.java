package isel.mpd.typesystem;

import static isel.mpd.typesystem.Primitives.wrap;
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
                    && wrap(parameterTypes[0]).isAssignableFrom(parameterClass)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public  <T> boolean bindMember(T newT, String key, Object val) {
        Class<?> valType = val.getClass();
        Method m = getSetterMethod(newT.getClass(), key, valType);

        if (m != null && wrap(m.getParameterTypes()[0]).isAssignableFrom(valType)) {
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
