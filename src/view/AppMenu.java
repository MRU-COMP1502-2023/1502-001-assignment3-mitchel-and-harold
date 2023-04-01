package view;

import java.util.Scanner;
/**
 *  This class will be used to show the menus and sub menus to the user
 *  It also prompts the user for the inputs and validates them 
 * @throws Exception
 * @author Mitchel Chanthaseng
 */
public class AppMenu {
	private Scanner input;

	/**AppMenu Constructor that creates scanner
	 * @author Mitchel Chanthaseng
	 * @throws Exception
	 */
	public AppMenu() throws Exception {
		input = new Scanner(System.in);
	}

	/**Prints first time banner welcome message
	 * @author Mitchel Chanthaseng
	 */
	public void FirstTimeDisplay() {
		System.out.println(String.format("*****************************************\n" +
				"*     WELCOME TO TOY STORE COMAPNY!     *\n" + 
				"*****************************************\n"));
	}

	/**Prints main menu
	 * @author Mitchel Chanthaseng
	 */
	public String newMainMenu() {
		String userInput = "";
		System.out.println("How May We Help you?\n\n" +
				"(1)\tSearch Inventory and Purchase Toys\n" +
				"(2)\tAdd New Toy\n" +
				"(3)\tRemove Toy\n" +
				"(4)\tSave & Exit\n\n" +
				"Enter an Option:");

		userInput = input.nextLine();
		return userInput;
	}

	/**Prints search sub-menu
	 * @author Mitchel Chanthaseng
	 */
	public String searchToys() {
		String userInput = "";
		System.out.println("Find Toys With: \n\n" +
				"(1)\tSerial Number(SN)\n" +
				"(2)\tToy Name\n" +
				"(3)\tToy Type\n" +
				"(4)\tBack to Main Menu");

		userInput = input.nextLine();
		return userInput;
	}

	/**Prints user prompt depending on how they want to search for the toy
	 * and returns a string 
	 * @author Mitchel Chanthaseng
	 * @param type
	 * @return userInput
	 */
	public String toySearchPrompt(String type) {
		String userInput = "";
		System.out.println("Enter " + type + ": ");

		userInput = input.nextLine();
		return userInput;
	}

	/**Prints user prompt depending on how they want to search for the toy
	 * and returns an int
	 * @author Mitchel Chanthaseng
	 * @param type
	 * @return userInput
	 */
	public int toySearchPromptInt(String type) {
		int userInput = 0;
		userInput = input.nextInt();
		return userInput;
	}

}



