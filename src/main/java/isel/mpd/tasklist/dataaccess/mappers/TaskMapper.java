/**
 *
 */
package isel.mpd.tasklist.dataaccess.mappers;

import isel.mpd.tasklist.domain.entities.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lfalcao
 *
 */
public class TaskMapper extends BaseMapper<Task> {
	static final String SELECT_STRING = "select id, title, description from tasks";
	private static final String WHERE_STRING = "where id = ?";
	static final String UPDATE_STRING = "update Tasks set title = ?, description = ? "
			+ WHERE_STRING;

	public TaskMapper(DatabaseManager dbManager) {
		super(dbManager);
	}

	public Task insert(Task task) {
		return null;
	}

	boolean delete(Task task) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * isel.mpd.tasklist.dataaccess.mappers.BaseMapper#bind(java.sql.ResultSet)
	 */
	@Override
	protected Task bind(ResultSet res) throws SQLException {
		int id = res.getInt(1);
		String title = res.getString(2);
		String description = res.getString(3);
		return new Task(id, title, description, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#selectString()
	 */
	@Override
	protected String selectString() {
		return SELECT_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#whereString()
	 */
	@Override
	protected String whereString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#updateString()
	 */
	@Override
	protected String updateString() {
		return UPDATE_STRING;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#updateStatement(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected void updateStatement(PreparedStatement stmt, Task entity) throws SQLException {
		stmt.setString(1, entity.getTitle());
		stmt.setString(2, entity.getDescription());
		stmt.setInt(3, entity.getId());
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#getId(java.lang.Object)
	 */
	@Override
	protected Object getId(Task entity) {
		return entity.getId();
	}
}
