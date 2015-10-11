import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName Referee
 * 
 *            Oct 4, 2015
 * 
 * @Description This Referee class represents a referee who can make a decision
 *              of which players are winners in Poker game. All players are
 *              managed by a Referee in this project. Also, this class is
 *              immutable, which can only return such an integer or String for
 *              output. The main features of this class are the below points:
 *              <p>
 *              1) Making a fair decision for players by using the algorithm of
 *              selecting winners.
 *              <p>
 *              2) Returning the relevant output messages for printing.
 * 
 * @see Player, SortedOccSet
 */
public final class Referee {
    /**
     * This variable holds an array for storing the reference of each player.
     */
    private Player[] players;

    /**
     * This constructor requires a size of how many players involve.
     * 
     * @param playerSize:
     *            the size of players.
     */
    public Referee(int playerSize) {
        this.players = new Player[playerSize];
    }

    /**
     * Return the size of players that can be determined whether it is a
     * multiple players mode.
     * 
     * @return int: the size of players.
     */
    public int getPlayerSize() {
        return players.length;
    }

    /**
     * Dealing each card to each player.
     * <p>
     * Here, the order of dealing card will be followed by the order of given
     * inputs.
     * 
     * @param card:
     *            a Card object.
     * @param numOfPlayers:
     *            the number which player belongs to.
     */
    public void dealCard(Card card, int numOfPlayers) {
        /*
         * If the number of player does not exist in the player array, then
         * create a new player object.
         */
        if (players[numOfPlayers] == null) {
            players[numOfPlayers] = new Player(numOfPlayers);
        }
        // If exists, then deal this card to the specific player.
        Player player = players[numOfPlayers];
        player.addCard(card);
    }

    /**
     * Determining the final winners.
     * <p>
     * Note: This selecting winners algorithm contains the below points:
     * <p>
     * 1) Using a SortedOccSet to group the array of players by the different
     * card type as a descending order.
     * <p>
     * 2) Selecting the first place which will hold a list of players who draw.
     * <p>
     * 3) Calling the findHigherRank() method to determine who will be the
     * winners among of the list of players holding the same card type.
     * 
     * @return String: a string of winners' list.
     */
    public String determineWinners() {
        /*
         * It is the same way with identifying different card type for each
         * player. More detail, see the CardType.hasAKind().
         */
        SortedOccSet<Player> occSet = new SortedOccSet<>();
        for (Player player : players) {
            occSet.add(player);
        }
        List<Player> topPlayers = occSet.findPeak();
        occSet.clear();
        // Calling the findHigherRank() method to deal with the winners.
        return toWinnerStr(findHigherRank(topPlayers));
    }

    /**
     * Overloading findHigherRank() method to provide a easy way for call this
     * method first time.
     * 
     * @param players:
     *            the players who draw.
     * @return List<Player>: the winners list.
     */
    private List<Player> findHigherRank(List<Player> players) {
        /*
         * For the first time to call this method, the rest of cards' size for
         * each player should be the given maximum cards' number.
         */
        return findHigherRank(players, Constants.MAX_CARDS_NUMBER);
    }

