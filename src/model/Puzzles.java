package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Puzzles sub-class of Toy class
 * @author Mitchel Chanthaseng
 */
public class Puzzles extends Toy{
	private char puzzleType;

	/**
	 * Puzzles constructor that also takes puzzleType argument
	 * Complete with getter and setter for unique attributes
	 * @author Mitchel Chanthaseng
	 */
	public Puzzles(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge, char puzzleType) {
		super(serialNumber, name, brand, price, availableCount, minimumAge);
		this.puzzleType = puzzleType;
	}

	/**
	 * @author Mitchel Chanthaseng
	 * @return
	 */
	public char getPuzzleType() {
		return puzzleType;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @param puzzleType
	 */
	public void setPuzzleType(char puzzleType) {
		this.puzzleType = puzzleType;
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
				"Puzzle Type: " + getPuzzleType() + "  ";

	}

	//Overrides version of Toy's abstract save() method.
	@Override
	public void save(String filename) throws IOException { 
		FileWriter fw = new FileWriter(filename, true);
		PrintWriter writer = new PrintWriter(fw);
		writer.write(getSerialNumber() + ";" + getName() + ";" + getBrand() + ";" + getPrice()+ ";"+ getAvailableCount() + ";" +getMinimumAge() + ";" + getPuzzleType()+"\n");
		writer.close();

	}

}
