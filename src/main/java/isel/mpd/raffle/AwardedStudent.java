/**
 *
 */
package isel.mpd.raffle;

/**
 * @author lfalcao
 *
 */
public class AwardedStudent extends Student {

	private int awards;

	/**
	 * @param nr
	 * @param name
	 * @param grade
	 */
	public AwardedStudent(int nr, String name, int grade, int awards) {
		super(nr, name, grade);
		this.awards = awards;
	}
	
	public int getNumberOfAwards() {
		return awards;
	}

}
