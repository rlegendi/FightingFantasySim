package utils;

import java.util.Random;

import data.PlayerCharacter;


public final class GameUtils {
	//private static final Random random = new Random(Constants.SEED);
	private static final Random random = new Random();

	public static int k6() {
		return random.nextInt(6) + 1;
	}

	public static int k6(final int die) {
		if (die < 1) {
			throw new IllegalArgumentException("die must be positive");
		}
		
		int ret = 0;

		for (int i = 0; i < die; ++i) {
			ret += k6();
		}

		return ret;
	}
	
	public static PlayerCharacter quicker(final PlayerCharacter player, final PlayerCharacter enemy) {
		return player.getAstral() >= enemy.getAstral() ? player : enemy;
	}
	
	public static PlayerCharacter slower(final PlayerCharacter player, final PlayerCharacter enemy) {
		return player.getAstral() < enemy.getAstral() ? player : enemy;
	}

	public static boolean fight(PlayerCharacter player, PlayerCharacter enemy) {
		try {
			while (true) {
				PlayerCharacter quicker = quicker(player, enemy);
				PlayerCharacter slower = slower(player, enemy);
				
				assert ( ! quicker.equals(slower) );
				
				if ( quicker.getAttackRate() >= slower.getDefenseRate() ) {
					slower.hit(quicker.getDamage());
				}
				
				checkMorale(quicker, slower);
				
				if (slower.getAttackRate() >= quicker.getDefenseRate()) {
					quicker.hit(slower.getDamage());
				}
				
				checkMorale(quicker, slower);
				
				quicker.decreaseMental();
				slower.decreaseMental();
			}
		} catch (DeathException e) {
			return player.getHealthPoints() > 0;
		} catch (MoraleFailedException e) {
			return player.getAstral() > enemy.getAstral();
		} finally {
			player.heal();
			enemy.heal();
		}
	}

	private static void checkMorale(PlayerCharacter player, PlayerCharacter enemy) throws MoraleFailedException {
		if ( Math.abs( player.getAstral() - enemy.getAstral() ) > 5 ) { // CHECKME: >= ?
			throw new MoraleFailedException();
		}
	}
	
	private GameUtils() {
		;
	}
}
