package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;
import model.Toy;

/**
 * Controller of the program that connects to the GUI and manages list changes
 * @author Mitchel Chanthaseng
 */
public class ToySearch {

	//Search Toy Tab Attributes
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	private RadioButton nameRadioButton;
	@FXML
	private RadioButton typeRadioButton;
	@FXML
	private RadioButton serialNumRadioButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button clearButton;
	@FXML
	private Button buyButton;
	@FXML
	private Button refreshButton;
	@FXML 
	private TextField searchField;
	@FXML
	private ToggleGroup toggleGroup1;
	@FXML
	private ListView<Toy> toyListView;

	//Remove Toy Tab Attributes
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	private TextField removeField;
	@FXML 
	private Button searchToRemoveButton;
	@FXML 
	private Button removeButton;
	@FXML
	private Button removeTabRefreshButton;
	@FXML
	private ListView<Toy> removeToyListView;

	//Add Toy Tab Attributes
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	private RadioButton figureRadioButton;
	@FXML
	private RadioButton animalRadioButton;
	@FXML
	private RadioButton puzzleRadioButton;
	@FXML
	private RadioButton boardGameRadioButton;
	@FXML
	private ToggleGroup toggleGroup2;
	@FXML
	private TextField ageField;
	@FXML
	private TextField brandField;
	@FXML
	private TextField classificationField;
	@FXML
	private TextField countField;
	@FXML
	private TextField designersField;
	@FXML
	private TextField maxPlayersField;
	@FXML
	private TextField materialField;
	@FXML
	private TextField sizeField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField snField;
	@FXML
	private TextField minPlayersField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField nameField;
	@FXML
	private Button addToyButton;
	@FXML
	private Label status;

	//General Class Attributes
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private ArrayList<Toy> toyList = new ArrayList<>();

	private final String FILE_PATH = "./res/toys.txt";

	static int list_size;

	private static final Logger LOGGER = Logger.getLogger("Mylogger");

	/**
	 * Initializer that loads the file with all the toy information and stores it into a toy list.
	 * Once the file is read, initialize() displays all the toys in scene builder on start up.
	 * @author Mitchel Chanthaseng
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public void initialize() throws SecurityException, IOException {
		FileHandler fileHandler = new FileHandler("myLog.txt", true); // true to append
		LOGGER.addHandler(fileHandler);
	    SimpleFormatter formatter = new SimpleFormatter();
	    fileHandler.setFormatter(formatter);
	    LOGGER.info("Logging started for ToySearch");
		File txt = new File(FILE_PATH);
		String thisLine;
		String[] separateLine;
		if (txt.exists()) {
			LOGGER.info("Loading data");
			try {
				Scanner inputFile = new Scanner(txt);
				while (inputFile.hasNext()) {

					thisLine = inputFile.nextLine();
					separateLine = thisLine.split(";");
					//if figure
					if (separateLine[0].charAt(0) == '0' || separateLine[0].charAt(0) == '1') {
						char puzzleType = separateLine[6].charAt(0);
						Figures toy = new Figures(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), puzzleType);
						toyList.add(toy);

					}
					//if puzzle
					if (separateLine[0].charAt(0) == '4' || separateLine[0].charAt(0) == '5'
							|| separateLine[0].charAt(0) == '6') {
						char puzzleType = separateLine[6].charAt(0);
						Puzzles toy = new Puzzles(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), puzzleType);
						toyList.add(toy);

					}

					//if animal
					if (separateLine[0].charAt(0) == '2' || separateLine[0].charAt(0) == '3') {
						char puzzleType = ' ';
						if (separateLine[7] != null) {
							puzzleType = separateLine[7].charAt(0);
						}
						Animals toy = new Animals(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), separateLine[6], puzzleType);
						toyList.add(toy);
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
							}
							else {
								list.add(""+ separateLine[7]);
							}
						}
						catch (NullPointerException e) {
							list.add(" ");
						}

						BoardGames toy1 = new BoardGames(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), separateLine[6], list);
						toyList.add(toy1);

					}
				}

			}catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}

			list_size = toyList.size();


			//Displaying Toy List on startup
			for (Toy t: toyList) {
				toyListView.getItems().add(t);
				removeToyListView.getItems().add(t);

			}
		}
		LOGGER.info("Data Loaded");
	}
	/**
	 * creating arraylist toy to hold all the toys found by Serial number
	 * @author Harold cuellar
	 * @param type
	 * @return the list of the type of toys chosen
	 */

