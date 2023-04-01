package model;

import java.io.IOException;
/**
 * Toy super-class that gets and sets shared attributes
 * @author Mitchel Chanthaseng
 */
public abstract class Toy {
	private String serialNumber;
	private String name;
	private String brand;
	private float price;
	private int availableCount;
	private int minimumAge;

	/**
	 * Toy constructor that sets shared attributes.
	 * Complete with getter and setter for shared attributes
	 * @author Mitchel Chanthaseng
	 */
	public Toy(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.availableCount = availableCount;
		this.minimumAge = minimumAge;
	}

	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
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
	public float getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}
	public int getMinimumAge() {
		return minimumAge;
	}
	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	//Abstract toString method that forces sub-classes to have a way to print the toy.
	public abstract String toString();

	//Abstract save method that forces sub-classes to have a way to save the toy.
	public abstract void save(String filename) throws IOException;

}