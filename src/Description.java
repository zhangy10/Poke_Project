
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
	private Classification classification;
	private Rank rank1;
	private Rank rank2;

	public Description(Classification classification, Rank rankest) {
		this(classification, rankest, null);
	}

	public Description(Classification classification, 
			Rank rank1, Rank rank2) {
		this.classification = classification;
		this.rank1 = rank1;
		this.rank2 = rank2;
	}

	public Classification getClassification() {
		return classification;
	}

	public Rank getRankest() {
		return rank1;
	}

	public Rank getRank2() {
		return rank2;
	}

	@Override
	public String toString() {
		return String.format(classification.toString(), rank1.getName(),
				rank2 == null ? "" : rank2.getName());
	}
}
