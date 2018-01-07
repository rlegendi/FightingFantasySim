package data;

import utils.DeathException;
import utils.GameUtils;

public final class PlayerCharacter {

	private final int maxPhysics;
	private final int maxAstral; // may change during battle
	private final int maxMental; // may change during battle
	private final int maxHealthPoints; // may change during battle

	private int physics;
	private int healthPoints;
	private int astral;
	private int mental;
	
	private final int damage = 2;

	public PlayerCharacter(final int physics, final int astral, final int mental) {
		this.maxPhysics = physics;
		this.maxAstral = astral;
		this.maxMental = mental;

		this.maxHealthPoints = physics + 1;
	}

	public int getAttackRate() {
		int ret = physics + GameUtils.k6(2);
		if (! isConscious() ) ret--;
		return ret;
	}

	public int getDefenseRate() {
		int ret = physics + mental;
		if (! isConscious()) ret--;
		return ret;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public int getPhysics() {
		return physics;
	}

	public int getAstral() {
		return astral;
	}
	
	// CHECKME: --attack, --defense OR --everything?
	public boolean isConscious() {
		return mental > 0;
	}
	
	public int getMental() {
		return mental;
	}
	
	public int getDamage() {
		return damage;
	}

	public void heal() {
		physics = maxPhysics;
		astral = maxAstral;
		mental = maxMental;

		healthPoints = maxHealthPoints;
	}

	public void hit(final int damage) throws DeathException {
		healthPoints -= damage;
		astral--;

		if (healthPoints <= 0) {
			throw new DeathException();
		}
	}

	public void decreaseMental() {
		mental--;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + astral;
		result = prime * result + healthPoints;
		result = prime * result + maxAstral;
		result = prime * result + maxHealthPoints;
		result = prime * result + maxMental;
		result = prime * result + maxPhysics;
		result = prime * result + mental;
		result = prime * result + physics;
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
		final PlayerCharacter other = (PlayerCharacter) obj;
		if (astral != other.astral)
			return false;
		if (healthPoints != other.healthPoints)
			return false;
		if (maxAstral != other.maxAstral)
			return false;
		if (maxHealthPoints != other.maxHealthPoints)
			return false;
		if (maxMental != other.maxMental)
			return false;
		if (maxPhysics != other.maxPhysics)
			return false;
		if (mental != other.mental)
			return false;
		if (physics != other.physics)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlayerCharacter [maxPhysics=" + maxPhysics + ", maxAstral=" + maxAstral + ", maxMental=" + maxMental +
			", maxHealthPoints=" + maxHealthPoints + ", getDefenseRate()=" + getDefenseRate() + "]";
	}
	
}
