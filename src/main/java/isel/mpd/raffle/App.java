package isel.mpd.raffle;

import java.io.IOException;
import java.net.URL;


class App
{
    private static String classroomUrl = "http://thoth.cc.e.ipl.pt/classes/MPD/1314v/LI41D-LI61D/pages/tpcs---entregas/504";

    public static void main(String [] args) throws IOException
    {
        Dealer d = new Dealer(new URL(classroomUrl));
        
        d.stds.stream()
                .filter(s -> s.nr > 100)
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .forEach(s -> System.out.println(s));

        Student s = d.randStudent();
        System.out.println("Aluno sorteado = " + s);
    }

    private static int CompareStdById(Student s1, Student s2)
    {
        return s1.nr - s2.nr;
    }

}
