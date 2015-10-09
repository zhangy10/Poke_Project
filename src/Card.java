/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName: Card
 * 
 *             Oct 4, 2015
 * 
 * @Description: This abstract Card class type represent one single poker card
 *               which contains two stuffs, Rank and Suit. Also, this class
 *               implements the interface of Comparable for sorting a group of
 *               cards. This class is defined as an immutable class.
 */
public final class Card implements Comparable<Card> {
	/* To represent the pattern of each card */
	private Suit suit;
	/* To represent the value of each card */
	private Rank rank;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * This constructor is a copy constructor used to make the new object an
	 * exact copy of the input argument
	 * 
	 * @param card:
	 *            input a Card type argument for copying a new Card object
	 */
	public Card(Card card) {
		this(card.getRank(), card.getSuit());
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	/**
	 * Overriding the compareTo from the Comparable for sorting a group of
	 * cards. As the value of rank is the decision of the Poker game, the order
	 * of cards depends on that of ranks. Therefore, to implement this method is to
	 * recall the compareTo method of Rank.
	 * 
	 * @param another:
	 *            another object which needs to compare with the current object.
	 * 
	 * @return negative: this less than another; positive: this greater than
	 *         another; 0: equal.
	 */
	@Override
	public int compareTo(Card another) {
		return rank.compareTo(another.getRank());
	}
}
