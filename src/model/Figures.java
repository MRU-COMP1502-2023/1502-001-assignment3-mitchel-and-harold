package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Figures sub-class of Toy class
 * @author Mitchel Chanthaseng
 */
public class Figures extends Toy {
	private char classification;

	/**
	 * Figures constructor that also takes classification argument
	 * Complete with getter and setter for unique attributes
	 * @author Mitchel Chanthaseng
	 */
	public Figures(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge, char classification) {
		super(serialNumber, name, brand, price, availableCount, minimumAge);
		this.classification = classification;
	}

	/**
	 * @author Mitchel Chanthaseng
	 * @return
	 */
	public char getClassification() {
		return classification;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @param classification
	 */
	public void setClassification(char classification) {
		this.classification = classification;
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
				"Classification: " + getClassification() + "  ";
	}

	//Overrides version of Toy's abstract save() method.
	@Override
	public void save(String filename) throws IOException { 
		FileWriter fw = new FileWriter(filename, true);
		PrintWriter writer = new PrintWriter(fw);
		writer.write(getSerialNumber() + ";" + getName() + ";" + getBrand() + ";" + getPrice()+ ";"+ getAvailableCount() + ";" +getMinimumAge() + ";" + getClassification()+"\n");

		writer.close();
	}


}
