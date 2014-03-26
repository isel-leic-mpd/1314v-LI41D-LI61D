package isel.mpd.exceptionHandling;

import java.util.Collection;

public interface IMapper<T> extends IMapperOriginal<T> {
	Collection<T> getAllNew() throws MapperException;
}
