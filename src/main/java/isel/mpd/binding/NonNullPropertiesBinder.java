package isel.mpd.binding;

public class NonNullPropertiesBinder extends NonNullBinder {
		 
	private static MemberBinder Binder = new PropertiesBinder();
		
	public NonNullPropertiesBinder() {
		super(Binder);
	}
}
