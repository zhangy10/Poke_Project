
import java.util.List;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: CardType
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public enum CardType implements Occurrence {
	// From highest to lowest
	/* */
	STRAIGHT_FLUSH(1, "%s-high straight flush"),
	/* */
	FOUR_OF_A_KIND(2, "Four %ss", 4),
	/* */
	FULL_HOUSE(2, "%ss full of %ss"),
	/* */
	FLUSH(5, "%s-high flush"),
	/* */
	STRAIGHT(1, "%s-high straight"),
	/* */
	THREE_OF_A_KIND(3, "Three %ss", 3),
	/* */
	TWO_PAIR(3, "%ss over %ss", 2),
	/* */
	ONE_PAIR(4, "Pair of %ss"),
	/* */
	HIGH_CARD(5, "%s-high");

	/**
	 * 
	 */
	private int compareNum;
	private String description;
	private int occurrence;

	CardType(int compareTimes, String description) {
		this(compareTimes, description, 0);
	}

	CardType(int compareTimes, String description, int occurrence) {
		this.compareNum = compareTimes;
		this.description = description;
		this.occurrence = occurrence;
	}

	public int getCompareNum() {
		return compareNum;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return description;
	}
	
	@Override
	public int getOccurrence() {
		return occurrence;
	}

	/**
	 * 
	 * @param cards
	 * @return
	 */
	public static boolean isFlush(List<Card> cards) {
		for (int i = 1; i < cards.size(); i++) {
			Suit suit1 = cards.get(i - 1).getSuit();
			Suit suit2 = cards.get(i).getSuit();
			if (suit1.compareTo(suit2) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param cards
	 * @return
	 */
	public static boolean isStraight(List<Card> cards) {
		for (int i = 1; i < cards.size(); i++) {
			Rank rank1 = cards.get(i).getRank();
			Rank rank2 = cards.get(i - 1).getRank();
			if (rank1.getValue() - rank2.getValue() != -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param cards
	 * @return
	 */
	public static Description hasAKind(List<Card> cards) {
		SortedOccSet<Rank> occSet = new SortedOccSet<>();
		for (Card card : cards) {
			occSet.add(card.getRank());
		}

		Rank rank = occSet.findByOccurence(FOUR_OF_A_KIND);
		if (rank != null) {
			return new Description(FOUR_OF_A_KIND, rank);
		}

		rank = occSet.findByOccurence(THREE_OF_A_KIND);
		if (rank != null) {
			Rank rank2 = occSet.findByOccurence(TWO_PAIR);
			if (rank2 != null) {
				return new Description(FULL_HOUSE, rank, rank2);
			}
			return new Description(THREE_OF_A_KIND, rank);
		}

		rank = occSet.findByOccurence(TWO_PAIR);
		if (rank != null) {
			Rank rank2 = occSet.findByOccurence(rank, TWO_PAIR);
			if (rank2 != null) {
				if (rank2.compareRank(rank) > 0) {
					return new Description(TWO_PAIR, rank2, rank);
				}
				return new Description(TWO_PAIR, rank, rank2);
			}
			return new Description(ONE_PAIR, rank);
		}

		occSet.clear();
		return null;
	}
}
