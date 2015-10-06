/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Suit
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public enum Suit {
	C("Clubs"), D("Diamonds"), H("Hearts"), S("Spades");

	private String value;

	Suit(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
