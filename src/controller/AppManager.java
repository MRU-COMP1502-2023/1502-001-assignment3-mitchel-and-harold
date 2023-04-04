package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.CustomException;
import exceptions.InvalidIntException;
import exceptions.PlayerRangeException;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;
import model.Toy;
import view.AppMenu;

/** Main controller of store and managing menu inputs
 * @author Mitchel Chanthaseng
 * @author Harold Cuellar
 */
public class AppManager {

	static ArrayList<Toy> toyType = new ArrayList<>();
	static int list_size;
	private final String FILE_PATH = "./res/toys.txt";
	private AppMenu am = new AppMenu();

	public AppManager() throws Exception {
		loadToys();
		am.FirstTimeDisplay();
		launchApplication();
	}

	/**  Use Toy class and convert each line from .txt file into a toy type based on
	 *  first number of serial number and keep them in an array list.
	 * @author Harold Cuellar
	 */
	public void loadToys(){

		File txt = new File(FILE_PATH);
		String thisLine;
		String[] separateLine;
		if (txt.exists()) {

			try {
				Scanner inputFile = new Scanner(txt);
				while (inputFile.hasNext()) {

					thisLine = inputFile.nextLine();
					separateLine = thisLine.split(";");
					//if figure
					if (separateLine[0].charAt(0) == '0' || separateLine[0].charAt(0) == '1') {
						char puzzleType = separateLine[6].charAt(0);
						Figures toy = new Figures(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), puzzleType);
						toyType.add(toy);
					}
					//if puzzle
					if (separateLine[0].charAt(0) == '4' || separateLine[0].charAt(0) == '5'
							|| separateLine[0].charAt(0) == '6') {
						char puzzleType = separateLine[6].charAt(0);
						Puzzles toy = new Puzzles(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), puzzleType);
						toyType.add(toy);
					}

					//if animal
					if (separateLine[0].charAt(0) == '2' || separateLine[0].charAt(0) == '3') {
						char puzzleType = ' ';
						if (separateLine[7] != null) {
							puzzleType = separateLine[7].charAt(0);
						}
						Animals toy = new Animals(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), separateLine[6], puzzleType);
						toyType.add(toy);
					}

					//if boardGames
					if (separateLine[0].charAt(0) == '7' || separateLine[0].charAt(0) == '8'
							|| separateLine[0].charAt(0) == '9') {

						List<String> list = new ArrayList<>();
						try {
							if(separateLine[7].contains(",")) {
								String[] st = separateLine[7].split(",");
								for (String d: st) {
									list.add(d);
								}
								//System.out.println(" data2 : " + st[0] +" , " + st[1]);
							}
							else
							{
								list.add(""+ separateLine[7]);
							}
						}catch (NullPointerException e)
						{
							list.add(" ");
						}

						//System.out.println(" data : " + list.toString());

						BoardGames toy1 = new BoardGames(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), separateLine[6], list);
						toyType.add(toy1);

					}
				}

			}catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}

			list_size = toyType.size();
		}
	}
	/**
	 * Calls AppMenu method to display menu and validates the input when searching for a toy.
	 * @throws InvalidIntException
	 * @author Mitchel Chanthaseng
	 */
	public void launchApplication() throws InvalidIntException {

		try {
			String userInput = am.newMainMenu();
			int userInputInt = Integer.parseInt(userInput);

			if (userInputInt < 1 || userInputInt > 4) {
				System.out.println("\n" + userInputInt + " is not a valid option! Try again.\n");
				launchApplication();
			}

			switch (userInputInt) {
			case 1:
				search();
				break;
			case 2:
				addToy();
				break;
			case 3:
				removeToy();
				break;
			case 4:
				save();
				break;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("\nThis is not an Integer Number! Try Again.\n");
			launchApplication();
		}
	}

	/**
	 * Adding toy depending on the serial number. Checks if the serial number is correct format
	 * and whether it exists or not yet.
	 * @author Harold Cuellar
	 */
	public void addToy() throws InvalidIntException {

		try {
			String serialNumber = am.toySearchPrompt("Serial Number");
			if (serialNumber.length() != 10) {
				System.out.println("The Serial Number should contain 10 digits only! try again .");
				addToy();
			}
			if (validate(serialNumber)) {
				System.out.println("The Serial Number should contain only digits! try again .");
				addToy();
			}
			if (isSerialAlreadyExists(serialNumber)) {
				System.out.println("The Serial Number already exists! try again .");
				addToy();
			}

			String name = am.toySearchPrompt(" Toy Name");
			System.out.println("Enter Brand Name:");
			String brand = new Scanner(System.in).nextLine();
			System.out.println("Enter Toy Price:");
			float price = new Scanner(System.in).nextFloat();
			if(price < 0) // if price is negative ....
			{
				throw new CustomException(price);
				
			}
			System.out.println("Enter Available Count:");
			int availableCount = new Scanner(System.in).nextInt();
			System.out.println("Enter Appropriate Age:");
			int age = new Scanner(System.in).nextInt();
			System.out.println("Enter Minimum Number Of Players:");
			int mininumberofplayers = new Scanner(System.in).nextInt();
			System.out.println("Enter Maximum Number Of Players:");
			int maxnumberofplayer = new Scanner(System.in).nextInt();
			if (maxnumberofplayer < mininumberofplayers) {
				
				throw new PlayerRangeException();
				
			}
			List<String> designers = new ArrayList<>();
			System.out.println("Enter Designers: (User ',' to separate the names if there is more than one name.) ");
			String numOfDesigners = new Scanner(System.in).nextLine();
			String[] list = numOfDesigners.trim().split(",");
			for (String st : list) {
				if (st != null) {
					designers.add(st);
				}
			}
			BoardGames boardGames = new BoardGames(serialNumber, name, brand, price, availableCount, age,mininumberofplayers+"-"+maxnumberofplayer, designers);
			toyType.add(boardGames);

			System.out.println("\nToy Added.\n");
			launchApplication();
		}
		catch (NumberFormatException e) {
			System.out.println("\nThis is not an Integer Number! Try Again.\n");
			search();
		} 
		catch (CustomException e) {
			e.printStackTrace();
		}
		catch (PlayerRangeException e) {
			e.printStackTrace();
		}

	}

	/**
	 *Method called in case the serial already exists
	 * @param str
	 * @return true/false depending on # entered.
	 * @author Harold Cuellar
	 */
	public boolean isSerialAlreadyExists(String str)
	{
		for (int i = 0; i < toyType.size(); i++) {
			if(str.equals(toyType.get(i).getSerialNumber()))
			{
				return true;
			}else continue;
		}
		return false;
	}

	/**
	 * Validates that what is entered is a number that is correctly formated 
	 * @param str
	 * @return true/false depending if its numbers or not.
	 * @author Harold Cuellar
	 */
	public boolean validate(String str)
	{
		for (int i = 0; i < str.length(); i++) {
			if(Character.isAlphabetic(str.charAt(i)))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove toy from the toys.txt file after showing user the toy based on serial number entered.
	 * If no toy match the Serial number then it shows message
	 * @throws InvalidIntException
	 * @author Harold Cuellar
	 */
	public void removeToy() throws InvalidIntException {

		String serialNumberString = am.toySearchPrompt("Enter Serial Number:");
		Toy toy_ = null;
		boolean f = false;
		if(!isSerialAlreadyExists(serialNumberString))
		{
			System.out.println("The Serial Number doesn't exists! try again .");
			launchApplication();
		}
		else
		{
			for (Toy toy: toyType) {
				if(toy.getSerialNumber().equals(serialNumberString))
				{
					toy_ = toy;
					System.out.println("This Item Found: ");
					printToString(serialNumberString.charAt(0),toy);
					f = true;
					break;
				} else continue;
			}

			if(f) {

				System.out.println("Do you want to remove it? (Y/N)");
				String option = new Scanner(System.in).nextLine();
				if (option.equalsIgnoreCase("Y")) {
					toyType.remove(toy_);
					System.out.println("Item Removed");
					String enterkey = am.toySearchPrompt("Press Enter Key to Continue...");
					if (enterkey.equals("")) {
						launchApplication();
					}
				} else {
					launchApplication();
				}
			}
			else
			{
				System.out.println("This Item Not Found! try again.");
				launchApplication();
			}
		}
	}
	/**
	 * Method to clear the file 
	 * @author Harold Cuellar
	 */
	public void clearFile() {
		try {
			FileWriter fw1 = new FileWriter(FILE_PATH, false);
			PrintWriter printWriter = new PrintWriter(fw1, false);
			printWriter.flush();
			printWriter.close();
			fw1.close();
		}
		catch(Exception exception){
		}
	}

	/**
	 * Method that saves toy data into toy.txt file 
	 * @author Harold Cuellar
	 */
	public void save() {
		clearFile();
		System.out.println("Saving data into Database....");
		try {
			int temp = toyType.size();
			for (int j = 0; j < temp; j++) {
				toyType.get(j).save(FILE_PATH);
			}
			System.out.println("************* THANKS FOR VISITING US! *************");

			System.exit(0);
		}
		catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Calls AppMenu method to show sub-menu and validates user input when choosing to search
	 * @author Mitchel Chanthaseng
	 * @throws InvalidIntException
	 */
	public void search() throws InvalidIntException {

		try {
			String userInput = am.searchToys();
			int userInputInt = Integer.parseInt(userInput);

			if (userInputInt < 1 || userInputInt > 4) {
				System.out.println("\n" + userInputInt + " is not a valid option! Try again.\n");
				search();
			}

			switch (userInputInt) {
			case 1:
				serialSearch();
				break;
			case 2:
				nameSearch();
				break;
			case 3:
				typeSearch();
				break;
			case 4:
				launchApplication();
				break;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("\nThis is not an Integer Number! Try Again.\n");
			search();
		}
	}

	/**
	 * when a serial number is entered it searches based on the input and outputs the toy(s)
	 * @author Mitchel Chanthaseng
	 * @author Harold Cuellar
	 */
	public void serialSearch() {
		try {

			String serialNumberString = am.toySearchPrompt("Toy Serial Number:");
			int count = 0;
			boolean isF = false;
			Toy selected = null;
			for (Toy toy: toyType) {
				if(toy.getSerialNumber().equals(serialNumberString))
				{
					selected = toy;
					count++;
					System.out.println("[" + count +"] "+ toy.toString());
					isF = true;
					break;
				} else continue;
			}

			if(isF == true)
			{
				count++;
				System.out.println("[" + count +"] Back To SearchMenu");
				System.out.println("Enter option number to purchase: ");
				int input = new Scanner(System.in).nextInt();
				if(input == count)
				{
					search();
				}
				else
				{
					try{
						if(isSerialAlreadyExists(""+selected.getSerialNumber())) {
							if(selected.getAvailableCount() != 0) {
								selected.setAvailableCount(selected.getAvailableCount() - 1);
								System.out.println("Enter Transaction Successfully Terminated!\n");
							}
							else {
								System.out.println("Item is out of stock!\n");
							}
						}
						else
						{
							System.out.println("Item is out of stock!\n");
						}
					}catch (IndexOutOfBoundsException ex)
					{
						System.out.println("This is not a valid option! Try again.\n");
					}
				}
				String enterkey = am.toySearchPrompt("Press Enter Key to Continue...");
				if(enterkey.equals("")){
					launchApplication();
				}
			}

			if(isF == false)
			{
				System.out.println("Toy not found ");
			}

			launchApplication();

		} catch (NumberFormatException q) {
			System.out.println("\nThis serial number is not an Integer Number! Try Again.\n");
			serialSearch();
		} catch (InvalidIntException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * for each toy, if the name matches, print a tostring with 1 line and ask to purchase
	 * @author Harold Cuellar
	 */
	public void nameSearch() {
		try {

			String Name = am.toySearchPrompt("Toy Name");
			List<Toy> templist = new ArrayList<>();
			int count = 0;
			boolean isF = false;
			for (Toy toy: toyType) {
				if(toy.getName().equalsIgnoreCase(Name) || toy.getName().contains(Name))
				{
					count++;
					templist.add(toy);
					String cat = "Category:";
					if(toy instanceof BoardGames)
					{
						cat += "BoardGames";
					}
					else if(toy instanceof Animals)
					{
						cat += "Animals";
					}else 	if(toy instanceof Figures)
					{
						cat += "Figures";
					}else 	if(toy instanceof Puzzles)
					{
						cat += "Puzzles";
					}

					System.out.println("[" + count +"] "+ cat +", "+ toy.toString());
					isF = true;
				} else continue;
			}

			if(isF == true)
			{
				count++;
				System.out.println("[" + count +"] Back To SearchMenu");
				System.out.println(" Enter option number to purchase: ");
				int input = new Scanner(System.in).nextInt();
				if(input == count)
				{
					search();
				}
				else
				{
					try {
						Toy tm = templist.get(input-1);
						if (isSerialAlreadyExists("" + tm.getSerialNumber())) {
							if(tm.getAvailableCount() != 0) {
								tm.setAvailableCount(tm.getAvailableCount() - 1);
								System.out.println("Enter Transaction Successfully Terminated!\n");
							}
							else
								System.out.println("Item is out of stock!\n");
						} else {
							System.out.println("Item is out of stock1  !\n");
						}
					}catch (IndexOutOfBoundsException ex)
					{
						System.out.println("This is not a valid option! Try again.  !\n");
					}
				}
				String enterkey = am.toySearchPrompt("Press Enter Key to Continue...");
				if(enterkey.equals("")){
					launchApplication();
				}
			}

			if(isF == false)
			{
				System.out.println("Toy not found ");
			}

			launchApplication();

		} catch (NumberFormatException q) {
			System.out.println("\nThis serial number is not an Integer Number! Try Again.\n");
			serialSearch();
		} catch (InvalidIntException e) {
			throw new RuntimeException(e);
		}


	}
	/**
	 * Depending on user input, make if statements for serial number inside a for (Toy toy: toyType) loop]
	 * @author Mithchel Chanthaseng
	 * @author Harold Cuellar
	 */
	public void typeSearch() {

		try {

			String toyType_ = am.toySearchPrompt("Toy Type");
			List<Toy> templist = new ArrayList<>();
			int t = 0;
			if(toyType_.equalsIgnoreCase("animal") || toyType_.equalsIgnoreCase("animals"))
			{
				t = 1;
			}
			else if(toyType_.equalsIgnoreCase("boardGames") || toyType_.equalsIgnoreCase("boardGame"))
			{
				t = 2;
			}
			else if(toyType_.equalsIgnoreCase("Figures") || toyType_.equalsIgnoreCase("Figure"))
			{
				t = 3;
			}
			else if(toyType_.equalsIgnoreCase("puzzles") || toyType_.equalsIgnoreCase("puzzle"))
			{
				t = 4;
			}
			int count = 1;
			boolean isF = false;
			for (Toy toy: toyType) {
				if(t == 1) {
					if (toy instanceof Animals) {
						templist.add(toy);
						System.out.println("[" + count +"] "+ toy.toString());
						count++;
						isF = true;
					} 
				}
				else
					if(t == 2) {
						if (toy instanceof BoardGames) {
							templist.add(toy);
							System.out.println("[" + count +"] "+ toy.toString());
							count++;
							isF = true;
						} 
					}
					else
						if(t == 3) {
							if (toy instanceof Figures) {
								templist.add(toy);
								System.out.println("[" + count +"] "+ toy.toString());
								count++;
								isF = true;
							} 
						}
						else
							if(t == 4) {
								if (toy instanceof Puzzles) {
									templist.add(toy);
									System.out.println("[" + count +"] "+ toy.toString());
									count++;
									isF = true;
								} 
							}
			}

			if(isF == true)
			{

				System.out.println("[" + count +"] Back To SearchMenu");
				System.out.println("Enter option number to purchase: ");
				int input = new Scanner(System.in).nextInt();
				if(input == count)
				{
					search();
				}
				else
				{
					try {
						Toy tm = templist.get(input - 1);
						if (input <= count) {
							if(tm.getAvailableCount() > 0) {
								tm.setAvailableCount(tm.getAvailableCount() - 1);
								System.out.println("Enter Transaction Successfully Processed!\n");
							}
							else
								System.out.println("Item is out of stock!\n");
						} else {
							System.out.println("Item is out of stock !\n");
						}
					}catch (IndexOutOfBoundsException ex)
					{
						System.out.println("This is not a valid option! Try again.\n");
					}
				}
				String enterkey = am.toySearchPrompt("Press Enter Key to Continue...");
				if(enterkey.equals("")){
					launchApplication();
				}
			}

			if(isF == false)
			{
				System.out.println("Toy not found ");
			}

			launchApplication();

		} catch (NumberFormatException q) {
			System.out.println("\nThis serial number is not an Integer Number! Try Again.\n");
			serialSearch();
		} catch (InvalidIntException e) {
			throw new RuntimeException(e);
		}

	}
	/**
	 * toString method that prints depending on the first 2 digits of the serial number entered.
	 * @author Harold Cuellar
	 * @param separateLine
	 * @param toy
	 */
	public void printToString(char separateLine, Toy toy)
	{
		//if figure
		if (separateLine == '0' || separateLine == '1') {
			Figures figure = (Figures) toy;
			System.out.println(figure.toString());
		}

		//if puzzle
		if (separateLine == '4' || separateLine == '5'
				|| separateLine== '6') {
			Puzzles puzzles = (Puzzles) toy;
			System.out.println(puzzles.toString());
		}

		//if animal
		if (separateLine == '2' || separateLine == '3') {
			Animals animals = (Animals) toy;
			System.out.println(animals.toString());
		}

		//if boardGames
		if (separateLine == '7' || separateLine == '8'
				|| separateLine == '9') {
			BoardGames boardGames = (BoardGames)toy;
			System.out.println(boardGames.toString());
		}

	}
}