	public ArrayList<Toy> findBySerialNumber(String serialNumber) {
		ArrayList<Toy> toys = new ArrayList();
		for (Toy toy: toyList) {
			if(toy.getSerialNumber().equals(serialNumber)) {
				toys.add(toy);
			}
		}

		return toys;
	}
/**
 * creating Arraylist toy to hold all the toys found by name
 * @author Harold cuellar
 * @param name
 * @return
 */
	public ArrayList<Toy> findByName(String name) {
		ArrayList<Toy> toys = new ArrayList();
		for (Toy toy: toyList) {
			if(toy.getName().equals(name)) {
				toys.add(toy);
			}
		}
		return toys;
	}
/**
 * creating arraylist toy to hold all the toys found by type
 * @author Harold cuellar
 * @param type
 * @return the list of the type of toys chosen
 */
	public ArrayList<Toy> findByType(String type) {
		ArrayList<Toy> toys = new ArrayList();
		for (Toy toy: toyList) {
			char t = 0;
			if(type.equalsIgnoreCase("animal") || type.equalsIgnoreCase("animals")){
				t = 1;
			}
			else if(type.equalsIgnoreCase("boardGames") || type.equalsIgnoreCase("boardGame")){
				t = 2;
			}
			else if(type.equalsIgnoreCase("Figures") || type.equalsIgnoreCase("Figure")){
				t = 3;
			}
			else if(type.equalsIgnoreCase("puzzles") || type.equalsIgnoreCase("puzzle")){
				t = 4;
			}

			if(t == 1) {
				if (toy instanceof Animals) {
					toys.add(toy);
				} 
			}
			else
				if(t == 2) {
					if (toy instanceof BoardGames) {
						toys.add(toy);
					} 
				}
				else
					if(t == 3) {
						if (toy instanceof Figures) {
							toys.add(toy);
						} 
					}
					else
						if(t == 4) {
							if (toy instanceof Puzzles) {
								toys.add(toy);
							} 
						}
		}
		return toys;
	}
/**
 * creating a method to add toy in the ToySearch class and make the JUNIT test
 * @param toy
 */
	public void addToy(Toy toy) {
		list_size++;// implementing the list size
		toyList.add(toy);
	}

	// Searching for a Toy Tab
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Search button handler that searches the toys list and displays toys depending on which search is selected.
	 * @throws Exception			No radio button selected
	 * @author Mitchel Chanthaseng
	 */
	public void searchButtonHandler() throws Exception {

		String input = searchField.getText();
		toyListView.getItems().clear();
		LOGGER.info("Searching for: " + input);
		ArrayList<Toy> toys = null;
		if (nameRadioButton.isSelected()) {
			toys = findByName(input);
		}
		else if (serialNumRadioButton.isSelected()) {
			toys = findBySerialNumber(input);
		}
		else if (typeRadioButton.isSelected()) {
			toys = findByType(input);
		}
		else if ( ! typeRadioButton.isSelected() ||!serialNumRadioButton.isSelected() ||! nameRadioButton.isSelected()){
			LOGGER.warning("did not select a name , serial ,of type when searching");
			status.setText("Select a classification to search please ");
			throw new Exception("must select name, serial number, of type");

		}
		else {
			LOGGER.severe("unwanted issue when searching");
			throw new Exception ("unwanted error");
		}

		for (Toy toy: toys) {
			toyListView.getItems().add(toy);
			LOGGER.info("Results for toy/type toy searched :"+ toys);

		}
	}

	/**
	 * Clear button handler that clears the current display.
	 * @author Mitchel Chanthaseng
	 */
	public void clearButtonHandler() {
		toyListView.getItems().clear();
	}

	/**
	 * Refresh button handler that clears the current display and then display the entire toy list.
	 * @author Mitchel Chanthaseng
	 */
	public void refreshButtonHandler() {
		toyListView.getItems().clear();
		for (Toy t: toyList) {
			toyListView.getItems().add(t);
		}
	}

	/**
	 * Buy button handler that updates the selected toy's available count by -1 and
	 * automatically saves it into the data base when purchased.
	 * @author Mitchel Chanthaseng
	 */
	public void buyButtonHandler() {


		Toy selected = toyListView.getSelectionModel().getSelectedItem();
		LOGGER.info("User purchased: " + selected);
		if(selected.getAvailableCount() != 0) {
			selected.setAvailableCount(selected.getAvailableCount() - 1);
		}
		else {
			System.out.println("Selected item is out of stock! Try again.  \n");
			LOGGER.info("User looked for: " + selected + "\n But is out of stock.");

		}

		try {
			FileWriter fw1 = new FileWriter(FILE_PATH, false);
			PrintWriter printWriter = new PrintWriter(fw1, false);
			printWriter.flush();
			printWriter.close();
			fw1.close();
		}
		catch(Exception exception){

			System.out.println("This is not a valid option! Try again.  \n");
			LOGGER.severe("The file could not writen.");


		}

		try {
			int temp = toyList.size();
			for (int j = 0; j < temp; j++) {
				toyList.get(j).save(FILE_PATH);
			}

			ArrayList<Toy> currentSearch = new ArrayList<Toy>(toyListView.getItems());
			toyListView.getItems().clear();
			for (Toy toy: currentSearch) {
				toyListView.getItems().add(toy);
			}

		}
		catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}

