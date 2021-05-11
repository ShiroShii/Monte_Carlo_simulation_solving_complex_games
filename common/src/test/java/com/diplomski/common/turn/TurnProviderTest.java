package com.diplomski.common.turn;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.Party;
import com.diplomski.common.targeting.ITargetProvider;

public class TurnProviderTest {
	private ITargetProvider targetProviderMock = mock(ITargetProvider.class);
	private IActivityProvider movementActivityProviderMock = mock(IActivityProvider.class);
	private IActivityProvider actionActivityProviderMock = mock(IActivityProvider.class);

	private Activity movementActivity = mock(Activity.class);
	private Activity actionActivity = mock(Activity.class);

	private BoardState battleState1;
	private BoardState battleState2;
	private BoardState battleState3;

	private final int INITIATOR_INDEX = 0;
	private final int TARGET_INDEX = 1;

	private final Party TARGET_PARTY = Party.ENEMY;

	private List<Activity> expectedActivities;
	private Turn expectedTurn;
	private Turn emptyTurn;

	TurnProvider unitUnderTest;

	@Before
	public void setup() {
		battleState1 = BoardState.builder().build();
		battleState2 = BoardState.builder().build();
		battleState3 = BoardState.builder().build();

		when(movementActivity.getFinalBoardState()).thenReturn(battleState2);
		when(actionActivity.getFinalBoardState()).thenReturn(battleState3);

		when(movementActivityProviderMock.getActivity(anyInt(), anyInt(), any())).thenReturn(movementActivity);
		when(actionActivityProviderMock.getActivity(anyInt(), anyInt(), any())).thenReturn(actionActivity);

		expectedActivities = Arrays.asList(movementActivity, actionActivity);

		expectedTurn = Turn.builder().initiatorIndex(INITIATOR_INDEX).initialBoardState(battleState1)
				.finalBoardState(battleState3).activities(expectedActivities).build();

		emptyTurn = Turn.builder().initiatorIndex(INITIATOR_INDEX).initialBoardState(battleState1)
				.finalBoardState(battleState1).activities(new ArrayList<>()).build();

		unitUnderTest = new TurnProvider(TARGET_PARTY, targetProviderMock, movementActivityProviderMock,
				actionActivityProviderMock);
	}

	@Test
	public void getTurn_withTarget() {
		when(targetProviderMock.getTargetCharacterIndex(anyInt(), any(), any())).thenReturn(Optional.of(TARGET_INDEX));

		Turn result = unitUnderTest.getTurn(INITIATOR_INDEX, battleState1);

		assertEquals(expectedTurn, result);
	}

	@Test
	public void getTurn_withoutTarget() {
		when(targetProviderMock.getTargetCharacterIndex(anyInt(), any(), any())).thenReturn(Optional.empty());
		
		Turn result = unitUnderTest.getTurn(INITIATOR_INDEX, battleState1);

		assertEquals(emptyTurn, result);
	}
}
