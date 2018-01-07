package data;

public class FightingResults implements Comparable<FightingResults> {
	private final PlayerCharacter player;
	private int winnings = 0;
	
	public FightingResults(final PlayerCharacter player) {
		this.player = player;
	}
	
	public void evalFight(final boolean win) {
		if (win) winnings++;
	}

	public PlayerCharacter getPlayer() {
		return player;
	}

	public int getWinnings() {
		return winnings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + winnings;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FightingResults other = (FightingResults) obj;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (winnings != other.winnings)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FightingResults [winnings=" + winnings + ", player=" + player + "]";
	}

	@Override
	public int compareTo(final FightingResults o) {
		return o.winnings - winnings;
	}
	
}
