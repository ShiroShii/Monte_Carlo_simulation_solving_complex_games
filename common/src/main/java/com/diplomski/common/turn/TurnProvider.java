package com.diplomski.common.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.INavigator;
import com.diplomski.common.board.ITile;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.resource.CombatStyle;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.targeting.ITargetProvider;

import lombok.AllArgsConstructor;

/**
 * Provides turn with one movement activity, then one action activity.
 */
@AllArgsConstructor
public class TurnProvider implements ITurnProvider {
	private final INavigator navigator;
	private final UUID initiatorId;
	private final Party targetParty;
	private final ITargetProvider targetProvider;
	private final IActivityProvider movementProvider;
	private final IActivityProvider actionProvider;
	private final PlayStyle playStyle;

	@Override
	public Turn getTurn(BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState;
		List<Activity> activities = new ArrayList<>();
		IBattleCharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);

		IResource resource = switch (playStyle) {
			case MELEE_WEAPON_DAMAGE -> initiator.getResources().stream()
					.filter(x -> x.getCombatStyle().equals(CombatStyle.MELEE)).findAny().get();
			case RANGED_WEAPON_DAMAGE -> initiator.getResources().stream()
					.filter(x -> x.getCombatStyle().equals(CombatStyle.RANGED)).findAny().get();
			default -> throw new IllegalArgumentException("PlayStyle not implemented");
		};

		Optional<UUID> targetIdOptional = targetProvider.getTargetId(initiatorId, targetParty, currentBoardState);

		if (targetIdOptional.isPresent()) {
			IBattleCharacterState target = currentBoardState.getCharacterStates().get(targetIdOptional.get());
			Optional<List<ITile>> optionalPath = navigator
					.getCheapestUnobstructedPath(initiator.getTileId(), target.getTileId(), currentBoardState);

			if (optionalPath.isPresent()) {
				int distance = (optionalPath.get().size() + 1) * 5;
				double rangeMultipier = resource.rangeMultiplier(distance);
				if (rangeMultipier < 1D) {
					Optional<Activity> movementActivity = movementProvider
							.getActivity(initiatorId, targetIdOptional.get(), currentBoardState, optionalPath
									.get(), distance, rangeMultipier);
					if (movementActivity.isPresent()) {
						currentBoardState = movementActivity.get().getFinalBoardState();
						activities.add(movementActivity.get());
						optionalPath = navigator.getCheapestUnobstructedPath(initiator.getTileId(), target
								.getTileId(), currentBoardState);

						distance = (optionalPath.get().size() + 1) * 5;
						rangeMultipier = resource.rangeMultiplier(distance);
					}
				}

				if (rangeMultipier > 0D) {
					Optional<Activity> actionActivity = actionProvider
							.getActivity(initiatorId, targetIdOptional.get(), currentBoardState, optionalPath
									.get(), distance, rangeMultipier, resource);
					if (actionActivity.isPresent()) {
						currentBoardState = actionActivity.get().getFinalBoardState();
						activities.add(actionActivity.get());
					}
				}
			}
		}

		return Turn.builder().initiatorId(initiatorId).initialBoardState(initialBoardState)
				.finalBoardState(currentBoardState).activities(activities).build();
	}
}
