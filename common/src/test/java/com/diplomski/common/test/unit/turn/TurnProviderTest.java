package com.diplomski.common.test.unit.turn;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
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
	private IActivityProvider movementActivityProviderMock = mock(IActivityProvider.class);
	private IActivityProvider actionActivityProviderMock = mock(IActivityProvider.class);
	private INavigator navigatorMock = mock(INavigator.class);

	private Activity movementActivity = mock(Activity.class);
	private Activity actionActivity = mock(Activity.class);

	private BoardState battleState1;
	private BoardState battleState2;
	private BoardState battleState3;

	private ITile distanceTile = mock(ITile.class);
	
	private final String INITIATOR_ID = "Initiator Id";
	private final String TARGET_ID = "Target Id";
	private final UUID INITIATOR_TILE_ID = UUID.fromString("42d48df1-ccc6-4133-9197-2da414e8a26f");
	private final UUID TARGET_TILE_ID = UUID.fromString("498c3248-818a-47d8-a692-c7c9069342ab");
	private final PlayStyle PLAY_STYLE = PlayStyle.MELEE_WEAPON_DAMAGE;
	private final Party TARGET_PARTY = Party.ENEMY;
	private PlayerBattleCharacterState initiator;
	private PlayerBattleCharacterState target;
	LinkedHashMap<String, IBattleCharacterState> characters;

	private List<IResource> weapons;
	private List<Activity> expectedActivities;
	private Turn expectedTurn;
	private Turn emptyTurn;

	TurnProvider unitUnderTest;

	@Before
	public void setup() {
		weapons = Arrays.asList(Weapon.CLUB);
		initiator = PlayerBattleCharacterState.builder().id(INITIATOR_ID).tileId(INITIATOR_TILE_ID).resources(weapons).build();
		target = PlayerBattleCharacterState.builder().id(TARGET_ID).tileId(TARGET_TILE_ID).resources(weapons).build();
		characters = new LinkedHashMap<>();
		characters.put(INITIATOR_ID, initiator);
		when(navigatorMock.getCheapestUnobstructedPath(any(), any(), any())).thenReturn(Optional.of(Arrays.asList(distanceTile))).thenReturn(Optional.of(new ArrayList<>()));

		battleState1 = BoardState.builder().characterStates(characters).build();
		battleState2 = BoardState.builder().characterStates(characters).build();
		battleState3 = BoardState.builder().characterStates(characters).build();

		when(movementActivity.getFinalBoardState()).thenReturn(battleState2);
		when(actionActivity.getFinalBoardState()).thenReturn(battleState3);

		when(movementActivityProviderMock.getActivity(any(), any(), any(), any(), anyInt(), anyDouble()))
				.thenReturn(Optional.of(movementActivity));
		when(actionActivityProviderMock.getActivity(any(), any(), any(), any(), anyInt(), anyDouble(), any()))
				.thenReturn(Optional.of(actionActivity));

		expectedActivities = Arrays.asList(movementActivity, actionActivity);

		expectedTurn = Turn.builder().initiatorId(INITIATOR_ID).initialBoardState(battleState1)
				.finalBoardState(battleState3).activities(expectedActivities).build();

		emptyTurn = Turn.builder().initiatorId(INITIATOR_ID).initialBoardState(battleState1)
				.finalBoardState(battleState1).activities(new ArrayList<>()).build();

		unitUnderTest = new TurnProvider(navigatorMock, INITIATOR_ID, TARGET_PARTY, targetProviderMock, movementActivityProviderMock, actionActivityProviderMock, PLAY_STYLE);
	}

	@Test
	public void getTurn_withTarget() {
		when(targetProviderMock.getTargetId(any(), any(), any())).thenReturn(Optional.of(TARGET_ID));
		characters.put(TARGET_ID, target);

		Turn result = unitUnderTest.getTurn(battleState1);

		assertEquals(expectedTurn, result);
	}

	@Test
	public void getTurn_withoutTarget() {
		when(targetProviderMock.getTargetId(any(), any(), any())).thenReturn(Optional.empty());

		Turn result = unitUnderTest.getTurn(battleState1);

		assertEquals(emptyTurn, result);
	}
}
