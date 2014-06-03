/**
 *
 */
package isel.mpd.tasklist.domain.logic;

import isel.mpd.tasklist.dataaccess.ITaskRepository;
import isel.mpd.tasklist.domain.entities.Task;
import isel.mpd.tasklist.domain.entities.User;

/**
 * @author lfalcao
 *
 */
public class TaskManager {

	private ITaskRepository tasksRepository;

	
	public TaskManager(ITaskRepository tasksRepo) {
		tasksRepository = tasksRepo;
		
	}


	/**
	 * @param tASK_TITLE
	 * @param tASK_DESCRIPTION
	 * @param defaultUser
	 * @return
	 */
	public Task createTask(String title, String description,
			User creator) {
		if(title == null || "".equals(title)) {
			throw new IllegalArgumentException("Task title may not be null");
		}
		if(creator == null) {
			throw new IllegalArgumentException("Tasks should have a creator");
		}
		
		Task t = new Task(title, description, creator);
		tasksRepository.AddTask(t);
		
		return t;

	}

	
	/**
	 * @param string
	 * @param object
	 * @return 
	 */
	public Task createTask(String title, User creator) {
		return createTask(title, null, creator);
	}

	/**
	 * @param id
	 * @return
	 */
	public Task getTask(int id) {
		return tasksRepository.getTask(id);
	}



}
