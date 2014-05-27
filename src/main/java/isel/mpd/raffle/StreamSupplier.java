/**
 *
 */
package isel.mpd.raffle;

import isel.mpd.raffle.mappers.StudentMapper;
import isel.mpd.typesystem.util.SneakyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author lfalcao
 *
 */
public abstract class StreamSupplier implements Supplier<List<Student>> {

	private StudentMapper mapper;

	/**
	 * @param mapper
	 */
	public StreamSupplier(StudentMapper mapper) {
		this.mapper = mapper;
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public List<Student> get() {
		List<Student> stds = null;
		try {
	        InputStream response = getInputStream();
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


	/**
	 * @return
	 */
	protected abstract InputStream getInputStream() throws IOException;

}
