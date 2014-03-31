package isel.mpd.typesystem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class FieldsBinder implements BinderStrategy{

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

    public static Field[] getAllFields(Class<?> c) {
        ArrayList<Field> allFields = new ArrayList<>();

        while (c != Object.class) {
            Field[] fields = c.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));

            c = c.getSuperclass();
        }

        return allFields.toArray(new Field[]{});
    }

    @Override
    public <T> boolean bindMember(T newT, String key, Object value) {
            Class fieldKlass = value == null? null : value.getClass();
            Field field = getField(newT.getClass(), key, fieldKlass);

            if (field != null) {
                if (isAssignable(field.getType(), fieldKlass)) {
                    try {
                        field.setAccessible(true);
                        field.set(newT, value);
                        return true;
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            return false;

    }
    
    protected static boolean isAssignable(Class<?> dstType, Class<?> srcType) {
        if(srcType == null) return true;
	return Primitives.wrap(dstType).isAssignableFrom(srcType);
    }

}
