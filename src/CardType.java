import java.util.List;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName CardType
 * 
 *            Oct 4, 2015
 * 
 * @Description This Enum CardType represents 9 different classifications of
 *              Poker game. It also offers the below features for identifying
 *              the given cards' description:
 *              <p>
 *              1) Identifying whether the cards belong to a Flush type.
 *              <p>
 *              2) Identifying whether the cards belong to a Striaght type.
 *              <p>
 *              3) Identifying whether the same ranks exist in the given cards
 *              to return a n of AKind type.
 *              <p>
 *              Note:
 *              <p>
 *              1) The idea of identifying a n of AKind type is to use the given
 *              occurrence of each classification from specification to match
 *              whether it can be found in a SortedOccSet. In this case, this
 *              class need to implement the Occurrence interface to provide the
 *              occurrence of each AKind type.
 *              <p>
 *              2) The order of the classifications is declared as a descending
 *              order, while the default sorting order in Java is ascending.
 *              Therefore, the first place of sorted CardType collection is the
 *              max value. This declaration is the same with Rank's.
 * 
 * @see Occurrence, SortedOccSet, Rank
 */
public enum CardType implements Occurrence {
    /*
     * Declaring 9 kinds of classifications in specification.
     */
    STRAIGHT_FLUSH("%s-high straight flush"),

    /*
     * Four of AKind should have 4 same ranks, which means the occurrence of
     * this type is 4.
     */
    FOUR_OF_A_KIND("Four %ss", 4),

    FULL_HOUSE("%ss full of %ss"),

    FLUSH("%s-high flush"),

    STRAIGHT("%s-high straight"),

    /*
     * Same idea with Four of AKnind. This type should have 3 same ranks.
     */
    THREE_OF_A_KIND("Three %ss", 3),

    /*
     * The occurrence of this type is 2.
     */
    TWO_PAIR("%ss over %ss", 2),

    ONE_PAIR("Pair of %ss"),

    HIGH_CARD("%s-high");

    /**
     * The relevant description of each card type.
     */
    private String description;

    /**
     * The given occurrence of ranks for identifying a n of AKind type.
     */
    private int occurrence;

    /**
     * The constructor method requires a description for each type.
     * <p>
     * Note: public is not permitted for Enum type.
     * 
     * @param description:
     *            each given description from specification.
     */
    CardType(String description) {
        this(description, 0);
    }

    /**
     * Overloading the constructor method, which requires a number of
     * occurrence.
     * 
     * @param description:
     *            each given description from specification.
     * @param occurrence:
     *            the number of occurrence
     */
    CardType(String description, int occurrence) {
        this.description = description;
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * Implement this method to return an occurrence number of each AKind type.
     */
    @Override
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * To identify whether a Flush exists in the list of cards.
     * 
     * @param cards:
     *            a list of cards.
     * @return true: is Flush type, otherwise not.
     */
    public static boolean isFlush(List<Card> cards) {
        for (int i = 1; i < cards.size(); i++) {
            Suit suit1 = cards.get(i - 1).getSuit();
            Suit suit2 = cards.get(i).getSuit();
            /*
             * If one of cards is not the same suit as others, then return
             * false.
             */
            if (suit1.compareTo(suit2) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * To identify whether a Straight exists in the list of cards.
     * 
     * @param cards:
     *            a list of cards.
     * @return true: is Straight type, otherwise not.
     */
    public static boolean isStraight(List<Card> cards) {
        for (int i = 1; i < cards.size(); i++) {
            Rank rank1 = cards.get(i).getRank();
            Rank rank2 = cards.get(i - 1).getRank();
            /*
             * If the absolute difference between two ranks is not equal 1,
             * which means it is not a sequence, then return false;
             */
            if (Math.abs(rank1.getValue() - rank2.getValue()) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * To identify whether a AKind type exists in the list of cards.
     * 
     * @param cards:
     *            a list of cards.
     * @return if the returned object is not null, which means AKind type has
     *         been found in this list, otherwise it does not exist.
     */
    public static Description hasAKind(List<Card> cards) {
        // Build a SortedOccSet for the list of cards to
        SortedOccSet<Rank> occSet = new SortedOccSet<>();
        for (Card card : cards) {
            occSet.add(card.getRank());
        }
        /*
         * Firstly, find whether it has FOUR_OF_A_KIND type. If exists, then
         * return the relevant Rank object for printing description.
         */
        Rank rank = occSet.findByOccurence(FOUR_OF_A_KIND);
        if (rank != null) {
            return new Description(FOUR_OF_A_KIND, rank);
        }
        // Secondly, find whether it exists THREE_OF_A_KIND.
        rank = occSet.findByOccurence(THREE_OF_A_KIND);
        if (rank != null) {
            // If exists, then find whether it has another pair.
            Rank rank2 = occSet.findByOccurence(TWO_PAIR);
            if (rank2 != null) {
                return new Description(FULL_HOUSE, rank, rank2);
            }
            return new Description(THREE_OF_A_KIND, rank);
        }
        // Thirdly, whether it has a pair.
        rank = occSet.findByOccurence(TWO_PAIR);
        if (rank != null) {
            // If exists, then find whether it has another pair.
            Rank rank2 = occSet.findByOccurence(rank, TWO_PAIR);
            if (rank2 != null) {
                /*
                 * According to the specification, the higher rank need to be
                 * first place.
                 */
                if (rank2.compareRank(rank) > 0) {
                    return new Description(TWO_PAIR, rank2, rank);
                }
                return new Description(TWO_PAIR, rank, rank2);
            }
            return new Description(ONE_PAIR, rank);
        }
        // Release unused memory is a good habit for efficient programming.
        occSet.clear();
        return null;
    }
}
