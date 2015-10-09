import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName: Constants
 * 
 *             Oct 4, 2015
 * 
 * @Description: This Constants class is used for declaring some constant
 *               things, including string, integer and static block, to provide
 *               a quickly convenient way for accessing these constant resources
 *               in this Project.
 *               <p>
 *               Note: According to the name principles of Java, using upper
 *               letters names the final constant variables with the underlines
 *               to separate different words.
 */
public class Constants {

	/**
	 * Declaring the maximum number of a hand of cards for each player.
	 */
	public final static int MAX_CARDS_NUMBER = 5;

	/**
	 * Declaring the valid number of each card's characters
	 */
	public final static int NUM_OF_ECAH_CARDS = 2;

	/*
	 * List all the output message as the constant final String.
	 */
	public final static String PLAYER_DES = "Player %d: %s";
	public final static String WINS_DES = "Player %d wins.";
	public final static String DRAW_DES = "Players %s draw.";
	public final static String INVALID_INPUT_NUMBER = 
			"Error: wrong number of arguments; must be a multiple of 5";
	public final static String INVALID_CARD_NAME = 
			"Error: invalid card name '%s'";

	/**
	 * Using map collection is a straightforward way to build the relationship
	 * between the input Strings and the Suit or Rank types.
	 * <p>
	 * Declaring the valid range of Suit types.
	 */
	public final static Map<String, Suit> SUITS = new HashMap<>();

	/**
	 * Declaring the valid range of Rank types.
	 */
	public final static Map<String, Rank> RANKS = new HashMap<>();

	/**
	 * Using static block to initialize the rang of both types
	 */
	static {
		// the key of the map represents the upper character of inputs.
		// the value of the map represents the different enum types.
		SUITS.put("C", Suit.C);
		SUITS.put("H", Suit.H);
		SUITS.put("S", Suit.S);
		SUITS.put("D", Suit.D);

		RANKS.put("2", Rank.TWO);
		RANKS.put("3", Rank.THREE);
		RANKS.put("4", Rank.FOUR);
		RANKS.put("5", Rank.FIVE);
		RANKS.put("6", Rank.SIX);
		RANKS.put("7", Rank.SEVEN);
		RANKS.put("8", Rank.EIGHT);
		RANKS.put("9", Rank.NINE);
		RANKS.put("T", Rank.T);
		RANKS.put("J", Rank.J);
		RANKS.put("Q", Rank.Q);
		RANKS.put("K", Rank.K);
		RANKS.put("A", Rank.A);
	}
}
