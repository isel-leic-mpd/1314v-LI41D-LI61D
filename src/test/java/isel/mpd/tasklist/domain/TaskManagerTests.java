/**
 *
 */
package isel.mpd.tasklist.domain;

import static org.junit.Assert.*;
import isel.mpd.tasklist.dataaccess.FakeRepository;
import isel.mpd.tasklist.domain.entities.Task;
import isel.mpd.tasklist.domain.entities.User;
import isel.mpd.tasklist.domain.logic.TaskManager;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author lfalcao
 *
 */
public class TaskManagerTests {
	/**
	 * 
	 */
	
	private static TaskManager tm;
	private static User defaultUser;
			
	@BeforeClass
	public static void beforeClass() {
		tm = new TaskManager(new FakeRepository());
		defaultUser = new User("username", "Full Name", "email");
	}

	
			
	@Test
	public void shoudAddANewTaskWithTitleOnly() {
		// Arrange
		final String TASK_TITLE = "Title1";
				
		// Act
		Task t = tm.createTask(TASK_TITLE, defaultUser);
		
		// Assert
		assertTask(TASK_TITLE, null, t);
	}
	
	@Test
	public void shoudAddANewTaskTitleAndDescription() {
		// Arrange
		final String TASK_TITLE = "Title1";
		final String TASK_DESCRIPTION = "Task description";
				
		// Act
		Task t = tm.createTask(TASK_TITLE, TASK_DESCRIPTION, defaultUser);
		
		// Assert
		assertTask(TASK_TITLE, TASK_DESCRIPTION, t);
	}



	/**
	 * @param TASK_TITLE
	 * @param TASK_DESCRIPTION
	 * @param t
	 */
	private void assertTask(final String TASK_TITLE, final String TASK_DESCRIPTION, Task t) {
		assertEquals(t.getTitle(), TASK_TITLE);
		assertEquals(t.getDescription(), TASK_DESCRIPTION);
		Task t1 = tm.getTask(t.getId());
		assertNotNull(t1);
		assertEquals(t1.getTitle(), TASK_TITLE);
	}

}
