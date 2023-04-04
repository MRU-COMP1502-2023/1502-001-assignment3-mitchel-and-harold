package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import controller.AppManager;
import model.BoardGames;
import model.Toy;
import controller.ToySearch;

/**
 * testing the search methods
 * @author Harold Cuellar
 *
 */
class SearchToyTest {
	 
	 /**
	  * testing for search by SerialNumber.
	  */
	@Test
	 public void testFindBySerialNumber() {
		 
		 ToySearch toySearch = new ToySearch();//creating a new instance of the ToySearch class.
			Toy toy = new BoardGames("2000000000", "Elephant", "Test", 19.99F, 19, 3, "2-4", Arrays.asList("Author A", "Author B"));
			//creating a new instance of the BoardGames class and initializes it with preset values.
			
			toySearch.addToy(toy);// adding a toy to the ToySearch instance
		 ArrayList<Toy> toys = toySearch.findBySerialNumber("2000000000");//setting the serial number to search to "2000000000" to the top of the list "toys".
		 assertEquals(toys.size(), 1);//verifying that there is only one toy that matches the search criteria. if not then its 0 and test fails.
		 assertEquals(toy, toys.get(0));//comparing the toy instance variable created earlier with the first toy in the toys list if they are equal, the test passes.
	 }
	

	 /**
	  * testing for search by Name.
	  */
	 @Test
	 public void testFindByName() {
		 
		 ToySearch toySearch = new ToySearch();
			Toy toy = new BoardGames("2000000000", "Elephant", "Test", 19.99F, 19, 3, "2-4", Arrays.asList("Author A", "Author B"));
			toySearch.addToy(toy);
		 ArrayList<Toy> toys = toySearch.findByName("Elephant");
		 assertEquals(toys.size(), 1);
		 assertEquals(toy, toys.get(0));
	 }
	 
	 /**
	  * testing for search by type.
	  */
	 @Test
	 public void testFindByType() {
		 
		 ToySearch toySearch = new ToySearch();
			Toy toy = new BoardGames("2000000000", "Elephant", "Test", 19.99F, 19, 3, "2-4", Arrays.asList("Author A", "Author B"));
			toySearch.addToy(toy);
		 ArrayList<Toy> toys = toySearch.findByType("BoardGames");
		 assertEquals(toys.size(), 1);
		 assertEquals(toy, toys.get(0));
	 }

}
