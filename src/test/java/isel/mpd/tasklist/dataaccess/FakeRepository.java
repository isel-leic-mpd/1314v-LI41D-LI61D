/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import isel.mpd.tasklist.domain.entities.Task;

/**
 * @author lfalcao
 *
 */
public class FakeRepository implements ITaskRepository {
	Task task;
	
	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.ITaskRepository#AddTask(isel.mpd.tasklist.domain.entities.Task)
	 */
	@Override
	public void AddTask(Task t) {
		task = t;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.ITaskRepository#getTask(int)
	 */
	@Override
	public Task getTask(int id) {
		return task;
	}

}
