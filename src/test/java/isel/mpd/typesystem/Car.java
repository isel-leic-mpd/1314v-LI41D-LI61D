package isel.mpd.typesystem;



public class Car extends Vehicle {
	private int year;
	
	
	public Car() {
	
	}
	
	public Car(String name, String brand, int year) {
		super(name, brand);
		this.setYear(year);
	}
	
	public void startEngine() { }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}

