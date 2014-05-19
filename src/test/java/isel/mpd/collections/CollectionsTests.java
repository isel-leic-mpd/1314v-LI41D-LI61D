/**
 *
 */
package isel.mpd.collections;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.ws.ServiceMode;

import org.junit.Before;
import org.junit.Test;

/**
 * @author lfalcao
 *
 */

public class CollectionsTests {
	private String []soccerClubNames = { "SLB" , "Benfica-B", "Benfica","Benfica Júniores"};
	private Runnable []runnables = { () -> System.out.println("SLB") , CollectionsTests::printToFile};
	List<String> soccerClubNamesList;

	static void printToFile() {
		
	}
	
	@Before
	public void setUpTest() {
		soccerClubNamesList = Arrays.asList(soccerClubNames);
	}
	
	private static int myClassStringLengthComparator(String s1, String s2) {
		return s1.length() -  s2.length();
	}
	
	private int myInstanceStringLengthComparator(String s1, String s2) {
		return s1.length() -  s2.length();
	}
	
	public class LengthComparator implements Comparator<String> {
		public int compare(String s1, String s2) {
			return s1.length() -  s2.length();
		}
	}

	@Test
	public void testSortingACollectionWithOuterClass() {
		// Arrange 
		
		// Act
		Collections.sort(soccerClubNamesList, new LengthComparator() );
		
		// Assert
		
	}

	public void testSortingACollectionWithAnonymousClass() {
		Integer stream = soccerClubNamesList.stream()
					.mapToInt((s) -> s.length()).sum();
		
		// Act
		Collections.sort(soccerClubNamesList, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() -  s2.length();
			}
		});
		
		// Assert
		
	}
	
	@Test
	public void testSortingACollectionWithLambdas() {
		Comparator<String> cmp = (s1, s2) -> s1.length() -  s2.length();
		Comparator<Integer> cmp1 = (s1, s2) -> s1 -  s2;
		
		// Act
		Collections.sort(soccerClubNamesList, cmp);
		
		m1((String s1, String s2) -> s1.length() -  s2.length());
		
		
		// Assert
		
	}
	
	public void testSortingACollectionWithStaticMethodWithoutLambdas() {
		Integer stream = soccerClubNamesList.stream()
					.mapToInt((s) -> s.length()).sum();
		
		// Act
		Collections.sort(soccerClubNamesList, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return CollectionsTests.this.myInstanceStringLengthComparator(s1, s2);
			}
		});
		
		// Assert
		
	}
	
	public void testSortingACollectionWithStaticMethodUsingLambdas() {
		Integer stream = soccerClubNamesList.stream()
					.mapToInt((s) -> s.length()).sum();
		
		// Act
		Collections.sort(soccerClubNamesList, CollectionsTests::myClassStringLengthComparator);
		
		// Assert
		
	}
	
	public void testSortingACollectionWithInstanceMethodUsingLambdas() {
		Integer stream = soccerClubNamesList.stream()
					.mapToInt((s) -> s.length()).sum();
		
		// Act
		Collections.sort(soccerClubNamesList, 
				//this::myInstanceStringLengthComparator
				(s1, s2) -> this.myInstanceStringLengthComparator(s1,s2)
				
				);
		
		// Assert
		
	}
	
	public void testSortingUsingInterfaceStaticMethos() {
		
		// Act
		Collections.sort(soccerClubNamesList, Comparator.comparing((s) -> s.length())); 
		
		// Assert
		
	}
	
	private void m1(Comparator<String> cmp) {
	
	}
	
	private void m1(IntComparator cmp) {
		
	}
	
	interface IntComparator {
		int compare(Integer i1, Integer i2);
	}

}
