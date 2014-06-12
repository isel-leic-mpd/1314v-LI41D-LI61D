/**
 *
 */
package isel.mpd.tasklist.dataaccess.mappers;

import isel.mpd.exceptionHandling.MapperException;
import isel.mpd.tasklist.domain.entities.Task;
import isel.mpd.tasklist.domain.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lfalcao
 *
 */
public class UserMapper extends BaseMapper<User> {
	static final String SELECT_STRING = "select id, username, fullName, email from users";
	private static final String WHERE_STRING = "where id = ?";
	private static final String UPDATE_STRING = "update users set username = ?, fullName = ?, email = ? " +  WHERE_STRING;
	
	public UserMapper(DatabaseManager dbManager) {
		super(dbManager);
	}
	
	void update(User user) throws DataMapperException {
		int rowsAffected;
		try {
			PreparedStatement stmt = dbManager.getPreparedStatement(UPDATE_STRING);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getFullName());
			stmt.setString(3, user.getEmail());
			stmt.setInt(4, user.getId());
			rowsAffected = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataMapperException(e);
		}
		if(rowsAffected != 1) {
			throw new DataMapperException("Could not update task with id " + user.getId());
		}
		
		
	}
	
	int insert(User entity) {
		return 0;
	}
	
	boolean delete(User entity) {
		return false;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#bind(java.sql.ResultSet)
	 */
	@Override
	protected User bind(ResultSet res) throws SQLException {
		int id = res.getInt(1);
		String username = res.getString(2);
		String fullName = res.getString(3);
		String email = res.getString(4);
		
		return new User(id, username, fullName, email);
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#selectString()
	 */
	@Override
	protected String selectString() {
		return SELECT_STRING;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#whereString()
	 */
	@Override
	protected String whereString() {
		return WHERE_STRING;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#getId(java.lang.Object)
	 */
	@Override
	protected Object getId(User entity) {
		return entity.getId();
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#updateStatement(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected void updateStatement(PreparedStatement stmt, User entity)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#updateString()
	 */
	@Override
	protected String updateString() {
		return UPDATE_STRING;
	}
	

}
