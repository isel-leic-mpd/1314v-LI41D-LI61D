/**
 *
 */
package isel.mpd.tasklist.domain.entities;

/**
 * @author lfalcao
 *
 */
public class User {


	private int id;
	private String username;
	private String fullName;
	private String email;

	/**
	 * @param id
	 * @param username
	 * @param fullName
	 * @param email
	 */
	public User(int id, String username, String fullName, String email) {
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.email = email;
	}
	
	public User(String username, String fullName, String email) {
		this(-1, username, fullName, email);
	}

	/**
	 * @return
	 */
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public String getFullName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
