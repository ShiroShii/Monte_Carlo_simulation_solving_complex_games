package com.diplomski.common.turn;

import com.diplomski.common.activity.AttackActionActivityProvider;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.activity.IAttackRollOutcomeProvider;
import com.diplomski.common.activity.IDamageProvider;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.targeting.ITargetProvider;
import com.diplomski.common.targeting.RoundRobinTargetProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TurnProviderFactory implements ITurnProviderFactory {
	private IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private IDamageProvider damageProvider;

	@Override
	public ITurnProvider getTurnProvider(String initiatorId, BoardState boardState) {
		BattleCharacterState character = boardState.getCharacterStates().get(initiatorId);
		PlayStyle playStyle = character.getPlayStyle();
		ITargetProvider targetProvider = switch (character.getTargetingStyle()) {
		default -> new RoundRobinTargetProvider();
		};

		IActivityProvider movementProvider = switch (playStyle) {
		default -> null;
		};

		IActivityProvider actionProvider = switch (playStyle) {
		default -> new AttackActionActivityProvider(attackRollOutcomeProvider, damageProvider);
		};

		Party targetParty = switch (playStyle) {
		case SUPPORT -> Party.PLAYER;
		default -> Party.ENEMY;
		};

		return new TurnProvider(initiatorId, targetParty, targetProvider, movementProvider, actionProvider, playStyle);
	}

}
