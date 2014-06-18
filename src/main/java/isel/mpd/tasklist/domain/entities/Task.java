/**
 *
 */
package isel.mpd.tasklist.domain.entities;

import isel.mpd.tasklist.dataaccess.IUserRepository;

import org.omg.CORBA.RepositoryIdHelper;

/**
 * @author lfalcao
 *
 */
//@TableName("Tarefas")
public class Task {
	//@Key(name="id")
	private int taskId;
	//@CoumumnName("descr")
	private String description;
	private String title;
	protected User creator;


	/**
	 * @param title2
	 * @param description2
	 * @param creator2
	 */
	public Task(String title, String description, User creator) {
		this(-1, title, description, creator);
	}
	
	
	public Task(int id, String title, String description, User creator) {
		this.taskId = id;
		this.title = title;
		this.description = description;
		this.creator = creator;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public int getId() {
		return taskId;
	}
	
	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @return
	 */
	public User getCreator() {
		return creator;
	}


	
	

}
