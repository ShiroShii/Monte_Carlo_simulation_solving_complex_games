package com.diplomski.backend.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diplomski.backend.contract.BattleOutcomeConvergence;
import com.diplomski.backend.contract.CategoryContract;
import com.diplomski.backend.contract.DownedPlayerContract;
import com.diplomski.backend.contract.NameValueIntPair;
import com.diplomski.backend.contract.PlayerBoxPlot;
import com.diplomski.backend.contract.PlayerCharacterStateResponse;
import com.diplomski.backend.contract.PlayerReportContract;
import com.diplomski.backend.contract.SimulationResponse;
import com.diplomski.backend.dal.PlayerCharacterStateDbModel;
import com.diplomski.common.simulation.PlayerReport;
import com.diplomski.common.simulation.SimulationReport;
import com.diplomski.common.simulation.StatReport;

public class SimulationTranslator {
	public static PlayerCharacterStateResponse translate(PlayerCharacterStateDbModel input) {
		return PlayerCharacterStateResponse.builder()
				.id(input.getId())
				.playerCharacterId(input.getPlayerCharacter().getId())
				.currentHp(input.getCurrentHp())
				.targetingStyle(input.getTargetingStyle())
				.playStyle(input.getPlayStyle())
				.tileId(input.getNodeTile().getId())
				.build();
	}

	public static List<CategoryContract> translate(StatReport input, String category) {
		List<CategoryContract> output = new ArrayList<>();

		output.add(CategoryContract.builder().category(category).value(input.getMin()).label("Lower Extreme").build());
		output.add(CategoryContract.builder().category(category).value(input.getLowerQuantile()).label("Lower Quartile")
				.build());
		output.add(CategoryContract.builder().category(category).value(input.getMedian()).label("Median").build());
		output.add(CategoryContract.builder().category(category).value(input.getUpperQuantile()).label("Upper Quartile")
				.build());
		output.add(CategoryContract.builder().category(category).value(input.getMax()).label("Upper Extreme").build());

		return output;
	}

	public static PlayerReportContract translate(PlayerReport input) {
		return PlayerReportContract.builder()
				.downCount(input.getDownCount())
				.id(input.getId())
				.name(input.getName())
				.playerBoxPlot(PlayerBoxPlot.builder()
						.health(input.getPlayerBoxPlot().getHealth() != null
								? translate(input.getPlayerBoxPlot().getHealth(), "Health")
								: null)
						.damageDealt(input.getPlayerBoxPlot().getDamageDealt() != null
								? translate(input.getPlayerBoxPlot().getDamageDealt(), "Damage Dealt")
								: null)
						.damageTaken(input.getPlayerBoxPlot().getDamageTaken() != null
								? translate(input.getPlayerBoxPlot().getDamageDealt(), "Damage Taken")
								: null)
						.build())
				.build();
	}

	public static SimulationResponse translate(SimulationReport input, UUID battleId) {
		List<BattleOutcomeConvergence> battleOutcomeConvergence = new ArrayList<>();

		for (int i = 1; i <= input.getSimulationCount(); i++) {
			battleOutcomeConvergence
					.add(BattleOutcomeConvergence.builder()
							.count(i)
							.drawRate(input.getDrawRateConvergence().get(i))
							.winRate(input.getWinRateConvergence().get(i))
							.lossRate(input.getLossRateConvergence().get(i))
							.build());
		}

		List<DownedPlayerContract> downedPlayers = input.getDownedPlayers().stream().sequential()
				.map(x -> DownedPlayerContract.builder().downedCount(x.get_1()).simulationCount(x.get_2())
						.downedPercentage(100f * (float) x.get_1() / (float) input.getInitialPlayerCount()).build())
				.toList();

		List<NameValueIntPair> outcomes = input.getOutcomes().stream().sequential()
				.map(x -> NameValueIntPair.builder().name(x.get_1().toString()).value(x.get_2()).build()).toList();

		List<NameValueIntPair> battleOutcomeSlices = new ArrayList<>();
		battleOutcomeSlices.add(NameValueIntPair.builder().name("Wins").value(input.getWinCount()).build());
		battleOutcomeSlices.add(NameValueIntPair.builder().name("Draws").value(input.getDrawCount()).build());
		battleOutcomeSlices.add(NameValueIntPair.builder().name("Losses").value(input.getLossCount()).build());

		List<CategoryContract> health = input.getPlayerWinStateReport().getHealth() != null
				? translate(input.getPlayerWinStateReport().getHealth(), "Health")
				: null;
		List<CategoryContract> damageDealt = input.getPlayerWinStateReport().getDamageDealt() != null
				? translate(input.getPlayerWinStateReport().getDamageDealt(), "Damage Dealt")
				: null;
		List<CategoryContract> damageTaken = input.getPlayerWinStateReport().getDamageTaken() != null
				? translate(input.getPlayerWinStateReport().getDamageTaken(), "Damage Taken")
				: null;

		PlayerBoxPlot playerBoxPlot = PlayerBoxPlot.builder().health(health).damageDealt(damageDealt)
				.damageTaken(damageTaken).build();

		List<PlayerReportContract> playerReports = input.getPlayerReports().stream().map(x -> translate(x)).toList();

		return SimulationResponse.builder()
				.battleId(battleId)
				.roundCountLimit(input.getRoundCountLimit())
				.simulationCount(input.getSimulationCount())
				.battleOutcomeConvergence(battleOutcomeConvergence)
				.playerBoxPlot(playerBoxPlot)
				.downedPlayers(downedPlayers)
				.battleOutcomeBars(outcomes)
				.battleOutcomeSlices(battleOutcomeSlices)
				.initialPlayerCount(input.getInitialPlayerCount())
				.playerReports(playerReports)
				.build();
	}
}
