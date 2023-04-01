package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Animals;
import model.Figures;

/**
 * Tests for Figures sub-class
 * @author Mithchel Chanthaseng
 */
public class FiguresTest {
	Figures figure1 = new Figures("0234534390", "DeadPool", "Hasbro", (float) 15.99, 22, 8, 'A');

	@Test
	public void NameTest() {
		assertEquals(figure1.getName(), "DeadPool");
	}

	@Test
	public void ClassificationTest() {
		assertEquals(figure1.getClassification(), 'A');
	}

}
