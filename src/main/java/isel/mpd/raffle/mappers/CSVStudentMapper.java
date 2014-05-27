package isel.mpd.raffle.mappers;



public class CSVStudentMapper extends StudentMapper {
    @Override
	protected boolean matchesStudent(String line) {
        boolean b =  line.matches("\\d+;(\\p{IsAlphabetic}|\\s)+;\\d{0,2}");
        return b;
    }
    
    @Override
	public String[] getTokens(String line){
        return line.split(";");
    }
}
