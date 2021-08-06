package com.diplomski.common.turn;

import com.diplomski.common.activity.AttackActionActivityProvider;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.activity.IAttackRollOutcomeProvider;
import com.diplomski.common.activity.WalkMovemementActivityProvider;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.damage.IDamageProvider;
import com.diplomski.common.targeting.ITargetProvider;
import com.diplomski.common.targeting.RoundRobinTargetProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TurnProviderFactory implements ITurnProviderFactory {
	private IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private IDamageProvider damageProvider;

	@Override
	public ITurnProvider getTurnProvider(String initiatorId, BoardState boardState) {
		IBattleCharacterState initiator = boardState.getCharacterStates().get(initiatorId);
		PlayStyle playStyle = initiator.getPlayStyle();
		ITargetProvider targetProvider = switch (initiator.getTargetingStyle()) {
			default -> new RoundRobinTargetProvider();
		};

		IActivityProvider movementProvider = switch (playStyle) {
			default -> new WalkMovemementActivityProvider();
		};

		IActivityProvider actionProvider = switch (playStyle) {
			default -> new AttackActionActivityProvider(attackRollOutcomeProvider, damageProvider);
		};

		Party targetParty = switch (playStyle) {
			case SUPPORT -> initiator.getParty();
			default -> initiator.getParty().getOpponentParty();
		};

		return new TurnProvider(initiatorId, targetParty, targetProvider, movementProvider, actionProvider, playStyle);
	}

}
