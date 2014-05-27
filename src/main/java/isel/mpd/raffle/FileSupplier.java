/**
 *
 */
package isel.mpd.raffle;

import isel.mpd.raffle.mappers.StudentMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author lfalcao
 *
 */
public class FileSupplier extends StreamSupplier {

	private String filePath;

	/**
	 * @param string
	 * @param csvStudentMapper
	 */
	public FileSupplier(String filePath, StudentMapper studentMapper) {
		super(studentMapper);
		this.filePath = filePath;
	}

	/* (non-Javadoc)
	 * @see isel.mpd.raffle.FooSuplier#getInputStream()
	 */
	@Override
	protected InputStream getInputStream() throws IOException {
		return new FileInputStream(filePath);
	}
}
