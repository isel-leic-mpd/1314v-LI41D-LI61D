/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import isel.mpd.tasklist.domain.entities.Task;

/**
 * @author lfalcao
 *
 */
public interface ITaskRepository {

	/**
	 * @param t
	 */
	void AddTask(Task t);

	/**
	 * @param id
	 * @return
	 */
	Task getTask(int id);

}
