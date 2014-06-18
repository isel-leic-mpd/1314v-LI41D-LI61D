/**
 *
 */
package isel.mpd.tasklist.dataaccess;

import isel.mpd.tasklist.domain.entities.User;

/**
 * @author lfalcao
 *
 */
public interface IUserRepository extends IRepository<User> {
	User getCreatorForTask(int taskId);

}
