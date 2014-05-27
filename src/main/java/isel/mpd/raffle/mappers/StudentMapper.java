/**
 *
 */
package isel.mpd.raffle.mappers;

import isel.mpd.raffle.Student;

/**
 * @author lfalcao
 *
 */
public abstract class StudentMapper {
	private String previous;

	public final Student mapToStudent(String line) {
		if(previous != line)
            throw new IllegalArgumentException("This mapper should be used in a stream with the hasStudentInfo as the previous filter funcion");
        
        String []tokens = getTokens(line);
                
        return new Student(
                Integer.parseInt(tokens[0]), 
                tokens[1], 
                Integer.parseInt(tokens[2]));
		
	}
	
	public final boolean hasStudentInfo(String line) {
		previous = line;
		return matchesStudent(line);
	}
	/**
	 * @param line
	 * @return
	 */
	protected abstract boolean matchesStudent(String line);
	
	/**
	 * @param line
	 * @return
	 */
	protected abstract String[] getTokens(String line);

}
