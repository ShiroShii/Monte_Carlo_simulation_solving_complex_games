package com.diplomski.backend.contract;

import java.util.UUID;

import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.targeting.TargetingStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCharacterStateCreateRequest {
	private UUID playerCharacterId;
	private int currentHp;
	private UUID tileId;
	private Party party;
	private PlayStyle playStyle;
	private TargetingStyle targetingStyle;
}
