package isel.mpd.typesystem;

import isel.mpd.misc.Utils;

public class Vehicle {
	private String name = "default";
	public String brand = "default";
	private static String Foo = "Foo";
	
	static {
		
	}
	
	public Vehicle() {
		
		
	}
	
	public Vehicle(String name, String brand) {
		this.name = name;
		this.brand =  brand;
	}
	
	
	public void startEngine() { }
	public final void killEngine() { }
}
