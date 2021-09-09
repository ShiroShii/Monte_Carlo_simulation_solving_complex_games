package com.diplomski.backend.contract;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattleCreateRequest {
	private String name;
	private List<NodeTileCreateRequest> tiles;
}
