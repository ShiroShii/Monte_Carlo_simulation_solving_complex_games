package com.diplomski.backend.contract;

import com.diplomski.common.character.Monster;
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
public class MonsterStateContract {
	private Monster monster;
	private int currentHp;
	private PlayStyle playStyle;
	private TargetingStyle targetingStyle;
}
