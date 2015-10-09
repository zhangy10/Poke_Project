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
 * @ClassName: Player
 * 
 *             Oct 4, 2015
 * 
 * @Description: This Player class is abstracted to represent each real player
 *               in Poker game. It holds a number of variables, and will be
 *               refereed by a Referee to identify which players are winners.
 *               <p>
 *               Note: Considering the algorithm of selecting winners will put
 *               each Player into a SortedOccSet to group by each card type and
 *               to get a highest card type player list, the Comparable
 *               interface is required to be implemented related to the
 *               description of SortedOccSet. Also, the equal() method need to
 *               be overridden. This class is immutable.
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
     * 
     * 
     * @return int: the rest size of cards.
     */
    public int shiftRank() {
        //
        removeCard(highestRank);
        CardType cardType = description.getCardType();
        //
        switch (cardType) {
            case FULL_HOUSE:
            case TWO_PAIR:
                Rank rank2 = description.getRank2();
                //
                if (highestRank.compareTo(rank2) != 0) {
                    highestRank = rank2;
                    return cards.size();
                }
            default:
                break;
        }

        //
        if (cards.size() >= 1) {
            highestRank = cards.get(0).getRank();
        }
        return cards.size();
    }

    /**
     * Returning each player's number.
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
     * Returning the highest rank.
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
        // If the size of cards is up to the given maximum number, then
        // identifying the card type of this player.
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
        // Pay more attention to remove action of collections, because
        Iterator<Card> iterator = cards.iterator();
        while (iterator.hasNext()) {
            if (rank.compareTo(iterator.next().getRank()) == 0) {
                iterator.remove();
            }
        }
    }

    /**
     * 
     * 
     */
    private void identifyType() {
        //
        Collections.sort(cards);
        //
        highestRank = cards.get(0).getRank();
        //
        if (CardType.isFlush(cards)) {
            description = new Description(CardType.FLUSH, highestRank);
        }
        //
        if (CardType.isStraight(cards)) {
            //
            if (description != null) {
                description = new Description(CardType.STRAIGHT_FLUSH,
                        highestRank);
            }
            else {
                description = new Description(CardType.STRAIGHT, highestRank);
            }
        }
        //
        if (description == null) {
            description = CardType.hasAKind(cards);
            if (description != null) {
                highestRank = description.getRankest();
            }
        }
        //
        if (description == null) {
            description = new Description(CardType.HIGH_CARD, highestRank);
        }
    }

    /**
     * 
     */
    @Override
    public int compareTo(Player another) {
        return description.getCardType()
                .compareTo(another.getDescription().getCardType());
    }

    /**
     * 
     */
    @Override
    public boolean equals(Object o) {
        Player another = (Player) o;
        return description.getCardType() == another.getDescription()
                .getCardType();
    }
}
