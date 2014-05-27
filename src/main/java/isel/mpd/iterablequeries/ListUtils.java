/**
 *
 */
package isel.mpd.iterablequeries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author lfalcao
 *
 */
public final class ListUtils {

	/**
	 * @author lfalcao
	 *
	 */
	private static class ListUtilsQueryable<T> implements Queryable<T> {

		private final Collection<T> elems;

		/**
		 * @param elems
		 */
		public ListUtilsQueryable(Collection<T> elems) {
			this.elems = elems;
		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#filter(java.util.function.Predicate)
		 */
		@Override
		public Queryable<T> filter(Predicate<? super T> pred) {
			List<T> result = new ArrayList<>();
			for(T t : elems) {
				if(pred.test(t)) {
					result.add(t);
				}
			}
			
			return new ListUtilsQueryable<>(result);
		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#map(java.util.function.Function)
		 */
		@Override
		public <R> Queryable<R> map(Function<? super T, ? extends R> mapper) {
			List<R> result = new ArrayList<>();
			for(T t : elems) {
				result.add(mapper.apply(t));
			}
			return new ListUtilsQueryable<>(result);
		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#forEach(java.util.function.Consumer)
		 */
		@Override
		public void forEach(Consumer<? super T> consumer) {
			for(T t : elems) {
				consumer.accept(t);
			}
			
		}

		/* (non-Javadoc)
		 * @see isel.mpd.iterablequeries.Queryable#skip(int)
		 */
		@Override
		public Queryable<T> skip(int i) {
			List<T> result = new ArrayList<>();
			Iterator<T> iter = elems.iterator();
			
			for(;i > 0; --i) {
				iter.next();
			}
			
			while(iter.hasNext()) {
				result.add(iter.next());
			}
			
			return new ListUtilsQueryable<>(result);
		}

		/* (non-Javadoc)
		 * @see java.lang.Iterable#iterator()
		 */
		@Override
		public Iterator<T> iterator() {
			return elems.iterator();
		}

	}

	private ListUtils() { }
	
	/**
	 * @param elems
	 * @return
	 */
	public static <T> Queryable<T> query(Collection<T> elems) {
		return new ListUtilsQueryable<T>(elems);
	}

}
