/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Card
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public final class Card implements Comparable<Card> {
	private Suit suit;
	private Rank rank;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Card(Card card) {
		this(card.getRank(), card.getSuit());
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public int compareTo(Card another) {
		return rank.compareTo(another.getRank());
	}
}
