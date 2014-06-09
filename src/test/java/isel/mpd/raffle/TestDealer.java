/**
 *
 */
package isel.mpd.raffle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author lfalcao
 *
 */
public class TestDealer {

	@Test
	public void testDealerGettingAllStudents() {
		List<Student> originalStudents = getAllStudents();
		
		// Arrange
		Dealer d = new Dealer((Supplier<List<Student>>)() -> getAllStudents());
		
		// Act 
		List<Student> dealerStudents = d.getStudents();

		// Assert
		assertEquals(originalStudents.size(), dealerStudents.size());
		assertArrayEquals(originalStudents.toArray(), dealerStudents.toArray());
		
	}

	/**
	 * @return
	 */
	private List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>(10);
		for(int i = 1; i <= 10; ++i) {
			students.add(i-1, new Student(i, "Student" + i, 10));
		}
		return students;
	}

}
