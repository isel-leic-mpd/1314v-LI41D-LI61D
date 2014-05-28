/**
 *
 */
package isel.mpd.iterablequeries;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author lfalcao
 *
 */
public final class IterUtils {
	/**
	 * @author lfalcao
	 *
	 */
	
	private IterUtils() { }
	
	public static class IterUtilsQueryable<T> implements Queryable<T> {

		private Iterator<T> it;

		/**
		 * @param elems
		 */
		public IterUtilsQueryable(Collection<T> elems) {
			this.it = elems.iterator();
		}

		public IterUtilsQueryable(Iterator<T> iter) {
			this.it = iter;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Iterable#iterator()
		 */
		@Override
		public Iterator<T> iterator() {
			return it;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * isel.mpd.iterablequeries.Queryable#filter(java.util.function.Predicate
		 * )
		 */
		@Override
		public Queryable<T> filter(Predicate<? super T> pred) {
			return new IterUtilsQueryable<T>(new Iterator<T>() {
				T next;
				boolean containsNext = false;
				@Override
				public boolean hasNext() {
					if(containsNext)
						return true;
					while(it.hasNext()) {
						T aux = it.next();
						if(pred.test(aux)) {
							next = aux;
							return containsNext = true;
						}
					}
					return false;
				}

				@Override
				public T next() {
					if(containsNext || hasNext()) {
						containsNext = false;
						return next;
					}
					throw new NoSuchElementException();
				}
				
			});
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * isel.mpd.iterablequeries.Queryable#map(java.util.function.Function)
		 */
		@Override
		public <R> Queryable<R> map(Function<? super T, ? extends R> map) {
			return new IterUtilsQueryable<R>(new Iterator<R>() {

				@Override
				public boolean hasNext() {
					return it.hasNext();
				}

				@Override
				public R next() {
					return map.apply(it.next());
				}
			});
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * isel.mpd.iterablequeries.Queryable#forEach(java.util.function.Consumer
		 * )
		 */
		@Override
		public void forEach(Consumer<? super T> consumer) {
			while(it.hasNext()) {
				consumer.accept(it.next());
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see isel.mpd.iterablequeries.Queryable#skip(int)
		 */
		@Override
		public Queryable<T> skip(int i) {
			
			return new IterUtilsQueryable<T>(new Iterator<T>() {
				int count = i;
				@Override
				public boolean hasNext() {
					while (it.hasNext() && count > 0) {
						--count;
						it.next();
					}
					return it.hasNext();
				}

				@Override
				public T next() {
					if(!hasNext())
						throw new NoSuchElementException();
					return it.next();
				}
			});
		}
	}
	
	public static <T> Queryable<T> query(Collection<T> elems) {
		return new IterUtilsQueryable<T>(elems);
	}


}
