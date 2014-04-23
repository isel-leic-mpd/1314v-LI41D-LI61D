/**
 *
 */
package isel.mpd.binding.formatters;


/**
 * @author lfalcao
 *
 */
public class ToUpperCaseFormatter extends StringFormatter implements IFormatter {
	@Override
	protected String stringFormat(String value) {
		return value.toUpperCase();
	}}
