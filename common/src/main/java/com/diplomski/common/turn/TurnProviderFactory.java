package com.diplomski.common.turn;

import java.util.UUID;

import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.activity.action.attack.AttackActionActivityProvider;
import com.diplomski.common.activity.action.attack.IAttackRollOutcomeProviderFactory;
import com.diplomski.common.activity.movement.MovemementActivityProvider;
import com.diplomski.common.board.INavigator;
import com.diplomski.common.character.CharacterType;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.damage.IDamageProvider;
import com.diplomski.common.targeting.ITargetProvider;
import com.diplomski.common.targeting.RoundRobinTargetProvider;
import com.diplomski.common.targeting.TargetingStyle;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TurnProviderFactory implements ITurnProviderFactory {
	private IAttackRollOutcomeProviderFactory attackRollOutcomeProviderFactory;
	private IDamageProvider damageProvider;
	private INavigator navigator;

	@Override
	public ITurnProvider getTurnProvider(UUID id, Party party, PlayStyle playStyle, TargetingStyle targetingStyle, CharacterType characterType) {
		ITargetProvider targetProvider = switch (targetingStyle) {
			default -> new RoundRobinTargetProvider();
		};

		IActivityProvider movementProvider = switch (playStyle) {
			default -> new MovemementActivityProvider();
		};

		IActivityProvider actionProvider =  new AttackActionActivityProvider(attackRollOutcomeProviderFactory.getAttackRollOutcomeProvider(characterType), damageProvider);

		Party targetParty = switch (playStyle) {
			case SUPPORT -> party;
			default -> party.getOpponentParty();
		};

		return new TurnProvider(navigator, id, targetParty, targetProvider, movementProvider, actionProvider, playStyle);
	}

}
