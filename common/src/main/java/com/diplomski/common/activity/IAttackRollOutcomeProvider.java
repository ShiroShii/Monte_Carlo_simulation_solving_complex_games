package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.resource.IResource;

import lombok.NonNull;

public interface IAttackRollOutcomeProvider {
	public AttackRollOutcome getAttackOutcome(
			@NonNull IResource resource,
			@NonNull BattleCharacterState initiator,
			@NonNull BattleCharacterState target);
}
