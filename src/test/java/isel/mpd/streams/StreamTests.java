/**
 *
 */
package isel.mpd.streams;

import static org.junit.Assert.*;
import isel.mpd.raffle.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream.GetField;
import java.util.List;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author lfalcao
 *
 */
public class StreamTests {
	static Stream<Student> roster;
	
	static Stream<Student> getStudentsStream() {
		return null;
	}
	
	private void foo() { }
	
	@BeforeClass
	public static void beforeClass() {
		roster = getStudentsStream();
	}
	
	@Test
	public void testStudentsStream() {
		Stream<Student> studentsWithA = roster
	    .filter(e -> e.getName().contains("a"));
	}
	
	@Test
	public void testFileStream() throws FileNotFoundException {
		String methodRegExp = //"public"; 
				"\\s*public\\s+(\\w+\\s+){1,2}\\w+\\s*\\([^)]*\\).*";
		new BufferedReader(new FileReader("C:\\lfalcao\\Work\\ISEL\\Disciplinas\\2013_2014\\2013-2014Ver-MPD\\Repositories\\1314v-LI41D-LI61D\\src\\test\\java\\isel\\mpd\\streams\\StreamTests.java"))
		.lines().filter(s -> s.matches(methodRegExp))
		.forEach(s -> System.out.println(s));
	}

}
