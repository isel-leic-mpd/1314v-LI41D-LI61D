package isel.mpd.raffle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer {

    List<Student> stds;

    
    public Dealer(URL path) throws IOException {
        initStudentsFromThoth(path);
    }
    

    private void initStudentsFromThoth(URL path) throws IOException {
        URLConnection connection = path.openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        InputStream response = connection.getInputStream();
        
        DealerStudentMapper mapper = new DealerStudentMapper();
        
        this.stds = new BufferedReader(new InputStreamReader(response))
                .lines()
                .filter(mapper::hasStudentInfo)
                .map(mapper::mapToStudent)
                .collect(Collectors.toList());

    }
    
    public Student randStudent() {
       return null;
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

}
