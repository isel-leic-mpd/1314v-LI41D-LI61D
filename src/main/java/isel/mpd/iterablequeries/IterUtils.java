/**
 *
 */
package isel.mpd.iterablequeries;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author lfalcao
 *
 */
public class IterUtils {
	/**
	 * @author lfalcao
	 *
	 */
	public static class IterUtilsQueryable<T> implements Queryable<T> {

		/**
		 * @param elems
		 */
		public IterUtilsQueryable(Collection<T> elems) {
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see java.lang.Iterable#iterator()
		 */
		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#filter(java.util.function.Predicate)
		 */
		@Override
		public Queryable<T> filter(Predicate<? super T> pred) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#map(java.util.function.Function)
		 */
		@Override
		public <R> Queryable<R> map(Function<? super T, ? extends R> pred) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#forEach(java.util.function.Consumer)
		 */
		@Override
		public void forEach(Consumer<? super T> consumer) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#skip(int)
		 */
		@Override
		public Queryable<T> skip(int i) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static <T> Queryable<T> query(Collection<T> elems) {
		return new IterUtilsQueryable<T>(elems);
	}
}
