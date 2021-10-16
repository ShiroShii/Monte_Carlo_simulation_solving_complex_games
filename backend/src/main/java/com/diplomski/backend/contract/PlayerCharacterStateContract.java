package com.diplomski.backend.contract;

import java.util.UUID;

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
public class PlayerCharacterStateContract {
	private UUID playerCharacterId;
	private int currentHp;
	private PlayStyle playStyle;
	private TargetingStyle targetingStyle;
}
