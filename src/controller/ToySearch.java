package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.InvalidIntException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;
import model.Toy;

public class ToySearch {

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	private TextField removeField;
	
	@FXML 
	private Button removeButton;
	
	@FXML
	private ListView<Toy> removeToyListView;

	private ArrayList<Toy> toyList = new ArrayList<>();

	private final String FILE_PATH = "./res/toys.txt";

	static int list_size;

	public void initialize() {
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
				
			}
		}
	}

// Searching for a Toy Tab
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void searchButtonHandler() throws Exception {

		String input = searchField.getText();
		toyListView.getItems().clear();

		for (Toy toy: toyList) {
			if (nameRadioButton.isSelected()) {
				if(toy.getName().equals(input)) {
					toyListView.getItems().add(toy);
				}	
			}
			else if (serialNumRadioButton.isSelected()) {
				if(toy.getSerialNumber().equals(input)) {
					toyListView.getItems().add(toy);
				}
			}

			else if (typeRadioButton.isSelected()) {
				char t = 0;
				if(input.equalsIgnoreCase("animal") || input.equalsIgnoreCase("animals")){
					t = 1;
				}
				else if(input.equalsIgnoreCase("boardGames") || input.equalsIgnoreCase("boardGame")){
					t = 2;
				}
				else if(input.equalsIgnoreCase("Figures") || input.equalsIgnoreCase("Figure")){
					t = 3;
				}
				else if(input.equalsIgnoreCase("puzzles") || input.equalsIgnoreCase("puzzle")){
					t = 4;
				}

				if(t == 1) {
					if (toy instanceof Animals) {
						toyListView.getItems().add(toy);
					} 
				}
				else
					if(t == 2) {
						if (toy instanceof BoardGames) {
							toyListView.getItems().add(toy);
						} 
					}
					else
						if(t == 3) {
							if (toy instanceof Figures) {
								toyListView.getItems().add(toy);
							} 
						}
						else
							if(t == 4) {
								if (toy instanceof Puzzles) {
									toyListView.getItems().add(toy);
								} 
							}
			}
			else {
				throw new Exception("must select name, serial number, of type");
			}
		}
	}

	public void clearButtonHandler() {
		toyListView.getItems().clear();
	}

	public void refreshButtonHandler() {
		toyListView.getItems().clear();
		for (Toy t: toyList) {
			toyListView.getItems().add(t);
		}
	}

	public void buyButtonHandler() {
		Toy selected = toyListView.getSelectionModel().getSelectedItem();
		if(selected.getAvailableCount() != 0) {
		selected.setAvailableCount(selected.getAvailableCount() - 1);
		}
		else {
			System.out.println("Selected item is out of stock! Try again.  \n");
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//Adding a Toy Tab
////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//Removing a Toy Tab
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeButtonHandler() {
		String input = searchField.getText();
		for (Toy toy: toyList) {
				if(toy.getSerialNumber().equals(input)) {
					toyListView.getItems().add(toy);
				}
			}
			
		Toy selected = toyListView.getSelectionModel().getSelectedItem();
		for (Toy toy: toyList) {
			if (toy == selected) {
				toyList.remove(toy);
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}