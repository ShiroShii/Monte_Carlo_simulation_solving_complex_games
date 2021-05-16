package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.resource.CombatStyle;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.resource.WeaponProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttackRollModifierProvider implements IAttackRollModifierProvider {
	@Override
	public int getAttackRollModifier(IResource resource, BattleCharacterState initiator) {
		int modifier = 0;
		// TODO: Spell modifiers
		// TODO: Check distance
		switch (initiator.getPlayStyle().getCombatStyle()) {
			case MELEE: {
				if (resource instanceof Weapon) {
					Weapon weapon = (Weapon) resource;

					if (weapon.getWeaponCategory().getStyle() == CombatStyle.MELEE && initiator.getCharacterClass()
							.getWeaponProficiencies().contains(weapon.getWeaponCategory())) {
						modifier += initiator.getLevel().getProficiencyBonus();
					}

					if (shouldUseDexterityForMeleeWeapon(weapon, initiator)) {
						modifier += initiator.getDexterity();
					} else {
						modifier += initiator.getStrengh();
					}

					break;
				}
			}
			case RANGED: {
				if (resource instanceof Weapon) {
					Weapon weapon = (Weapon) resource;

					if (weapon.getWeaponCategory().getStyle() == CombatStyle.RANGED && initiator.getCharacterClass()
							.getWeaponProficiencies().contains(weapon.getWeaponCategory())) {
						modifier += initiator.getLevel().getProficiencyBonus();
					}

					boolean shouldUseDexterity = weapon.getWeaponCategory().getStyle() == CombatStyle.RANGED
							|| (shouldUseDexterityForMeleeWeapon(weapon, initiator));

					if (shouldUseDexterity) {
						modifier += initiator.getDexterity();
					} else {
						modifier += initiator.getStrengh();
					}

					break;
				}
			}
			default:
				break;
		}

		return modifier;
	}

	private boolean shouldUseDexterityForMeleeWeapon(Weapon weapon, BattleCharacterState initiator) {
		return weapon.getProperties().contains(WeaponProperty.FINESSE)
				&& initiator.getDexterity() > initiator.getStrengh();

	}
}
