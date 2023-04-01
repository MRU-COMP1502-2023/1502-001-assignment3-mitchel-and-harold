package exceptions;

/**Custom Exception that handles input that is out of menu range.
 * @author Mitchel Chanthaseng
 */
public class InvalidIntException extends Exception {
	int input;

	public InvalidIntException(int input) {
		this.input = input;
	}

	public String toString() {
		return this.input + " is not a valid option! Try again.";
	}
}
