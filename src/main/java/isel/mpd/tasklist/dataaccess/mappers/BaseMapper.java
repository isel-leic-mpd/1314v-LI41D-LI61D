/**
 *
 */
package isel.mpd.tasklist.dataaccess.mappers;

import isel.mpd.tasklist.domain.entities.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lfalcao
 *
 */
public abstract class BaseMapper<T> {
	protected DatabaseManager dbManager;
	
	protected BaseMapper(DatabaseManager dbManager) {
		this.dbManager = dbManager;
		
	}
	
	public final T findById(int id) throws DataMapperException {
		PreparedStatement stmt = null;
		
		try {
			stmt = dbManager.getPreparedStatement(selectString() + whereString());
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
				return bind(res);
			} 
			return null;
		} catch (SQLException e) {
			throw new DataMapperException(e);
		}
	}

	/**
	 * @return
	 */
	protected abstract String whereString();

	public final Collection<T> findAll() throws DataMapperException {
		PreparedStatement stmt = null;
		
		try {
			stmt = dbManager.getPreparedStatement(selectString());
			ResultSet res = stmt.executeQuery();
			List<T> entities = new LinkedList<>();
			
			while(res.next()) {
				entities.add(bind(res));
			}
			return entities;
		} catch (SQLException e) {
			throw new DataMapperException(e);
		}
	}

	/**
	 * @param res
	 * @return
	 * @throws SQLException 
	 */
	protected abstract T bind(ResultSet res) throws SQLException;

	/**
	 * @return
	 */
	protected abstract String selectString();

}
