package com.diplomski.backend.repository;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@AllArgsConstructor
public class RepositoryProvider {
	@Getter
	private BoardStateRepository boardStateRepository;
	
	@Getter
	private CharacterModelRepository characterModelRepository;
	
	@Getter
	private CharacterStateRepository characterStateRepository;
	
	@Getter
	private NodeBoardRepository nodeBoardRepository;
	
	@Getter
	private NodeTileRepository nodeTileRepository;
}
