/**
 *
 */
package isel.mpd.binding;

import isel.mpd.binding.formatters.IFormatter;


/**
 * @author lfalcao
 *
 */
public class FormattersBinder implements IBinderStrategy {

	FormattersBinder(IBinderStrategy binder, IFormatter ... formatters) {
		
	}
	
	/* (non-Javadoc)
	 * @see isel.mpd.binding.IBinderStrategy#bindMember(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	
	
	@Override
	public <T> boolean bindMember(T newT, String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

}
