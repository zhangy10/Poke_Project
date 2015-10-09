/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName: Poker
 * 
 *             Oct 4, 2015
 * 
 * @Description: This project will provide an efficient solution to deal with
 *               the questions of Poker game. According to the specification,
 *               this game offers players a number of rules for selecting the
 *               final winner. It contains 13 kinds of ranks from 2 to Ace as an
 *               ascending order, 4 kinds of suits and 9 kinds of
 *               classifications from StriaghtFlush to HighCard as a descending
 *               order. Also, the maximum card number of each player is 5. The
 *               decision for selecting winners will depend on the card
 *               classifications as well as the value of each card rank.
 *               <p>
 *               The challenges of this project will involve the below points:
 *               <p>
 *               1) Identifying the description of each hand of cards.
 *               <p>
 *               2) Selecting winners by using given conditions in
 *               specification.
 *               <p>
 *               This Poker class is the launch class of this project. Its
 *               features involve the below points:
 *               <p>
 *               1) Validating the given input data.
 *               <p>
 *               2) Outputting each player's description of cards. For this
 *               point, the Referee class is abstracted. It is involved to
 *               return the relevant description of each player and to select
 *               winners. More detail can be found in this class.
 *               <p>
 *               3) Printing a winner list.
 * 
 * @see Referee
 */
public class Poker {

    /**
     * Presenting the main work-flow of this project from validating data to
     * printing the expected output.
     * 
     * @param inputs:
     *            the given input data for command line arguments.
     */
    public static void main(String[] inputs) {
        // If the length of inputs is not the multiple of 5 or is not given,
        // then print invalid message.
        if (inputs == null || inputs.length == 0
                || inputs.length % Constants.MAX_CARDS_NUMBER != 0) {
            System.out.println(Constants.INVALID_INPUT_NUMBER);
            return;
        }
        // To calculate how many players are involved.
        int playerSize = inputs.length / Constants.MAX_CARDS_NUMBER;
        // Create a new Referee object to hold the valid card objects.
        Referee referee = new Referee(playerSize);
        for (int i = 0; i < inputs.length; i++) {
            String cardStr = inputs[i];
            // Verify whether the card name is right.
            Card card = verifyName(cardStr, i);
            if (card == null) {
                System.out.println(
                        String.format(Constants.INVALID_CARD_NAME, cardStr));
                return;
            }
            // To the number of each player.
            int numOfPlayers = i / Constants.MAX_CARDS_NUMBER;
            // Add a valid card object into Referee.
            referee.addPlayer(card, numOfPlayers);
        }
        // Print the description of player.
        System.out.println(referee.toDescription());
        /*
         * If the size of players is more than one, then Referee will return the
         * winners list as a formatted string.
         */
        if (referee.getPlayerSize() > 1) {
            System.out.println(referee.refereeWinners());
        }
    }

    /**
     * Validating each card name. For each card, two characters will be
     * contained. The first one is the rank of card which is in the range of
     * Ranks map. The second one is the suit of card that the valid range of it
     * is defined in Suits map.
     * 
     * @see Constants
     * 
     * @param cardStr:
     *            each card string which should contain 2 characters. Each
     *            character should be in the valid range.
     * @param index:
     *            the index of each card string in the input array.
     * @return Card: if the returned card object is not null, which means the
     *         inputs are valid, otherwise they are invalid.
     */
    private static Card verifyName(String cardStr, int index) {
        // If each card string length is not 2, then return error.
        if (cardStr.length() != Constants.NUM_OF_ECAH_CARDS)
            return null;
        // Avoiding mix letter case, and split the string to 2 characters.
        String rank = cardStr.substring(0, 1).toUpperCase();
        String suit = cardStr.substring(1).toUpperCase();
        /*
         * If each rank or suit is not in the predefined valid ranges, then
         * return error.
         */
        if (!Constants.RANKS.containsKey(rank)
                || !Constants.SUITS.containsKey(suit)) {
            return null;
        }
        // Converting valid input to the specific Rank or Suit type.
        Rank rankEnum = Constants.RANKS.get(rank);
        Suit suitEnum = Constants.SUITS.get(suit);
        // If it is valid, return a new card object.
        return new Card(rankEnum, suitEnum);
    }
}
