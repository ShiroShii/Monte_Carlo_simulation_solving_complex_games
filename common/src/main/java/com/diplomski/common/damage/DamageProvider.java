package com.diplomski.common.damage;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.resource.IResource;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DamageProvider implements IDamageProvider {
	private final @NonNull IDiceFactory diceFactory;

	@Override
	public int getDamage(IResource resource, IBattleCharacterState initiator, IBattleCharacterState target) {
		return resource.getDamageRoll().getRollAddend() + resource.getDamageRoll().getDice().stream().map(x-> diceFactory.getDice(x).getRoll()).reduce(0, Integer::sum);
	}

}
