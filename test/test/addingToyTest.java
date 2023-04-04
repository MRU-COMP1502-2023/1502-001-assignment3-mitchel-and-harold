package test;

import controller.AppManager;
import exceptions.CustomException;
import exceptions.InvalidIntException;
import model.BoardGames;
import model.Toy;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class addingToyTest  {

	private AppManager appManager ;
	private List<Toy> toyType;
	@Before
	public void setUp()  {
	    appManager = new AppManager(); 
	    toyType = new ArrayList<>();
	}


	@Test
	public void testAddToy()  {
		// Manually set the input for System.in
		String input = "1234567890\n"  // serial number
				+ "Toy Name\n"  // toy name
				+ "Brand Name\n"  // brand name
				+ "10.5\n"  // toy price
				+ "5\n"  // available count
				+ "6\n"  // appropriate age
				+ "1\n"  // minimum number of players
				+ "4\n"  // maximum number of players
				+ "Designer1,Designer2\n";  // designers
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		// Call the method to be tested
		appManager.addToy();

		// Assert the results
		assertEquals(1, toyType.size());
		assertTrue(toyType.get(0) instanceof BoardGames);
		assertEquals("1234567890", toyType.get(0).getSerialNumber());
		assertEquals("Toy Name", toyType.get(0).getName());
		assertEquals("Brand Name", ((BoardGames) toyType.get(0)).getBrand());
		assertEquals(10.5f, toyType.get(0).getPrice(), 0.01f);
		assertEquals(5, toyType.get(0).getAvailableCount());
		assertEquals(6, ((BoardGames) toyType.get(0)).getMinimumAge());
		assertEquals("1-4", ((BoardGames) toyType.get(0)).getNumOfPlayers());
		assertEquals("Designer1", ((BoardGames) toyType.get(0)).getDesigners().get(0));
		assertEquals("Designer2", ((BoardGames) toyType.get(0)).getDesigners().get(1));
	}}