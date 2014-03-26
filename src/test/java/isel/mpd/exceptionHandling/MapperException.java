package isel.mpd.exceptionHandling;

public class MapperException extends Exception {
	public MapperException() {	}
	
	public MapperException(String message) {
		super(message);
	}
	
	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MapperException(Throwable cause) {
		super(cause);
	}
}
