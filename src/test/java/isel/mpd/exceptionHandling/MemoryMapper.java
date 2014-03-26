package isel.mpd.exceptionHandling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemoryMapper<T> implements IMapper<T> {
	List<T> coll = new ArrayList<T>();
	
	@Override
	public Collection<T> getAll() {
		return coll;
	}

	@Override
	public Collection<T> getAllNew() throws MapperException {
		// TODO Auto-generated method stub
		return null;
	}

}
