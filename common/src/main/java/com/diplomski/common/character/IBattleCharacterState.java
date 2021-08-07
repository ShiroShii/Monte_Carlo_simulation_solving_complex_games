package com.diplomski.common.character;

import java.util.List;

import com.diplomski.common.resource.IResource;
import com.diplomski.common.turn.ITurnProvider;

public interface IBattleCharacterState {
	public String getId();
	
	public Party getParty();

	public int getCurrentHp();

	public void takeDamage(int damage);
	
	public void setUsedWalkingSpeed(int i);

	public int getArmorClass();

	public List<IResource> getResources();

	public ITurnProvider getTurnProvider();
	
	public void setTurnProvider(ITurnProvider turnProvider);
}
