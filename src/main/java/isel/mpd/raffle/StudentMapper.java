/**
 *
 */
package isel.mpd.raffle;

/**
 * @author lfalcao
 *
 */
public interface StudentMapper {

	Student mapToStudent(String line);

	boolean hasStudentInfo(String line);

}
