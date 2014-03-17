package isel.mpd.typesystem;



public class Car extends Vehicle {
	private int year;
	
	public Car(String name, String brand, int year) {
		super(name, brand);
		this.year = year;
	}
	
	public void startEngine() { }
}

