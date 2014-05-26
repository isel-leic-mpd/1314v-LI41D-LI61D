package isel.mpd.raffle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Dealer {

    private List<Student> stds;

    
//    public Dealer(URL path) throws IOException {
//        initStudentsFromThoth(path);
//    }
    
    public Dealer(Supplier<List<Student>> studentsProvider)  {
        stds = studentsProvider.get();
    }
    

      
    public Student randStudent() {
       List<Student> elegibleStudents = stds.stream().filter(s -> s.getGrade() >= 5 && s.getGrade() < 20).collect(Collectors.toList());
       int idx = (new Random()).nextInt(elegibleStudents.size());
       return elegibleStudents.get(idx);
    }

    
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        stds.forEach(s -> res.append(s).append("\n"));
        return res.toString();
    }
    
    
    public void print() {
        System.out.println(this.toString());
    }

	/**
	 * @return the stds
	 */
	public List<Student> getStudents() {
		return stds;
	}

	/**
	 * @param stds the stds to set
	 */
	public void setStds(List<Student> stds) {
		this.stds = stds;
	}

}
