/**
 *
 */
package isel.mpd.tasklist.domain.entities;

/**
 * @author lfalcao
 *
 */
public class Task {

	private String description;
	private int taskId;
	private String title;
	private User creator;


	/**
	 * @param title2
	 * @param description2
	 * @param creator2
	 */
	public Task(String title, String description, User creator) {
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

}
