package isel.mpd.exceptionHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

public class FileMapper<T> implements IMapper<T> {

	private String path;

	FileMapper(String path) {
		this.path = path;
		
	}
	
	@Override
	public Collection<T> getAll()  {
		try {
			return getAllNew();
		} catch (MapperException e) {
			// This is a poor solution, but it's the best one when we don't have access 
			// to the base class or interface source code and still want yo use it.  
			throw new RuntimeException(e);
		}
	}
	


	@Override
	public Collection<T> getAllNew() throws MapperException {
		// Java 6 version
//		FileInputStream fs = null;
//		try {
//			fs = new FileInputStream(path);
//			fs.read();
//		} catch (IOException  e) {
//			throw new MapperException(e);
//		} finally {
//			if(fs != null)
//				try {
//					fs.close();
//				} catch (IOException e) {
//					
//				}
//		}
//		return null;

		// Java 7 version with try-with-resources statement
		try(FileInputStream fs = new FileInputStream(path)) { 
			fs.read();
		} catch (IOException  e) {
			throw new MapperException(e);
		}
		return null;
		
	}
	
	public Collection<T> anotherGetAll() throws Exception {
		try {
			return getAllNew();
		} catch (MapperException e) {
			throw new Exception("Rethrowing the same exception in another exception. Caution: this is very stupid",  e);
		}
		
	}
}