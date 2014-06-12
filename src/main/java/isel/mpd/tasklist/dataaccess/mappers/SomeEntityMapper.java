/**
 *
 */
package isel.mpd.tasklist.dataaccess.mappers;

import isel.mpd.tasklist.domain.entities.{EntityName};

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lfalcao
 *
 */
public class {EntityName}Mapper extends BaseMapper<{EntityName}> {
	static final String SELECT_STRING = "select {COLUMN_NAMES} from {TABLE_NAME}";
	private static final String WHERE_STRING = "where {KEY_NAME} = ?";
	static final String UPDATE_STRING = "update {TABLE_NAME} set {SET_COLUMNS}"
			+ WHERE_STRING;

	public {EntityName}Mapper(DatabaseManager dbManager) {
		super(dbManager);
	}

	int insert({EntityName} e) {
		return 0;
	}

	boolean delete({EntityName} e) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * isel.mpd.tasklist.dataaccess.mappers.BaseMapper#bind(java.sql.ResultSet)
	 */
	@Override
	protected {EntityName} bind(ResultSet res) throws SQLException {
		
		// ###################
		{BIND_CODE}
		// ###################
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
		return WHERE_STRING;
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
	protected void updateStatement(PreparedStatement stmt, {EntityName} entity) throws SQLException {
		// ###################
		{UPDATE_STATEMENT}
		// ###################
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.mappers.BaseMapper#getId(java.lang.Object)
	 */
	@Override
	protected Object getId(Task entity) {
		return entity.getId();
	}
}
