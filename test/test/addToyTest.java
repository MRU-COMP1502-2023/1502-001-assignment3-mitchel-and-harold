package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import controller.AppManager;
import exceptions.InvalidIntException;
import model.BoardGames;
import model.Toy;
import view.AppMenu;
/**
 * Testing the AddToy method from The AppManager class.
 * @author Harold Cuellar
 *
 */
public class addToyTest {
	
	 AppManager appManager;
	 Toy toy;

	 /**
	  * method that initializes the appManager and toy instance variables with appropriate values for the test.
	  * @before 
	  * @throws Exception
	  */
	 @Before
	 public void setUp() throws Exception {
		 appManager = new AppManager(true);
			toy = new BoardGames("2000000000", "Elephant", "Test", 19.99F, 19, 3, "2-4", Arrays.asList("Author A", "Author B"));
			//creating a new instance of the BoardGames class and initializes it with preset values.
			
			appManager.addToy("2000000000", "Elephant", "Test", 19.99F, 19, 3, 2, 4, "Author A,Author B");
           // adding a toy to the AppManager with the  values as the toy instance variable created in the previous instance "toy" of BoardGames class.
	 }

	/**
	 * Method testing the addToy method from AppManager.
	 * @throws InvalidIntException
	 */
	@Test
	public void testAddToy() throws InvalidIntException {
	
		Toy lastToy = appManager.getLastInserted();//retrieves the last toy tahta was added to the toy manager that should be the one that we created in the setUp method.
		assertEquals(toy.toString(), lastToy.toString());//If they are equal, the test passes.
	}
}