/**
 * This Description class represents an abstract data structure of each
 * classification's description from specification. It contains three instance
 * variables which are also related to the expected output of each type
 * description.
 * <p>
 * This class is an immutable class.
 * 
 * @author Yu Zhang
 * 
 * @ClassName Description
 * 
 *            Oct 4, 2015
 */
public final class Description {
    /**
     * Representing the type of each hand of cards. The 9 kinds of specific
     * types are declared in this Card class.
     */
    private CardType cardType;

    /**
     * Declaring this variable for representing the highest rank of cards.
     */
    private Rank rank1;

    /**
     * Declaring this variable for representing the second pair ranks.
     */
    private Rank rank2;

    /**
     * Overloading the constructor method for those types which do not contain a
     * second pair ranks.
     * 
     * @param cardType:
     *            the current classification of cards.
     * @param rankest:
     *            the highest rank in a hand of cards.
     */
    public Description(CardType cardType, Rank rankest) {
        this(cardType, rankest, null);
    }

    /**
     * Constructor method for FULL_HOUSE and TWO_PAIR types which contain
     * another pair ranks
     * 
     * @param cardType:
     *            the current classification of cards.
     * @param rank1:
     *            the highest rank in a hand of cards.
     * @param rank2:
     *            the second pair ranks
     */
    public Description(CardType cardType, Rank rank1, Rank rank2) {
        this.cardType = cardType;
        this.rank1 = rank1;
        this.rank2 = rank2;
    }

    /**
     * Return the current card type. As most of Enum types in Java are
     * immutable, here returning the reference card type directly is fine.
     * 
     * @return CardType: return the current card type.
     */
    public CardType getCardType() {
        return cardType;
    }

    public Rank getRankest() {
        return rank1;
    }

    public Rank getRank2() {
        return rank2;
    }

    /**
     * Overriding this method for printing formatted description regarding with
     * the specification.
     */
    @Override
    public String toString() {
        return String.format(cardType.toString(), rank1.getName(),
                rank2 == null ? "" : rank2.getName());
    }
}
