/**
 *
 */
package isel.mpd.tasklist.dataaccess.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import isel.mpd.tasklist.domain.entities.Task;

/**
 * @author lfalcao
 *
 */
public class TaskMapper extends BaseMapper<Task> {
	static final String SELECT_STRING = "select id, title, description from tasks";
	

	public TaskMapper(DatabaseManager dbManager) {
		super(dbManager);
	}
	
	
	
	void update(Task task) {
		
	}
	
	int insert(Task task) {
		return 0;
	}
	
	boolean delete(Task task) {
		return false;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#bind(java.sql.ResultSet)
	 */
	@Override
	protected Task bind(ResultSet res) throws SQLException {
		int id = res.getInt(1);
		String title = res.getString(2);
		String description = res.getString(3);
		
		return new Task(id, title, description, null);
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#selectString()
	 */
	@Override
	protected String selectString() {
		return SELECT_STRING;
	}
	

}
