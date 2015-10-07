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

	public String refereeWinners() {
		SortedOccSet<Player> occSet = new SortedOccSet<>();
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

		return toWinnerStr(findHighestRank(topPlayers, topPlayers.get(0).getCompareNum()));
	}
	
	private List<Player> findHighestRank(List<Player> players, int compareNum) {
		return findHigher(players, compareNum, false);
	}

	private List<Player> findHigher(List<Player> players, int compareNum, boolean isShift) {
		if (players.size() == 1 || compareNum == 0) {
			return players;
		}
		
		if (isShift) {
			for (Player player : players) {
				player.shiftRank();
			}
		}
		
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
		
		return findHigher(winners, --compareNum, true);
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

	private String toWinnerStr(List<Player> winners) {
		if (winners == null) {
			return null;
		}
		if (winners.size() == 1) {
			return String.format(Constants.WINS, winners.get(0).getNumber());
		}
		else {
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
			return String.format(Constants.DRAW, sBuilder.toString());
		}
	}
}
