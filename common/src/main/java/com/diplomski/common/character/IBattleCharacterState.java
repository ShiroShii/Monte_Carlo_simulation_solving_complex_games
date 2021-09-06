package com.diplomski.common.character;

import java.util.List;
import java.util.UUID;

import com.diplomski.common.resource.IResource;
import com.diplomski.common.turn.ITurnProvider;

public interface IBattleCharacterState {
	public UUID getId();
	
	public Party getParty();

	public int getCurrentHp();

	public UUID getTileId();
	
	public void takeDamage(int damage);
	
	public int getWalkingSpeed();
	
	public void setUsedWalkingSpeed(int i);
	
	public void setUsedFlyingSpeed(int i);

	public int getArmorClass();

	public List<IResource> getResources();

	public ITurnProvider getTurnProvider();
	
	public void setTurnProvider(ITurnProvider turnProvider);

	public void setTileId(UUID id);
}
