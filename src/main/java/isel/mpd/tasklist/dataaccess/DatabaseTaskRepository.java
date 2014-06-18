/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import isel.mpd.tasklist.dataaccess.mappers.DataMapperException;
import isel.mpd.tasklist.dataaccess.mappers.DatabaseManager;
import isel.mpd.tasklist.dataaccess.mappers.TaskMapper;
import isel.mpd.tasklist.domain.entities.Task;
import isel.mpd.tasklist.domain.entities.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lfalcao
 *
 */
public class DatabaseTaskRepository implements ITaskRepository {

	TaskMapper taskMapper;
	private Map<Integer, Task> identityMap = new HashMap<Integer, Task>();
	
	DatabaseTaskRepository(DatabaseManager dbManager) {
		taskMapper = new TaskMapper(dbManager);
	}
	
	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.IRepository#getAll()
	 */
	@Override
	public Collection<Task> getAll() throws RepositoryException {
		try {
			return taskMapper.findAll();
		} catch (DataMapperException e) {
			throw new RepositoryException(e);
		}
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.IRepository#add(java.lang.Object)
	 */
	@Override
	public void add(Task t) {
		t = taskMapper.insert(t);
		identityMap.put(t.getId(), t);
		
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.IRepository#update(java.lang.Object)
	 */
	@Override
	public void update(Task t) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see isel.mpd.tasklist.dataaccess.IRepository#getById(int)
	 */
	@Override
	public Task getById(int id) throws RepositoryException {
		try {
			if(identityMap.containsKey(id)) {
				return identityMap.get(id);
			}
			Task t = taskMapper.findById(id);
			identityMap.put(id, t);
			return t;
			
		} catch (DataMapperException e) {
			throw new RepositoryException(e);
		}
	}


}
