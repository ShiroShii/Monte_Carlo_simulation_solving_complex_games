package com.diplomski.common.activity.action.attack;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.resource.WeaponProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerAttackRollModifierProvider implements IAttackRollModifierProvider {
	@Override
	public int getAttackRollModifier(IResource resource, IBattleCharacterState initiator) {
		int modifier = 0;
		PlayerBattleCharacterState player = (PlayerBattleCharacterState) initiator;
		if (resource instanceof Weapon) {
			Weapon weapon = (Weapon) resource;

			if (player.getCharacterClass().getWeaponProficiencies().contains(weapon.getWeaponCategory())) {
				modifier += player.getLevel().getProficiencyBonus();
			}
			
			switch(weapon.getCombatStyle()) {
				case MELEE:{
					if (shouldUseDexterityForMeleeWeapon(weapon, player)) {
						modifier += Math.floor((player.getDexterity() - 10) / 2.0d);
					} else {
						modifier += Math.floor((player.getStrength() -10) / 2.0d);
					}
					
					break;
				}
				case RANGED:{
					modifier += Math.floor((player.getDexterity() - 10) / 2.0d);
					break;
				}
			}
		}

		return modifier;
	}

	private boolean shouldUseDexterityForMeleeWeapon(Weapon weapon, PlayerBattleCharacterState initiator) {
		return weapon.getProperties().contains(WeaponProperty.FINESSE)
				&& initiator.getDexterity() > initiator.getStrength();

	}
}
