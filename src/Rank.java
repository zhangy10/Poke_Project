/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName: Rank
 * 
 *             Oct 4, 2015
 * 
 * @Description: This Enum type represent the rank of each card in Poker game.
 *               It contains 13 different types which are from 2 to Ace.
 *               <p>
 *               As the decision of who is the winner depends on the order of
 *               Rank in specification, the way of using Enum type to represent
 *               different types is useful for sorting a list of data by using
 *               Arrays.sort() or Collections.sort(), because the those sort
 *               methods in Java require the input objects to implements a
 *               Comparable interface or Comparator interface. In this case, the
 *               Comparable interface has been automatically implemented by Enum
 *               type for user to compare each different type.
 *               <p>
 *               Note: The return of this compare method depends on the order of
 *               user's declarations, which means the given order is followed by
 *               from left to right as an ascending order (default order in
 *               Java). However the below declarations are defined as a
 *               descending order by personal consideration. So, another
 *               compareRank() method is designed for comparing the real value
 *               of each Rank type in this Project.
 */
public enum Rank {

    /**
     * Redesign as a descending order for logical part which can easily access
     * the max value from a data structure instead of iterating to the end of
     * it.
     */
    A(14), K(13), Q(12), J(11), T(10), NINE(9), EIGHT(8), 
    SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2);

    /**
     * The real value of Rank in Poker game, which is from 2 to Ace.
     */
    private int value;

    /**
     * This constructor is used for storing a real value of ecah type.
     * <p>
     * Note: public is not permitted for Enum type.
     * 
     * @param value:
     *            the real value of Rank.
     */
    Rank(int value) {
        this.value = value;
    }

    /**
     * Accessor method for accessing the real value of each type
     * 
     * @return int: The real value of each rank
     */
    public int getValue() {
        return value;
    }

    /**
     * Return the relevant card full name of each rank
     * 
     * @return String: full name of rank
     */
    public String getName() {
        switch (value) {
            case 14:
                return "Ace";
            case 13:
                return "King";
            case 12:
                return "Queen";
            case 11:
                return "Jack";
            default:
                return String.valueOf(value);
        }
    }

    /**
     * The reason for designing this method is because that the original
     * ascending order is assumed as a descending order. So this method will
     * return correctly by using the value variable to compare.
     * 
     * @param another:
     *            another object which needs to compare with the current object.
     * 
     * @return negative: this less than another; positive: this greater than
     *         another; 0: equal.
     */
    public int compareRank(Rank another) {
        return value - another.getValue();
    }
}
