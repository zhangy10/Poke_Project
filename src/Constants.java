
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Constants
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public class Constants {
	/*    */
	public final static int MAX_CARDS_NUMBER = 5;
	public final static int NUM_OF_ECAH_CARDS = 2;

	/*    */
	public final static String PLAYER_DES = "Player %d: %s";
	public final static String WINS = "Player %d wins.";
	public final static String DRAW = "Players %s draw.";
	public final static String INVALID_INPUT_NUMBER = 
			"Error: wrong number of arguments; must be a multiple of 5";
	public final static String INVALID_CARD_NAME = 
			"Error: invalid card name '%s'";

	/*  */
	public final static Map<String, Suit> SUITS = new HashMap<>();
	public final static Map<String, Rank> RANKS = new HashMap<>();

	static {
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
