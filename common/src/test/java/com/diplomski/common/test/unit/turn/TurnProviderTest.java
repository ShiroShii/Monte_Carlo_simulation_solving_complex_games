package com.diplomski.common.test.unit.turn;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import com.diplomski.common.turn.Turn;
import com.diplomski.common.turn.TurnProvider;

public class TurnProviderTest {
	private ITargetProvider targetProviderMock = mock(ITargetProvider.class);
	private IActivityProvider movementActivityProviderMock = mock(IActivityProvider.class);
	private IActivityProvider actionActivityProviderMock = mock(IActivityProvider.class);

	private Activity movementActivity = mock(Activity.class);
	private Activity actionActivity = mock(Activity.class);

	private BoardState battleState1;
	private BoardState battleState2;
	private BoardState battleState3;

	private final String INITIATOR_ID = "Initiator Id";
	private final String TARGET_ID = "Target Id";

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

		when(movementActivityProviderMock.getActivity(any(), any(), any())).thenReturn(movementActivity);
		when(actionActivityProviderMock.getActivity(any(), any(), any())).thenReturn(actionActivity);

		expectedActivities = Arrays.asList(movementActivity, actionActivity);

		expectedTurn = Turn.builder().initiatorId(INITIATOR_ID).initialBoardState(battleState1)
				.finalBoardState(battleState3).activities(expectedActivities).build();

		emptyTurn = Turn.builder().initiatorId(INITIATOR_ID).initialBoardState(battleState1)
				.finalBoardState(battleState1).activities(new ArrayList<>()).build();

		unitUnderTest = new TurnProvider(INITIATOR_ID, TARGET_PARTY, targetProviderMock, movementActivityProviderMock,
				actionActivityProviderMock);
	}

	@Test
	public void getTurn_withTarget() {
		when(targetProviderMock.getTargetId(any(), any(), any())).thenReturn(Optional.of(TARGET_ID));

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
