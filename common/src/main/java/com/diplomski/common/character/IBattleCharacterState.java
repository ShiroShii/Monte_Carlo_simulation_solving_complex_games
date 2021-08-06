package com.diplomski.common.character;

import java.util.Collection;

import com.diplomski.common.resource.Weapon;
import com.diplomski.common.targeting.TargetingStyle;
import com.diplomski.common.turn.ITurnProvider;

public interface IBattleCharacterState {
	public String getId();
	//public int getAttackModifier(CombatStyle combatStyle, IResource resource);
	//public int getDamageModifier();

	public void setUsedWalkingSpeed(int i);

	public Party getParty();

	public int getCurrentHp();

	public void takeDamage(int damage);

	public void setTurnProvider(ITurnProvider turnProvider);

	public int getArmorClass();

	public CharacterLevel getLevel();

	public PlayStyle getPlayStyle();

	public TargetingStyle getTargetingStyle();

	public Collection<Weapon> getWeapons();

	public ITurnProvider getTurnProvider();

	public int getDexterity();

	public CharacterClass getCharacterClass();

	public int getStrength();
}
