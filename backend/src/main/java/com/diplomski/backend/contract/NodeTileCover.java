package com.diplomski.backend.contract;

import com.diplomski.common.board.Cover;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NodeTileCover {
	private Cover cover;
	private int id;
}
