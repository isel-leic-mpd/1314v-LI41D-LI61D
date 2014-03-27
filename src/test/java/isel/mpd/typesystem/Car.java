package isel.mpd.typesystem;

import java.sql.Date;
import java.util.Calendar;



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
		if(year < 0)
			throw new IllegalArgumentException("year");
		this.year = year;
	}
	
	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - year;
	}
	
	public void setAge(int age) {
		year = Calendar.getInstance().get(Calendar.YEAR) - age;
	}
}

