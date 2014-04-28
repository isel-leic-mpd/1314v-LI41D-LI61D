/**
 *
 */
package isel.mpd.evaluationsforms.form1.utestfw.tests;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lfalcao
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Expect {
	Class<? extends Exception> value();
}
