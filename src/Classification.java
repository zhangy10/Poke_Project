
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Classification
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public enum Classification {
	// From highest to lowest
	/* */
	STRAIGHT_FLUSH("%s-high straight flush"),
	/* */
	FOUR_OF_A_KIND("Four %ss"),
	/* */
	FULL_HOUSE("%ss full of %ss"),
	/* */
	FLUSH("%s-high flush"),
	/* */
	STRAIGHT("%s-high straight"),
	/* */
	THREE_OF_A_KIND("Three %ss"),
	/* */
	TWO_PAIR("%ss over %ss"),
	/* */
	ONE_PAIR("Pair of %ss"),
	/* */
	HIGH_CARD("%s-high");

	/**
	 * 
	 */
	private String description;

	Classification(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return description;
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
		OccurSortedSet<Rank> occSet = new OccurSortedSet<>();
		for (Card card : cards) {
			occSet.add(card.getRank());
		}

		Rank rank = findByOccurence(occSet, 4);
		if (rank != null) {
			return new Description(FOUR_OF_A_KIND, rank);
		}

		rank = findByOccurence(occSet, 3);
		if (rank != null) {
			Rank rank2 = findByOccurence(rank, occSet, 2);
			if (rank2 != null) {
				return new Description(FULL_HOUSE, rank, rank2);
			}
			return new Description(THREE_OF_A_KIND, rank);
		}

		rank = findByOccurence(occSet, 2);
		if (rank != null) {
			Rank rank2 = findByOccurence(rank, occSet, 2);
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

	private static Rank findByOccurence(OccurSortedSet<Rank> occSet,
			int occurence) {
		return findByOccurence(null, occSet, occurence);
	}

	private static Rank findByOccurence(Rank uncheckRank,
			OccurSortedSet<Rank> occSet, int occurence) {
		Iterator<Rank> iterator = occSet.iterator();
		while (iterator.hasNext()) {
			Rank another = iterator.next();
			if (another != uncheckRank
					&& occSet.getOccurrence(another) == occurence) {
				return another;
			}
		}
		return null;
	}
}
