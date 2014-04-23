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

	private IBinderStrategy binder;
	private IFormatter[] formatters;

	public FormattersBinder(IBinderStrategy binder, IFormatter ... formatters) {
		this.binder = binder;
		this.formatters = formatters;
		
	}
	
	/* (non-Javadoc)
	 * @see isel.mpd.binding.IBinderStrategy#bindMember(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	
	
	@Override
	public <T> boolean bindMember(T newT, String key, Object value) {
		
		for (IFormatter formatter : formatters) {
			value = formatter.format(value);
		}
		return binder.bindMember(newT, key, value);
	}

}
