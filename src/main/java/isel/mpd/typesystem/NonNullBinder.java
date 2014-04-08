package isel.mpd.typesystem;


public class NonNullBinder implements BinderStrategy {
	
	private final BinderStrategy binderStrategy;

	
	public NonNullBinder(BinderStrategy binderStrategy) {
		this.binderStrategy = binderStrategy;
	}
	
	@Override
	public final <T> boolean bindMember(T newT, String key, Object value) {
			
		if(value == null)
            return false;
        return binderStrategy.bindMember(newT, key, value);
	}

}
