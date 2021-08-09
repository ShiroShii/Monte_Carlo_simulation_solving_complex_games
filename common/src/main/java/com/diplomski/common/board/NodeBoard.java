package com.diplomski.common.board;

import java.util.HashMap;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NodeBoard implements IBoard{
	private HashMap<UUID, NodeTile> tiles;
}
