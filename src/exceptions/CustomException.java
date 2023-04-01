package exceptions;

/**Custom exception that handles a negative price when trying to 
 * add a new toy.
 * @author Harold Cuellar
 */
public class CustomException extends Exception {
	float input;
    /**
     * custom exception for negative price
     * @param s
     */
    public CustomException(float s) {
    	this.input = s;
        System.out.println("\nPrice cannot be negative! Try Again.");
    }
}
