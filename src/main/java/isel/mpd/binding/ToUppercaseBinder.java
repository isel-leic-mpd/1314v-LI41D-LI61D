/**
 *
 */
package isel.mpd.binding;


/**
 * @author lfalcao
 *
 */
public class ToUppercaseBinder implements IBinderStrategy {

	private IBinderStrategy[] delegateBinderStrategy;


	
	public ToUppercaseBinder(IBinderStrategy... delegateBinderStrategy) {
		this.delegateBinderStrategy = delegateBinderStrategy;
	}

	/* (non-Javadoc)
	 * 
	 * @see isel.mpd.typesystem.BinderStrategy#bindMember(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> boolean bindMember(T newT, String key, Object value) {
		if(value != null && value instanceof String) {
			value = value.toString().toUpperCase();
		}
		
		for (IBinderStrategy bs : delegateBinderStrategy) {
			if (bs.bindMember(newT, key, value))
				return true;
		}
		return false;
	}
}
