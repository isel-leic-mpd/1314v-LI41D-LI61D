package isel.mpd.raffle;

import java.net.URL;
import java.util.List;
import java.util.function.Supplier;


public class RaffleStudent
{
    private static String classroomUrl = "http://thoth.cc.e.ipl.pt/classes/MPD/1314v/LI41D-LI61D/pages/tpcs---entregas/504";

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String [] args) throws Exception
    {
    	Supplier<List<Student>> supplier = new UrlSupplier(new URL(classroomUrl), new ThothStudentMapper());
        Dealer d = new Dealer(supplier);
        d.getStudents().stream()
                .filter(s -> s.getNumber() > 100)
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                //.forEach(s -> System.out.println(s))
                .forEach(System.out::println);
        Student s = d.randStudent();
        System.out.println("Aluno sorteado = " + s);
    }
}
