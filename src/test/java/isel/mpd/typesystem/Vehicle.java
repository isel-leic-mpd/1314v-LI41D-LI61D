package isel.mpd.typesystem;

import isel.mpd.misc.Utils;

public class Vehicle {
	@Formater(ToUpperCaseFormmater.class)
	private String name = "default";
	private String brand = "default";
	private static String Foo = "Foo";
	
	static {
		
	}
	
	public Vehicle() {
		
		
	}
	
	public Vehicle(String name, String brand) {
		this.setName(name);
		this.setBrand(brand);
	}
	
	
	public void startEngine() { }
	public final void killEngine() { }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
