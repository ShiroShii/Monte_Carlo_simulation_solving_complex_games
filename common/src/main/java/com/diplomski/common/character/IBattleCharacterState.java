package com.diplomski.common.character;

import com.diplomski.common.resource.CombatStyle;
import com.diplomski.common.resource.IResource;

public interface IBattleCharacterState {
	public int getAttackModifier(CombatStyle combatStyle, IResource resource);
	public int getDamageModifier();
	public int getArmorClass();
	public Party getParty();
}
