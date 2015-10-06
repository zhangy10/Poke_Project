import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: Referee
 * 
 *             Oct 6, 2015
 * 
 * @Description: TODO
 */
public final class Referee {

	private Player[] players;

	public Referee(int playerSize) {
		this.players = new Player[playerSize];
	}

	public int getPlayerSize() {
		return players.length;
	}

	public void addPlayer(Card card, int numOfPlayers) {
		if (players[numOfPlayers] == null) {
			players[numOfPlayers] = new Player(numOfPlayers);
		}
		Player player = players[numOfPlayers];
		player.addCard(card);
	}

	public Player[] refereeWinners() {
		OccurSortedSet<Player> occSet = new OccurSortedSet<>();
		for (Player player : players) {
			occSet.add(player);
		}
		List<Player> topPlayers = null;
		Iterator<Player> iterator = occSet.iterator();
		while (iterator.hasNext()) {
			topPlayers = occSet.getOccurList(iterator.next());
			break;
		}
		occSet.clear();

		if (topPlayers != null && topPlayers.size() == 1) {
			return topPlayers.toArray(new Player[topPlayers.size()]);
		}

		Classification classification = topPlayers.get(0).getDescripition()
				.getClassification();
		List<Player> winners = findHighest(topPlayers);
		switch (classification) {
			// 1
			case FOUR_OF_A_KIND:
			case FULL_HOUSE:
				winners = findWinByTimes(1, winners);
				break;
			// 2
			case THREE_OF_A_KIND:
			case TWO_PAIR:
				winners = findWinByTimes(2, winners);
				break;
			// 3
			case ONE_PAIR:
				winners = findWinByTimes(3, winners);
				break;
			// 4
			case FLUSH:
			case HIGH_CARD:
				winners = findWinByTimes(4, winners);
				break;
			default:
				break;
		}
		return winners.toArray(new Player[winners.size()]);
	}

	private List<Player> findWinByTimes(int n, List<Player> winners) {
		for (int i = 0; i < n; i++) {
			winners = findHighest(winners, true);
			if (winners.size() == 1)
				return winners;
		}
		return winners;
	}

	private List<Player> findHighest(List<Player> sameRankPlayers) {
		return findHighest(sameRankPlayers, false);
	}

	private List<Player> findHighest(List<Player> sameRankPlayers,
			boolean matchNextRank) {
		if (matchNextRank) {
			for (Player player : players) {
				player.changeNextRank();
			}
		}
		Player winPlayer = sameRankPlayers.get(0);
		List<Player> winners = new ArrayList<>();
		for (Player player : sameRankPlayers) {
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
		return winners;
	}

	/**
	 * 
	 * @return
	 */
	public String toDescription() {
		// Difference between StringBuilder and StringBuffer
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
}
