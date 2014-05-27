/**
 *
 */
package isel.mpd.iterablequeries;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author lfalcao
 *
 */
public interface Queryable<T> extends Iterable<T> {

	Queryable<T> filter(Predicate<? super T> pred);
	<R> Queryable<R> map(Function<? super T,? extends R> pred);
	void forEach(Consumer<? super T> consumer);
	Queryable<T> skip(int i);
	
	

}
