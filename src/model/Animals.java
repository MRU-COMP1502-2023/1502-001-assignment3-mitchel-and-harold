package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Animals sub-class of Toy class
 * @author Mitchel Chanthaseng
 */
public class Animals extends Toy{
	private String material;
	private char size;

	/**
	 * Animals constructor that also takes size and material arguments
	 * Complete with getter and setter for unique attributes
	 * @author Mitchel Chanthaseng
	 */
	public Animals(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge, String material, char size) {
		super(serialNumber, name, brand, price, availableCount, minimumAge);
		this.material = material;
		this.size = size;
	}

	/**
	 * @author Mitchel Chanthaseng
	 * @return
	 */
	public String getMaterial() {
		return material;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @param material
	 */
	public void setMaterial(String material) {
		this.material = material;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @return
	 */
	public char getSize() {
		return size;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @param size
	 */
	public void setSize(char size) {
		this.size = size;
	}

	//Overrides version of Toy's abstract toString() method.
	@Override
	public String toString() {
		return "Serial Number: " + getSerialNumber() + "  " +
				"Name: " + getName() + "  " +
				"Brand: " + getBrand() + "  " +
				"Price: " + getPrice() + "  " +
				"Available Count: " + getAvailableCount() + "  " +
				"Minimum Age: " + getMinimumAge() + "  " +
				"Material: " + getMaterial() + "  " +
				"Size: " + getSize() + "  ";

	}

	//Overrides version of Toy's abstract save() method.
	@Override
	public void save(String filename) throws IOException { 
		FileWriter fw = new FileWriter(filename, true);
		PrintWriter writer = new PrintWriter(fw);
		writer.write(getSerialNumber() + ";" + getName() + ";" + getBrand() + ";" + getPrice()+ ";"+ getAvailableCount() + ";" +getMinimumAge() + ";" + getMaterial() + ";" + getSize()+"\n");
		writer.close();

	}

}