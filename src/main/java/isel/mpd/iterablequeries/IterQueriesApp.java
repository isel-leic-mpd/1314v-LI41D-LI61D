/**
 *
 */
package isel.mpd.iterablequeries;

/**
 * @author lfalcao
 *
 */

import isel.mpd.raffle.Dealer;
import isel.mpd.raffle.FileSupplier;
import isel.mpd.raffle.Person;
import isel.mpd.raffle.Student;
import isel.mpd.raffle.mappers.CSVStudentMapper;

import java.io.IOException;

public class IterQueriesApp {

    private static String classroomUrl = "http://thoth.cc.e.ipl.pt/classes/MPD/1314v/LI41N-LI61N/pages/tpcs---entregas/501";

    public static void main(String[] args) throws IOException {
        Dealer d = new Dealer(new FileSupplier("c:\\tmp\\students.csv", new CSVStudentMapper()));

        //String name = IterUtils.query(d.getStudents()) // TPC Alinea 3
        String name = ListUtils.query(d.getStudents()) // TPC Alinea 2
        //String name = d.getStudents().stream() // TPC Alinea 1
                .filter(s -> {
                    trace("filter1");
                    return s.getNumber() > 3500;
                })
                .filter(s -> {
                    trace("filter2");
                    return IterQueriesApp.filterName(s); 
                    //return s.getName().startsWith("A");
                })
                .map(s -> {
                    trace("map");
                    return s.getNumber() + " " + s.getName() + " " + s.getGrade();
                })
                .skip(2)
                .iterator().next();

        System.out.println(name);
    }

    public static void trace(String msg) {
        System.out.println(msg);
    }

    private static int CompareStdById(Student s1, Student s2) {
        return s1.getNumber() - s2.getNumber();
    }
    
    private static boolean filterName(Person p) {
        return p.getName().startsWith("A");
    }

}

