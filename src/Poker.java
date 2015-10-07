/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Poker
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public class Poker {

	public static void main(String[] inputs) {
		if (inputs == null || inputs.length == 0
				|| inputs.length % Constants.MAX_CARDS_NUMBER != 0) {
			System.out.println(Constants.INVALID_INPUT_NUMBER);
			return;
		}
		int playerSize = inputs.length / Constants.MAX_CARDS_NUMBER;
		Referee referee = new Referee(playerSize);
		for (int i = 0; i < inputs.length; i++) {
			String hand = inputs[i];
			Card card = verifyName(hand, i);
			if (card == null) {
				System.out.println(
						String.format(Constants.INVALID_CARD_NAME, hand));
				return;
			}
			int numOfPlayers = i / Constants.MAX_CARDS_NUMBER;
			referee.addPlayer(card, numOfPlayers);
		}

		System.out.println(referee.toDescription());
		
		if (referee.getPlayerSize() > 1) {
			System.out.println(referee.refereeWinners());
		}
	}

	private static Card verifyName(String hand, int index) {
		if (hand.length() != Constants.NUM_OF_ECAH_CARDS)
			return null;
		String rank = hand.substring(0, 1).toUpperCase();
		String suit = hand.substring(1).toUpperCase();
		if (!Constants.RANKS.containsKey(rank)
				|| !Constants.SUITS.containsKey(suit)) {
			return null;
		}
		Rank rankEnum = Constants.RANKS.get(rank);
		Suit suitEnum = Constants.SUITS.get(suit);
		return new Card(rankEnum, suitEnum);
	}
}
