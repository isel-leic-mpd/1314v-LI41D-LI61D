package isel.mpd.typesystem;

import isel.mpd.binding.formatters.Formatter;
import isel.mpd.binding.formatters.ToUpperCaseFormatter;
import isel.mpd.binding.formatters.TrimFormatter;

import java.util.Calendar;
import java.util.Date;



public class Car extends Vehicle {
	
	@Formatter(formatterClass = ToUpperCaseFormatter.class)
	private String model = "default model";
	private int year;
	private String purchaseDate;
	
	
	public Car() {
	
	}
	
	public Car(String name, String brand, int year) {
		this(name, brand, null, year);
		this.setYear(year);
	}
	
	public Car(String name, String brand, String model, int year) {
		super(name, brand);
		this.setYear(year);
		this.model = model == null ? this.model : model;
	}
	
	public void startEngine() { }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year < 0)
			throw new IllegalArgumentException("year cannot be a negative value");
		this.year = year;
	}
	
	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - year;
	}
	
	public void setAge(int age) {
		year = Calendar.getInstance().get(Calendar.YEAR) - age;
	}

	public String getModel() {
		return model;
	}
	
	@Formatter(formatterClass = TrimFormatter.class)
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the purchaseDate
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	
	@Formatter(formatterMethod = "formatDate")
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	@Formatter(formatterMethod = "formatDate")
	public String formatDate(String date) {
		return date.replace("/", "-");
	}
}

