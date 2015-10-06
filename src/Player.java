
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Player
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public class Player implements Comparable<Player> {
	private int number;
	private List<Card> cards;
	private Description descripition;
	private Rank highestRank;

	public Player(int number) {
		this.number = number;
		this.cards = new ArrayList<>();
	}

	public Rank highestRank() {
		return highestRank;
	}

	public void changeNextRank() {
		Classification classification = descripition.getClassification();
		switch (classification) {
			case FULL_HOUSE:
				highestRank = descripition.getRank2();
				return;
			case TWO_PAIR:
				Rank rank2 = descripition.getRank2();
				if (!highestRank.equals(rank2)) {
					highestRank = rank2;
					return;
				}
			default:
				break;
		}

		for (Card card : cards) {
			Rank nextHigh = card.getRank();
			Rank rank1 = descripition.getRankest();
			Rank rank2 = descripition.getRank2();
			switch (classification) {
				case FOUR_OF_A_KIND:
					if (!nextHigh.equals(highestRank)) {
						highestRank = nextHigh;
						return;
					}
					break;
				case THREE_OF_A_KIND:
				case ONE_PAIR:
					if (highestRank.equals(rank1)) {
						if (!nextHigh.equals(highestRank)) {
							highestRank = nextHigh;
							return;
						}
					}
					else if (nextHigh.compareRank(highestRank) < 0) {
						highestRank = nextHigh;
						return;
					}
					break;
				case TWO_PAIR:
					if (!nextHigh.equals(rank1) && !nextHigh.equals(rank2)) {
						highestRank = nextHigh;
						return;
					}
					break;
				case FLUSH:
				case HIGH_CARD:
					if (nextHigh.compareRank(highestRank) < 0) {
						highestRank = nextHigh;
						return;
					}
					break;
				default:
					break;
			}
		}
	}

	public int getNumber() {
		return number + 1;
	}

	public Description getDescripition() {
		return descripition;
	}

	@Override
	public String toString() {
		return String.format(Constants.PLAYER_DES, getNumber(),
				descripition.toString());
	}

	public void addCard(Card card) {
		cards.add(new Card(card));
		if (cards.size() == Constants.MAX_CARDS_NUMBER) {
			identifyType();
		}
	}

	private void identifyType() {
		if (descripition != null) {
			return;
		}

		Collections.sort(cards);
		highestRank = cards.get(0).getRank();

		if (Classification.isFlush(cards)) {
			descripition = new Description(Classification.FLUSH, highestRank);
		}

		if (Classification.isStraight(cards)) {
			if (descripition != null && descripition
					.getClassification() == Classification.FLUSH) {
				descripition = new Description(Classification.STRAIGHT_FLUSH,
						highestRank);
			}
			else {
				descripition = new Description(Classification.STRAIGHT,
						highestRank);
			}
		}

		if (descripition == null) {
			descripition = Classification.hasAKind(cards);
			if (descripition != null) {
				highestRank = descripition.getRankest();
			}
		}

		if (descripition == null) {
			descripition = new Description(Classification.HIGH_CARD,
					highestRank);
		}
	}

	@Override
	public int compareTo(Player another) {
		return descripition.getClassification()
				.compareTo(another.getDescripition().getClassification());
	}

	@Override
	public boolean equals(Object o) {
		Player another = (Player) o;
		return descripition.getClassification() == another.getDescripition()
				.getClassification();
	}
}
