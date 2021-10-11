package com.diplomski.common.board;

import java.util.LinkedHashMap;
import java.util.UUID;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BoardState {
	private LinkedHashMap<UUID, IBattleCharacterState> characterStates;
	private IBoard board;

	public int getPartyHp(Party party) {
		return characterStates.values().stream().filter(x -> x.getParty().equals(party)).mapToInt(x -> x.getCurrentHp())
				.sum();
	}

	public void resetSpeed() {
		for (IBattleCharacterState characterState : characterStates.values()) {
			characterState.setUsedSpeed(0);
		}
	}

	public boolean isBattleComplete() {
		return getPartyHp(Party.PLAYER) == 0 || getPartyHp(Party.ENEMY) == 0;
	}

	public int getPartyActiveCount(Party party) {
		return (int) characterStates.values().stream().filter(x -> x.getParty().equals(party) && x.getCurrentHp() > 0)
				.count();
	}
}
