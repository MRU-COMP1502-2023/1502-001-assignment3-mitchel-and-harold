package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Figures;
import model.Puzzles;

/**
 * Tests for Puzzles sub-class
 * @author Mithchel Chanthaseng
 */
public class PuzzlesTest {
	Puzzles puzzle1 = new Puzzles("4534534390", "Mountain", "Hasbro", (float) 7.99, 22, 8, 'L');

	@Test
	public void PuzzleTypeTest() {
		assertEquals(puzzle1.getPuzzleType(), 'L');
	}

	@Test
	public void toStringTest() {
		assertEquals(puzzle1.toString(), "Serial Number: " + "4534534390" + "  " +
				"Name: " + "Mountain" + "  " +
				"Brand: " + "Hasbro" + "  " +
				"Price: " + 7.99 + "  " +
				"Available Count: " + 22 + "  " +
				"Minimum Age: " + 8 + "  " +
				"Puzzle Type: " + 'L' + "  ");
	}
}