    /**
     * Finding players who hold a higher rank among of peers.
     * <p>
     * Note: This method will be recalled recursively by the rest of cards' size
     * for finding the final winners until it stops when the below two basic
     * cases occur:
     * <p>
     * 1) If the size of input players equal 1, then return the final winner
     * directly.
     * <p>
     * 2) If the rest card size of each player equal 0, which means that a draw
     * situation occur among of the players, then return these players as
     * winners.
     * <p>
     * The reason for using this recursive solution is to avoid some redundant
     * for-loops which are usually not clear and not readable for this project.
     * 
     * @param players:
     *            the players who draw
     * @param restCardNum:
     *            the rest card size of each player.
     * @return List<Player>: the players who hold a higher rank in each turn.
     */
    private List<Player> findHigherRank(List<Player> players, int restCardNum) {
        // Two basic breaking cases.
        if (players.size() == 1 || restCardNum == 0) {
            return players;
        }
        /*
         * For the first time, it does not need to shift cards to the next
         * highest rank. If not the first time, for each turn, all players'
         * cards should be shifted before determining.
         */
        if (restCardNum != Constants.MAX_CARDS_NUMBER) {
            for (Player player : players) {
                /*
                 * To hold the rest of cards' size. Here, it may be a little bit
                 * puzzled, because the value of restCardNum will be assigned
                 * more than once. Actually, it could be fixed as the
                 * restCardNum should be assigned until the last turn. However,
                 * as each rest size of players will be the same, it does not
                 * really matter to assign the restCardNum too many times. So,
                 * in fact, the restCardNum will also hold the last return
                 * value.
                 */
                restCardNum = player.shiftRank();
            }
        }
        /*
         * This part of codes will determine one or more players who have a
         * higher rank by a linear scanning. The idea of this looks like a
         * simple way to find a max value from a sequence, but vary.
         * 
         * 1) Firstly, picks up the first object, and assumes that this is the
         * max one.
         * 
         * 2) Then, scans the rest of the list. If a higher one occurs, then
         * remove all the previous objects, and assuming this is a new max one.
         * If not, which means players draw, then adding a player into a draw
         * list until the loop stops.
         */
        Player winPlayer = players.get(0);
        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            Rank rank1 = player.highestRank();
            Rank rank2 = winPlayer.highestRank();
            if (rank1.compareRank(rank2) > 0) {
                winners.removeAll(winners);
                winners.add(player);
                winPlayer = player;
            }
            else if (rank1.compareRank(rank2) == 0) {
                winners.add(player);
            }
        }
        /*
         * Recall this method to check as the same as the previous turn.
         * 
         * Be careful with the key number, restCardNum, because this recursive
         * relation will be stopped by this value in most of testing cases.
         * 
         * For the pre-decrement, the first time I used this as a post
         * decrement, then I got a StackOverflowError which was really dangerous
         * for large scale projects. Anyway, pay more attention to each
         * recursive using case.
         */
        return findHigherRank(winners, --restCardNum);
    }

    /**
     * Return the description of all players for output.
     * 
     * @return String: the description of all players.
     */
    public String toDescription() {
        /*
         * The reason for using StringBuilder to append each String is because
         * that it is an efficient way for appending multiple String object. It
         * is faster than using '+' to append a String object, because the way
         * of using '+' will create a new String object for each time, and it is
         * more expensive than using StringBuilder.
         * 
         * Note: The main difference between StringBuilder and StringBuffer is
         * that StringBuilder is faster than StringBuffer, because the latter
         * one is synchronized, while the former one is not. For multiple
         * threads system, the StringBuffer is recommended and is thread-safe,
         * but this project only has main thread. So, StringBuilder does make
         * sense.
         */
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            sBuilder.append(player.toString());
            if (i != players.length - 1) {
                sBuilder.append('\n');
            }
        }
        return sBuilder.toString();
    }

    /**
     * Return the winner list as a String.
     * 
     * @param winners:
     *            input a list of winners.
     * @return String: convert the winners list to a formatted String for
     *         output.
     */
    private String toWinnerStr(List<Player> winners) {
        if (winners == null) {
            return null;
        }
        if (winners.size() == 1) {
            return String.format(Constants.WINS_DES,
                    winners.get(0).getNumber());
        }
        else {
            // Again, using StringBuilder to append each String.
            StringBuilder sBuilder = new StringBuilder();
            for (int i = 0; i < winners.size(); i++) {
                if (i == winners.size() - 2) {
                    sBuilder.append(winners.get(i).getNumber());
                    sBuilder.append(" and ");
                }
                else if (i == winners.size() - 1) {
                    sBuilder.append(winners.get(i).getNumber());
                }
                else {
                    sBuilder.append(winners.get(i).getNumber());
                    sBuilder.append(", ");
                }
            }
            return String.format(Constants.DRAW_DES, sBuilder.toString());
        }
    }
}
