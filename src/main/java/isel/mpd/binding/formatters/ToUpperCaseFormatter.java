/**
 *
 */
package isel.mpd.binding.formatters;

/**
 * @author lfalcao
 *
 */
public class ToUpperCaseFormatter implements IFormatter {

	/* (non-Javadoc)
	 * @see isel.mpd.binding.formatters.IFormatter#format(java.lang.Object)
	 */
	@Override
	public Object format(Object value) {
		return value != null && value instanceof String ? value.toString().toUpperCase() : value;
	}
}
