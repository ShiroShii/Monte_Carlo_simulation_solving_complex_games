package com.diplomski.common.board;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;

import java.util.LinkedHashMap;
import java.util.Optional;
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

	public Optional<Party> getWinningParty() {
		return (getPartyHp(ENEMY) == 0) ?
				Optional.of(PLAYER) :
				(getPartyHp(PLAYER) == 0) ?
						Optional.of(ENEMY) :
				Optional.empty();
	}
}
