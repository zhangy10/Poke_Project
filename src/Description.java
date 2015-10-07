
/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Description
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public final class Description {
	private CardType cardType;
	private Rank rank1;
	private Rank rank2;

	public Description(CardType cardType, Rank rankest) {
		this(cardType, rankest, null);
	}

	public Description(CardType cardType, 
			Rank rank1, Rank rank2) {
		this.cardType = cardType;
		this.rank1 = rank1;
		this.rank2 = rank2;
	}

	public CardType getCardType() {
		return cardType;
	}

	public Rank getRankest() {
		return rank1;
	}

	public Rank getRank2() {
		return rank2;
	}

	@Override
	public String toString() {
		return String.format(cardType.toString(), rank1.getName(),
				rank2 == null ? "" : rank2.getName());
	}
}
