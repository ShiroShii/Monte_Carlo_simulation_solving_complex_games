package com.diplomski.common.test.unit.turn;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.activity.movement.MovementActivity;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.INavigator;
import com.diplomski.common.board.ITile;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.targeting.ITargetProvider;
import com.diplomski.common.turn.Turn;
import com.diplomski.common.turn.TurnProvider;

public class TurnProviderTest {
	private ITargetProvider targetProviderMock = mock(ITargetProvider.class);
	private IActivityProvider movementProviderMock = mock(IActivityProvider.class);
	private IActivityProvider actionProviderMock = mock(IActivityProvider.class);
	private INavigator navigatorMock = mock(INavigator.class);

	private MovementActivity movementActivity = mock(MovementActivity.class);
	private Activity actionActivity = mock(Activity.class);

	private BoardState boardState1;
	private BoardState boardState2;
	private BoardState boardState3;

	private ITile distanceTile = mock(ITile.class);

	private final UUID INITIATOR_ID = UUID.fromString("8b521099-18fd-4810-953d-bc4dde0eae14");
	private final UUID TARGET_ID = UUID.fromString("3e5aee3a-41e6-402c-a42d-6da8adc7cac9");
	private final UUID INITIATOR_TILE_ID = UUID.fromString("42d48df1-ccc6-4133-9197-2da414e8a26f");
	private final UUID DISTANCE_TILE_ID = UUID.fromString("14df2367-a0d8-4a3f-ba90-d652d88c7464");
	private final UUID TARGET_TILE_ID = UUID.fromString("498c3248-818a-47d8-a692-c7c9069342ab");
	private final PlayStyle PLAY_STYLE = PlayStyle.MELEE_DAMAGE;
	private final Party TARGET_PARTY = Party.ENEMY;
	private PlayerBattleCharacterState initiator;
	private PlayerBattleCharacterState target;
	LinkedHashMap<UUID, IBattleCharacterState> characters;

	private IResource weapon;
	private List<IResource> weapons;
	private List<Activity> expectedActivities;
	private Turn expectedTurn;
	private Turn emptyTurn;

	TurnProvider unitUnderTest;

	@Before
	public void setup() {
		weapon = Weapon.CLUB;
		weapons = Arrays.asList(weapon);
		initiator = PlayerBattleCharacterState.builder()
				.id(INITIATOR_ID)
				.tileId(INITIATOR_TILE_ID)
				.resources(weapons)
				.build();

		target = PlayerBattleCharacterState.builder()
				.id(TARGET_ID)
				.tileId(TARGET_TILE_ID)
				.resources(weapons)
				.build();

		characters = new LinkedHashMap<>();
		characters.put(INITIATOR_ID, initiator);
		characters.put(TARGET_ID, target);

		when(navigatorMock.getCheapestUnobstructedPath(any(), any(), any()))
				.thenReturn(Optional.of(Arrays.asList(distanceTile)))
				.thenReturn(Optional.of(new ArrayList<>()));

		when(distanceTile.getId()).thenReturn(DISTANCE_TILE_ID);

		boardState1 = BoardState.builder().characterStates(characters).build();
		boardState2 = BoardState.builder().characterStates(characters).build();
		boardState3 = BoardState.builder().characterStates(characters).build();

		when(movementActivity.getFinalTileId()).thenReturn(DISTANCE_TILE_ID);
		when(movementActivity.getFinalBoardState()).thenReturn(boardState2);
		when(actionActivity.getFinalBoardState()).thenReturn(boardState3);

		when(targetProviderMock.getTargetId(eq(INITIATOR_ID), eq(TARGET_PARTY), eq(boardState1)))
				.thenReturn(Optional.of(TARGET_ID));

		when(movementProviderMock.getActivity(any(), any(), any(), any(), anyInt(), anyDouble()))
				.thenReturn(Optional.of(movementActivity));
		when(actionProviderMock.getActivity(any(), any(), any(), any(), anyInt(), anyDouble(), any()))
				.thenReturn(Optional.of(actionActivity));

		expectedActivities = Arrays.asList(movementActivity, actionActivity);

		expectedTurn = Turn.builder().initiatorId(INITIATOR_ID).initialBoardState(boardState1)
				.finalBoardState(boardState3).activities(expectedActivities).build();

		emptyTurn = Turn.builder().initiatorId(INITIATOR_ID).initialBoardState(boardState1)
				.finalBoardState(boardState1).activities(new ArrayList<>()).build();

		unitUnderTest =
				new TurnProvider(navigatorMock, INITIATOR_ID, TARGET_PARTY, targetProviderMock, movementProviderMock, actionProviderMock, PLAY_STYLE);
	}

	@Test
	public void getTurn() {
		Turn result = unitUnderTest.getTurn(boardState1);

		assertEquals(expectedTurn, result);
		verifyGetTargetCalled(1);
		verifyGetPathCalled(2);
		verifyGetMovementCalled(1);
		verifyGetActionCalled(1);

		verify(targetProviderMock, times(1)).getTargetId(
				eq(INITIATOR_ID),
				eq(TARGET_PARTY),
				eq(boardState1));

		verify(navigatorMock, times(1)).getCheapestUnobstructedPath(
				eq(INITIATOR_TILE_ID),
				eq(TARGET_TILE_ID),
				eq(boardState1));

		verify(navigatorMock, times(1)).getCheapestUnobstructedPath(
				eq(DISTANCE_TILE_ID),
				eq(TARGET_TILE_ID),
				eq(boardState2));

		verify(movementProviderMock, times(1)).getActivity(
				eq(INITIATOR_ID),
				eq(TARGET_ID),
				eq(boardState1),
				eq(Arrays.asList(distanceTile)),
				eq(10),
				eq(0d));

		verify(actionProviderMock, times(1)).getActivity(
				eq(INITIATOR_ID),
				eq(TARGET_ID),
				eq(boardState1),
				eq(Arrays.asList()),
				eq(5),
				eq(1d),
				eq(weapon));
	}

	private void verifyGetPathCalled(int times) {
		verify(navigatorMock, times(times)).getCheapestUnobstructedPath(
				any(),
				any(),
				any());
	}

	private void verifyGetTargetCalled(int times) {
		verify(targetProviderMock, times(times)).getTargetId(any(), any(), any());
	}

	private void verifyGetMovementCalled(int times) {
		verify(movementProviderMock, times(times)).getActivity(
				any(),
				any(),
				any(),
				any(),
				anyInt(),
				anyDouble());
	}

	private void verifyGetActionCalled(int times) {
		verify(actionProviderMock, times(times)).getActivity(
				any(),
				any(),
				any(),
				any(),
				anyInt(),
				anyDouble(),
				any());
	}

	@Test
	public void getTurn_withoutTarget() {
		characters.remove(TARGET_ID);
		when(targetProviderMock.getTargetId(eq(INITIATOR_ID), eq(TARGET_PARTY), eq(boardState1)))
				.thenReturn(Optional.empty());

		Turn result = unitUnderTest.getTurn(boardState1);

		assertEquals(emptyTurn, result);
		verifyGetTargetCalled(1);
		verify(targetProviderMock, times(1)).getTargetId(eq(INITIATOR_ID), eq(TARGET_PARTY), eq(boardState1));
		verifyGetPathCalled(0);
		verifyGetActionCalled(0);
		verifyGetMovementCalled(0);
	}
}
