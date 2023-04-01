package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Animals;
import model.Toy;

/**
 * Tests for Animals sub-class
 * @author Mithchel Chanthaseng
 */
	public class AnimalsTest {
	Animals animal1 = new Animals("2234567890", "Tiger", "Hasbro", (float) 9.99, 10, 4, "Plastic", 'M');

	@Test
	public void AvailableCountTest() {
		assertEquals(animal1.getAvailableCount(), 10);
	}

	@Test
	public void materialTest() {
		animal1.setMaterial("Cotton");
		assertEquals(animal1.getMaterial(), "Cotton");
	}
}
