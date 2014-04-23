package isel.mpd.binding.formatters;


public abstract class StringFormatter implements IFormatter {

	@Override
	public final Object format(Object value) {
		if(value != null && value instanceof String) {
			value = stringFormat((String)value);
		}
		return value;
	}

	protected abstract String stringFormat(String value);

}
