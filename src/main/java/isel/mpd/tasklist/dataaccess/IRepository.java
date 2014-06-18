/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import isel.mpd.tasklist.domain.entities.User;

import java.util.Collection;

/**
 * @author lfalcao
 *
 */
public interface IRepository<T> {
	Collection<T> getAll() throws RepositoryException;
	void add(T t);
	
	void update(T t);

	T getById(int id) throws RepositoryException;

}
