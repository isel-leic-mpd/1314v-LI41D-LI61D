package isel.mpd.typesystem;

public class NonNullPropertiesBinder extends NonNullBinder {
		 
	private static PropertiesBinder Binder = new PropertiesBinder();
		
	public NonNullPropertiesBinder() {
		super(Binder);
	}
}
