package isel.mpd.raffle.mappers;

import java.util.Arrays;

import org.apache.commons.lang3.StringEscapeUtils;

public class ThothStudentMapper extends StudentMapper {
    private String[] tokens;

	@Override
	protected boolean matchesStudent(String line) {
        if (line.indexOf("<td>") < 0) {
            return false;
        }
        line = line
                .replace("<td>", "|")
                .replace("</td>", "|")
                .replace("<tr>", "|")
                .replace("</tr>", "|")
                .replace("<table>", "|")
                .replace("</table>", "|");

        tokens = line.split("\\|");

        tokens = (String[]) Arrays.stream(tokens)
                .filter(s -> {
                    s = s.replaceAll("\\s+", "");
                    return s != null && !s.isEmpty();
                 })
                .toArray(cap -> new String[cap]);

        if(!tokens[0].matches("\\d+"))
            return false;
        
        return true;

    }
    
    @Override
	public String[] getTokens(String line){
           tokens[2] = StringEscapeUtils.unescapeHtml4(tokens[2]);
           return new String[] { tokens[0], tokens[2], tokens.length >= 4 ? tokens[3] : "10" };
    }
}
