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
 *
 * @ClassName Player
 * 
 *            Oct 4, 2015
 * 
 * @Description This Player class is abstracted to represent each real player in
 *              Poker game. It holds a number of variables, and will be
 *              determined by a Referee to identify which players are winners.
 *              <p>
 *              Note: Considering the algorithm of selecting winners will put
 *              each Player into a SortedOccSet to group by each card type and
 *              to get a highest card type player list, the Comparable interface
 *              is required to be implemented related to the description of
 *              SortedOccSet. Also, the equal() method need to be overridden.
 *              This class is immutable.
 * 
 * @see Card, Description, CardType, SortedOccSet
 */
public final class Player implements Comparable<Player> {
    /**
     * This variable holds the number of each player.
     */
    private int number;

    /**
     * A player can hold a list of cards.
     */
    private List<Card> cards;

    /**
     * This variable holds the identified description.
     */
    private Description description;

    /**
     * This variable holds the highest rank from cards. It will be changed
     * dynamically when a few of players have the same highest card type
     * simultaneously.
     */
    private Rank highestRank;

    /**
     * This constructor requires a number for identifying each player.
     * 
     * @param number:
     *            the number of each player.
     */
    public Player(int number) {
        this.number = number;
        this.cards = new ArrayList<>();
    }

    /**
     * Shifting the highest rank since more than one players hold the same card
     * type that needs to shift to the next highest rank.
     * <p>
     * This return value will be used to check the final winners recursively if
     * the draw occurs.
     * 
     * @return int: the rest size of cards.
     */
    public int shiftRank() {
        /*
         * Since the draw occurred, the current highest rank could not decide
         * the winners any more. Therefore, the highest rank should be shifted
         * to the next highest rank.
         * 
         * Removing the current highest rank from the list of cards.
         */
        removeCard(highestRank);
        CardType cardType = description.getCardType();
        /*
         * Especially, for these two types, the second high rank is the rank of
         * another pair. Therefore, this case is specialized for dealing
         * FullHouse and TwoPair.
         */
        switch (cardType) {
            case FULL_HOUSE:
            case TWO_PAIR:
                Rank rank2 = description.getRank2();
                /*
                 * For TwoPair, if the draw still occurs, the last card need to
                 * be compared. In this case, if the current highest rank has
                 * been the rank of another pair, this statement will be skipped
                 * to the next statement to assign the last rank.
                 */
                if (highestRank.compareTo(rank2) != 0) {
                    highestRank = rank2;
                    return cards.size();
                }
            default:
                break;
        }

        /*
         * The first place has been the highest rank since previous rank was
         * removed.
         */
        if (cards.size() >= 1) {
            highestRank = cards.get(0).getRank();
        }
        return cards.size();
    }

    /**
     * Return each player's number.
     * 
     * @return int: the number of each player. Attention off-by-one error.
     */
    public int getNumber() {
        return number + 1;
    }

    /**
     * As Description is immutable, returning the reference of this object is
     * permitted.
     * 
     * @return Description: a Description object for printing.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Return the highest rank.
     * 
     * @return Rank: the highest rank.
     */
    public Rank highestRank() {
        return highestRank;
    }

    /**
     * Overriding this method for formatted printing.
     */
    @Override
    public String toString() {
        return String.format(Constants.PLAYER_DES, getNumber(),
                description.toString());
    }

    /**
     * Add a card object to each player.
     * 
     * @param card:
     *            Card type object.
     */
    public void addCard(Card card) {
        cards.add(new Card(card));
        /*
         * If the size of cards is up to the given maximum number, then
         * identifying the card type of this player.
         */
        if (cards.size() == Constants.MAX_CARDS_NUMBER) {
            identifyType();
        }
    }

    /**
     * Removing a card from the list of cards.
     * 
     * @param rank:
     *            the rank of which card need to be removed.
     */
    private void removeCard(Rank rank) {
        /*
         * Pay more attention to remove action of collections, because such
         * unsafe operation for collections in Java will usually give rise to
         * the ConcurrentModificationException or IndexOutOfBoundsException.
         * 
         * In general, using Iterator loop to traverse a collection is a proper
         * way to do so. Another well-known way is by using normal for-loop to
         * traverse reversely to delete elements.
         */
        Iterator<Card> iterator = cards.iterator();
        while (iterator.hasNext()) {
            if (rank.compareTo(iterator.next().getRank()) == 0) {
                iterator.remove();
            }
        }
    }

    /**
     * Identifying card type since the cards have been distributed to each
     * player.
     */
    private void identifyType() {
        /*
         * Collections.sort() method is specialized for List interface to sort
         * data. In fact, the inside implement of this method also use
         * Arrays.sort() to work. Therefore, the Card object need to implement
         * the Comparable interface.
         */
        Collections.sort(cards);
        /*
         * Pick the first one as the highest rank. As mentioned in Rank Enum,
         * the default order of ranks is descending.
         */
        highestRank = cards.get(0).getRank();

        if (CardType.isFlush(cards)) {
            description = new Description(CardType.FLUSH, highestRank);
        }
        if (CardType.isStraight(cards)) {
            /*
             * If description is not null, which means it is a Flush. Also, it
             * is a Straight. Therefore it is a StraightFlush.
             */
            if (description != null) {
                description = new Description(CardType.STRAIGHT_FLUSH,
                        highestRank);
            }
            else {
                description = new Description(CardType.STRAIGHT, highestRank);
            }
        }
        // If description is still null, then to check whether it is AKind type
        if (description == null) {
            description = CardType.hasAKind(cards);
            if (description != null) {
                highestRank = description.getRankest();
            }
        }
        // Finally, if the description does not match any types, it will be a
        // HighCard.
        if (description == null) {
            description = new Description(CardType.HIGH_CARD, highestRank);
        }
    }

    /**
     * Recalling the compareTo() of CardType to decide which player is greater
     * than another.
     */
    @Override
    public int compareTo(Player another) {
        return description.getCardType()
                .compareTo(another.getDescription().getCardType());
    }

    /**
     * Also this method depends on whether the card types are equal.
     */
    @Override
    public boolean equals(Object o) {
        Player another = (Player) o;
        return description.getCardType() == another.getDescription()
                .getCardType();
    }
}
