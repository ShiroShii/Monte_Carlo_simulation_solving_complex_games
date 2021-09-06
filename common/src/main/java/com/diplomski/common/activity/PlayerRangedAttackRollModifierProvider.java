package com.diplomski.common.activity;

import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.resource.CombatStyle;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.resource.WeaponProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerRangedAttackRollModifierProvider implements IAttackRollModifierProvider {
	@Override
	public int getAttackRollModifier(IResource resource, IBattleCharacterState initiator) {
		int modifier = 0;
		// TODO: Check for disadvantage
		if (resource instanceof Weapon) {
			Weapon weapon = (Weapon) resource;

			if (weapon.getWeaponCategory().getStyle() == CombatStyle.RANGED && ((PlayerBattleCharacterState) initiator)
					.getCharacterClass().getWeaponProficiencies().contains(weapon.getWeaponCategory())) {
				modifier += ((PlayerBattleCharacterState) initiator).getLevel().getProficiencyBonus();
			}

			boolean shouldUseDexterity = weapon.getWeaponCategory().getStyle() == CombatStyle.RANGED
					|| (shouldUseDexterityForMeleeWeapon(weapon, (PlayerBattleCharacterState) initiator));

			if (shouldUseDexterity) {
				modifier += Math.floor((((PlayerBattleCharacterState) initiator).getDexterity() - 10) / 2.0d);
			} else {
				modifier += Math.floor((((PlayerBattleCharacterState) initiator).getStrength() - 10) / 2.0d);
			}

		}

		return modifier;
	}

	private boolean shouldUseDexterityForMeleeWeapon(Weapon weapon, PlayerBattleCharacterState initiator) {
		return weapon.getProperties().contains(WeaponProperty.FINESSE)
				&& initiator.getDexterity() > initiator.getStrength();

	}
}
