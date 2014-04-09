package isel.mpd.binding;



public class NonNullBinder implements IBinderStrategy {
	
	private final IBinderStrategy binderStrategy;

	
	public NonNullBinder(IBinderStrategy binderStrategy) {
		this.binderStrategy = binderStrategy;
	}
	
	@Override
	public final <T> boolean bindMember(T newT, String key, Object value) {
			
		if(value == null)
            return false;
        return binderStrategy.bindMember(newT, key, value);
	}

}
