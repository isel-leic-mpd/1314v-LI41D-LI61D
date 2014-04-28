package isel.mpd.binding.formatters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Formatter {
	Class<?> formatterClass() default Void.class;
	String formatterMethod() default "";
}
