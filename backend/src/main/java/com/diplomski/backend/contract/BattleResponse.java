package com.diplomski.backend.contract;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattleResponse {
	private UUID id;
	private String name;
	private List<PlayerCharacterStateResponse> playerCharacterStates;
	private List<MonsterStateResponse> monsterStates;
	private List<NodeTileResponse> tiles;
}
