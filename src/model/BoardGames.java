package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * BoardGames sub-class of Toy class
 * @author Mithchel Chanthaseng
 */
public class BoardGames extends Toy{
	private String numOfPlayers;
	private List<String> designers;

	/**
	 * BoardGames constructor that also takes numOfPlayers and designers arguments
	 * Complete with getter and setter for unique attributes
	 * @author Mithchel Chanthaseng
	 */
	public BoardGames(String serialNumber, String name, String brand, float price, int availableCount, int minimumAge,
			String numOfPlayers, List<String> designers) {
		super(serialNumber, name, brand, price, availableCount, minimumAge);
		this.numOfPlayers = numOfPlayers;
		this.designers = designers;
	}

	/**
	 * @author Mitchel Chanthaseng
	 * @return
	 */
	public String getNumOfPlayers() {
		return numOfPlayers;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @param numOfPlayers
	 */
	public void setNumOfPlayers(String numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @return
	 */
	public List<String> getDesigners() {
		return designers;
	}
	
	/**
	 * @author Mitchel Chanthaseng
	 * @param designers
	 */
	public void setDesigners(List<String> designers) {
		this.designers = designers;
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
				"Number of Players: " + getNumOfPlayers() + "  " +
				"Designers: " + getDesigners().toString().replaceAll("\\[","").replaceAll("\\]","") + "";
	}

	//Overrides version of Toy's abstract save() method.
	@Override
	public void save(String filename) throws IOException { 
		FileWriter fw = new FileWriter(filename, true);
		PrintWriter writer = new PrintWriter(fw);
		writer.write(getSerialNumber() + ";" + getName() + ";" + getBrand() + ";" + getPrice()+ ";"+ getAvailableCount() + ";" +getMinimumAge() + ";" + getNumOfPlayers() + ";" +getDesigners().toString().replaceAll("\\[","").replaceAll("\\]","")+"\n");
		writer.close();

	}

}
