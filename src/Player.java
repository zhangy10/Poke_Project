
import java.util.ArrayList;
import java.util.Collections;
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
 * @ClassName: Player
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public final class Player implements Comparable<Player> {
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

	public void shiftRank() {
		removeCard(highestRank);
		CardType cardType = descripition.getCardType();
		switch (cardType) {
			case FULL_HOUSE:
			case TWO_PAIR:
				Rank rank2 = descripition.getRank2();
				if (highestRank.compareTo(rank2) != 0) {
					highestRank = rank2;
					return;
				}
			default:
				break;
		}

		if (cards.size() >= 1) {
			highestRank = cards.get(0).getRank();
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

	private void removeCard(Rank rank) {
		Iterator<Card> iterator = cards.iterator();
		while (iterator.hasNext()) {
			if (rank.compareTo(iterator.next().getRank()) == 0) {
				iterator.remove();
			}
		}
	}

	private void identifyType() {

		Collections.sort(cards);
		highestRank = cards.get(0).getRank();

		if (CardType.isFlush(cards)) {
			descripition = new Description(CardType.FLUSH, highestRank);
		}

		if (CardType.isStraight(cards)) {
			if (descripition != null) {
				descripition = new Description(CardType.STRAIGHT_FLUSH,
						highestRank);
			}
			else {
				descripition = new Description(CardType.STRAIGHT, highestRank);
			}
		}

		if (descripition == null) {
			descripition = CardType.hasAKind(cards);
			if (descripition != null) {
				highestRank = descripition.getRankest();
			}
		}

		if (descripition == null) {
			descripition = new Description(CardType.HIGH_CARD, highestRank);
		}
	}

	public int getCompareNum() {
		return descripition.getCardType().getCompareNum();
	}

	@Override
	public int compareTo(Player another) {
		return descripition.getCardType()
				.compareTo(another.getDescripition().getCardType());
	}

	@Override
	public boolean equals(Object o) {
		Player another = (Player) o;
		return descripition.getCardType() == another.getDescripition()
				.getCardType();
	}
}
