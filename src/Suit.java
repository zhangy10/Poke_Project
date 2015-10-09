/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName: Suit
 * 
 *             Oct 4, 2015
 * 
 * @Description: This Enum type represent the suit of each card in Poker game.
 *               It contains 4 different types which indicate the different
 *               patterns.
 */
public enum Suit {
	C("Clubs"), D("Diamonds"), H("Hearts"), S("Spades");

	/**
	 * The full name of each pattern.
	 */
	private String value;

	/**
	 * The constructor method requires a full name for each type.
	 * <p>
	 * Note: public is not permitted for Enum type.
	 * 
	 * @param value:
	 *            the full name of suit.
	 */
	Suit(String value) {
		this.value = value;
	}

	/**
	 * Return the full name of each pattern.
	 * 
	 * @return String: full name of each pattern.
	 */
	public String getValue() {
		return value;
	}
}
