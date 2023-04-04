package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import controller.AppManager;
import model.Toy;
/**
 * Testing the remove Toy method in the AppManager class
 * @author Harold Cuellar
 *
 */
class removeToyTest {
	
	/**
	 * sets the toy serial number to 2000000000 and then tests to remove it
	 */
	
	@Test
	void test() {
		AppManager appManager = new AppManager(true);// creating a new AppManager object, which includes an empty list of toys.
		appManager.removeToy("2000000000");//calling remove toy from AppManger and adding the serial number to be removed
		Toy toy = appManager.findToyBySerialNumber("2000000000");//checks if the toy with that serial number previously added manually still exists 
		assertEquals(null, toy);// uses assertEquals() to verify that toy is null, indicating that the toy was successfully removed and passing the test
	}

}
