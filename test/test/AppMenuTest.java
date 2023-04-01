package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.AppMenu;

/**
 * Tests for AppMenu
 * @author Mithchel Chanthaseng
 */
public class AppMenuTest {

	@Test
	public void toySearchPrompTest() throws Exception {
		AppMenu am = new AppMenu();
		String userInput = am.toySearchPrompt("Name");
		// Input "Batman"
		assertEquals(userInput, "Batman");
	}
	
	@Test
	public void FirstTimeDisplayTest() throws Exception {
		AppMenu am = new AppMenu();
		am.FirstTimeDisplay();
		
		//Check to see if welcome banner is in terminal.
	}

}
