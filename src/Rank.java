
/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Rank
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public enum Rank {
	// From highest to lowest
	A(14), K(13), Q(12), J(11), T(10), NINE(9), EIGHT(8), SEVEN(7), SIX(
			6), FIVE(5), FOUR(4), THREE(3), TWO(2);

	private int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		switch (value) {
			case 14:
				return "Ace";
			case 13:
				return "King";
			case 12:
				return "Queen";
			case 11:
				return "Jack";
			default:
				return String.valueOf(value);
		}
	}

	/**
	 * 
	 * @param another
	 * @return negative means this less than another;
	 *         <p>
	 *         positive means this greater than another;
	 *         <p>
	 *         0 means equal.
	 */
	public int compareRank(Rank another) {
		return value - another.getValue();
	}
}
