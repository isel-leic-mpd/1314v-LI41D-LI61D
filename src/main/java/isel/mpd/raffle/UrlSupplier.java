/**
 *
 */
package isel.mpd.raffle;

import isel.mpd.typesystem.util.SneakyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author lfalcao
 *
 */
public class UrlSupplier implements Supplier<List<Student>> {

	private URL url;
	private StudentMapper mapper;


	UrlSupplier(URL url, StudentMapper mapper) {
		this.url = url;
		this.mapper = mapper;
		
	}
	
	/* (non-Javadoc)
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public List<Student> get() {
		URLConnection connection;
		List<Student> stds = null;
		try {
			connection = url.openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");
	        InputStream response = connection.getInputStream();
	        stds = new BufferedReader(new InputStreamReader(response))
		        .lines()
		        .filter(mapper::hasStudentInfo)
		        .map(mapper::mapToStudent)
		        .collect(Collectors.toList());
		} catch (IOException e) {
			SneakyUtils.sneakyThrow(e);
		}
		
		return stds;
    }

}
