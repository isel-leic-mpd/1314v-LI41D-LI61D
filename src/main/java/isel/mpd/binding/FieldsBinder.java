package isel.mpd.binding;

import static isel.mpd.typesystem.Primitives.wrap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class FieldsBinder implements IBinderStrategy{

    private static Field getField(Class<?> instanceClass, String key,
            Class<?> parameterClass) {
        for (Field f : getAllFields(instanceClass)) {
            if (f.getName().equals(key)
                    && wrap(f.getType()).isAssignableFrom(parameterClass)) {
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
                if (wrap(field.getType()).isAssignableFrom(fieldKlass)) {
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
    
}