	//Adding a Toy Tab
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Add toy button handler that adds a toy depending on which toy type is selected and
	 * automatically saves it into the data base when added.
	 * @author Mitchel Chanthaseng
	 */
	public void addToyButtonHandler() {

		ArrayList<Object> newToy = new ArrayList<Object>();


		String sn = snField.getText();
		String name = nameField.getText();
		String brand = brandField.getText();
		float price = Float.parseFloat(priceField.getText());
		int count = Integer.parseInt(countField.getText());
		int age = Integer.parseInt(ageField.getText());

		if (figureRadioButton.isSelected()) {
			char classification = classificationField.getText().charAt(0);
			Toy newToyF = new Figures(sn, name, brand, price, count, age, classification); 
			toyList.add(newToyF);
			status.setText("Toy has been added!");
			LOGGER.info("Figure Toy Added: " + sn +" "+ name+" " + brand +" "+'$' + price +" "+ count+" " + age+" " + classification);

		}
		else if (animalRadioButton.isSelected()) {
			String material = materialField.getText();
			char size = sizeField.getText().charAt(0);
			Toy newToyF = new Animals(sn, name, brand, price, count, age, material, size); 
			toyList.add(newToyF);
			status.setText("Toy has been added!");
			LOGGER.info("Animal  Toy Added: " + sn +" "+ name+" " + brand +" "+'$' + price +" "+ count+" " + age+" " + material+" "  +size);


		}
		else if (puzzleRadioButton.isScaleShape()) {
			char type = typeField.getText().charAt(0);
			Toy newToyF = new Puzzles(sn, name, brand, price, count, age, type); 
			toyList.add(newToyF);
			status.setText("Toy has been added!");
			LOGGER.info("Puzzle Toy Added: " + sn +" "+ name+" " + brand +" "+'$' + price +" "+ count+" " + age+" " + type);

		}
		else if (boardGameRadioButton.isSelected()) {
			List<String> designerlist = null;
			String minPlayers = minPlayersField.getText();
			String maxPlayers = maxPlayersField.getText();
			String numOfPlayers = minPlayers + "-" + maxPlayers;
			String[] designers = designersField.getText().split(",");
			for (String person: designers) {
				designerlist.add(name);
			}
			Toy newToyF = new BoardGames(sn, name, brand, price, count, age, numOfPlayers, designerlist); 
			toyList.add(newToyF);
			status.setText("Toy has been added!");
			LOGGER.info("BoardGame Toy Added: " + sn +" "+ name+" " + brand +" "+'$' + price +" "+ count+" " + age+" " + numOfPlayers+" "  +designerlist);
		}
		else {

			System.out.println("Must select a toy catagory.  \n");

		}

		try {
			FileWriter fw1 = new FileWriter(FILE_PATH, false);
			PrintWriter printWriter = new PrintWriter(fw1, false);
			printWriter.flush();
			printWriter.close();
			fw1.close();
		}
		catch(Exception exception){

			System.out.println("This is not a valid option! Try again.  \n");

		}
		try {
			int temp = toyList.size();
			for (int j = 0; j < temp; j++) {
				toyList.get(j).save(FILE_PATH);
			} 
		}
		catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}

	//Removing a Toy Tab
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Search button in remove toy tab handler that searhes the toy list for a matching serial number and displays
	 * the toy to the user if found.
	 * @author Mitchel Chanthaseng
	 */
	public void searchToRemoveButtonHandler() {
		String input = removeField.getText();
		removeToyListView.getItems().clear();
		LOGGER.info("Searching SN to remove: " + input);
		for (Toy toy: toyList) {
			if(toy.getSerialNumber().equals(input)) {
				removeToyListView.getItems().add(toy);
			}


		}
	}

	/**
	 * Remove button handler that will remove the selected toy from the toy list and display.
	 * @author Mitchel Chanthaseng
	 */
	public void removeButtonHandler() {
		Toy selected = removeToyListView.getSelectionModel().getSelectedItem();
		ArrayList<Toy> toyListCopy = (ArrayList<Toy>)toyList.clone();
		
		for (Toy toy: toyListCopy) {
			if (selected == toy) {
				toyList.remove(toy);
				removeToyListView.getItems().remove(toy);
				LOGGER.info("Removing toy: "+ toy);

			}
		}

		try {
			FileWriter fw1 = new FileWriter(FILE_PATH, false);
			PrintWriter printWriter = new PrintWriter(fw1, false);
			printWriter.flush();
			printWriter.close();
			fw1.close();
		}
		catch(Exception exception){

			System.out.println("This is not a valid option! Try again.  \n");

		}

		try {
			int temp = toyList.size();
			for (int j = 0; j < temp; j++) {
				toyList.get(j).save(FILE_PATH);
			} 
		}
		catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Refresh button in remove tab handler that will clear the current display and display the entire toy list. 
	 */
	public void removeTabRefreshButtonHandler() {
		removeToyListView.getItems().clear();
		for (Toy t: toyList) {
			removeToyListView.getItems().add(t);

		}
	}
}

