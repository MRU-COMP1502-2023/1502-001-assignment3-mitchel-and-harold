package exceptions;

/**Custom Exception that handles a greater minNumOfPlayers than maxNumOfPlayers
 * when adding a new toy.
 * @author Mitchel Chanthaseng
 */
public class PlayerRangeException extends Exception {
	
    /**
     * Custom exception for incorrect age range
     * @param s
     */
    public PlayerRangeException() {
        System.out.println("Maximum number of players cannot be lower then minimum number of players! Try Again.");
    }}
