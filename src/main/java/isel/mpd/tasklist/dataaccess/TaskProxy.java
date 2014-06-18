/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import isel.mpd.tasklist.domain.entities.Task;
import isel.mpd.tasklist.domain.entities.User;

/**
 * @author lfalcao
 *
 */
public class TaskProxy extends Task {

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param creator
	 */
	public TaskProxy(int id, String title, String description, User creator) {
		super(id, title, description, creator);
		
	}

	@Override
	public User getCreator() {
		if(super.getCreator() == null) {
			loadCreator();
		}
		return creator;
		
	}
	
	private void loadCreator() {
		IUserRepository userRepo = RepositoryFactory.getUserRepository();
		userRepo.getCreatorForTask(taskId);
		
	}



	

}
