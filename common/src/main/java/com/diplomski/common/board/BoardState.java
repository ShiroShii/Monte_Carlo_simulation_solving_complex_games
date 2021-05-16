package com.diplomski.common.board;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.Party;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BoardState {
	private LinkedHashMap<String, BattleCharacterState> characterStates;
	private HashMap<ITile, TerrainFeature> terainFeatures;
	private HashSet<ITile> obsticles;

	public int getPartyHp(Party party) {
		return characterStates.values().stream().filter(x -> x.getParty().equals(party)).mapToInt(x -> x.getCurrentHp())
				.sum();
	}

	public void resetSpeed() {
		for (BattleCharacterState characterState : characterStates.values()) {
			characterState.setUsedWalkingSpeed(0);
		}
	}

	public boolean isBattleComplete() {
		return getPartyHp(Party.PLAYER) == 0 || getPartyHp(Party.ENEMY) == 0;
	}
}
