/**
 *
 */
package isel.mpd.raffle;

import isel.mpd.raffle.mappers.StudentMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lfalcao
 *
 */
public class UrlSupplier extends StreamSupplier {

	private URL url;


	public UrlSupplier(URL url, StudentMapper mapper) {
		super(mapper);
		this.url = url;
	}
	
	@Override
	protected InputStream getInputStream() throws IOException {
		URLConnection connection;
		connection = url.openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");
        return connection.getInputStream();
	}
}
