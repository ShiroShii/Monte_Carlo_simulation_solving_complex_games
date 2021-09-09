package com.diplomski.backend.service;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.diplomski.backend.dal.BattleDbModel;
import com.diplomski.backend.repository.BattleRepository;
import com.diplomski.common.board.NodeBoard;
import com.diplomski.common.board.NodeTile;
import com.diplomski.common.character.ICharacterState;
import com.diplomski.common.character.MonsterCharacterState;
import com.diplomski.common.character.PlayerCharacterState;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.simulation.ISimulationService;
import com.diplomski.common.simulation.SimulationReport;
import com.diplomski.common.simulation.SimulationServiceFactory;

@Component
public class SimulationServiceComponent {
	private ISimulationService simulationService;
	private BattleRepository battleRepository;

	public SimulationServiceComponent(BattleRepository battleRepository) {
		simulationService = SimulationServiceFactory.getSimulationService();
		this.battleRepository = battleRepository;
	}

	public SimulationReport getSimulation(UUID battleId, int simulationCount, int roundCountLimit) throws Exception {
		Optional<BattleDbModel> battleDbModel = battleRepository.findById(battleId);
		if (battleDbModel.isEmpty()) {
			throw new Exception("Battle not found");
		}
		HashMap<UUID, NodeTile> nodeTiles = new HashMap<>();

		battleDbModel.get().getNodeTiles().stream()
				.map(x -> NodeTile.builder().id(x.getId()).terrainType(x.getTerrainFeature())
						.reachableTiles(x.getReachableNodes().stream().map(y -> y.getId())
								.collect(Collectors.toCollection(HashSet::new)))
						.build())
				.forEach(x -> nodeTiles.put(x.getId(), x));

		NodeBoard nodeBoard = NodeBoard.builder().tiles(nodeTiles).build();

		List<ICharacterState> initialCharacterStates = new ArrayList<>();

		battleDbModel.get().getNodeTiles().stream().flatMap(x -> x.getCharacterStates().stream())
				.forEach(x -> initialCharacterStates.add(PlayerCharacterState.builder()
						.party(PLAYER)
						.id(x.getId())
						.name(x.getPlayerCharacter().getName())
						.level(x.getPlayerCharacter().getCharacterLevel())
						.armorClass(x.getPlayerCharacter().getArmorClass())
						.dexterity(x.getPlayerCharacter().getDexterity())
						.resources(x.getPlayerCharacter().getWeapons().stream().map(y -> (IResource) y).toList())
						.characterClass(x.getPlayerCharacter().getCharacterClass())
						.currentHp(x.getCurrentHp())
						.playStyle(x.getPlayStyle()).targetingStyle(x.getTargetingStyle())
						.strength(x.getPlayerCharacter().getStrength())
						.tileId(x.getNodeTile().getId())
						.walkingSpeed(x.getPlayerCharacter().getWalkingSpeed())
						.build()));

		battleDbModel.get().getNodeTiles().stream().flatMap(x -> x.getMonsterStates().stream())
				.forEach(x -> initialCharacterStates.add(MonsterCharacterState.builder()
						.targetingStyle(x.getTargetingStyle())
						.playStyle(x.getPlayStyle())
						.tileId(x.getNodeTile().getId())
						.currentHp(x.getCurrentHp())
						.party(ENEMY)
						.monster(x.getMonster())
						.id(x.getId())
						.build()));

		return simulationService.getSimulation(initialCharacterStates, nodeBoard, simulationCount, roundCountLimit);
	}
}
