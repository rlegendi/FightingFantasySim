package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import utils.GameUtils;

import data.Constants;
import data.FightingResults;
import data.PlayerCharacter;

public class Simulator {
	
	private final List<PlayerCharacter> characters;
	private final SortedSet<FightingResults> results = new TreeSet<FightingResults>();
	
	public Simulator() {
		characters = Collections.unmodifiableList( initCharacterList() );
	}
	
	private List<PlayerCharacter> initCharacterList() {
		final ArrayList<PlayerCharacter> ret = new ArrayList<PlayerCharacter>();
		
		for (int i=1; i<=Constants.INITIAL_MAX_ATTRIBUTE; ++i) {
			for (int j=1; j<=Constants.INITIAL_MAX_ATTRIBUTE; ++j) {
				for (int k=1; k<=Constants.INITIAL_MAX_ATTRIBUTE; ++k) {
					if ( i + j + k != Constants.INITIAL_CHAR_POINTS) {
						continue;
					}
					
					ret.add( new PlayerCharacter(i, j, k));
				}
			}
		}
		
		return ret;
	}

	public void start() {
		for (int i = 0, n = characters.size(); i < n; ++i) {
			final PlayerCharacter act = characters.get(i);
			final FightingResults result = new FightingResults(act);
			
			for (int j=i+1; j< n; ++j) {
				final PlayerCharacter enemy = characters.get(j);
				
				for (int k=0; k<Constants.FIGHT_NUMBER; ++k) {
					result.evalFight( GameUtils.fight( act, enemy ) ); 
				}
			}
			
			results.add(result);
		}
	}
	
	private void printResults() {
		for (final FightingResults act : results) {
			System.out.println( act );
		}
	}
	
	public static void main(final String[] args) {
		final Simulator simulator = new Simulator();
		simulator.start();
		simulator.printResults();
	}

}
