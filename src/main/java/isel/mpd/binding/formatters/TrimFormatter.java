package isel.mpd.binding.formatters;


public class TrimFormatter extends StringFormatter implements IFormatter {
	@Override
	protected String stringFormat(String value) {
		return value.trim();
	}
}
