package com.diplomski.common.round;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.turn.ITurnProvider;
import com.diplomski.common.turn.Turn;

public class RoundProviderTest {
	private ITurnProvider turnProviderMock = mock(ITurnProvider.class);
	private RoundProvider unitUnderTest;

	private int boardState1character1CurrentHp = 20;
	private int boardState2character1CurrentHp = 20;
	private int boardState3character1CurrentHp = 10;
	private int boardState1character2CurrentHp = 10;
	private int boardState2character2CurrentHp = 0;
	private int boardState3character2CurrentHp = 0;
	private int boardState1character3CurrentHp = 7;
	private int boardState2character3CurrentHp = 7;
	private int boardState3character3CurrentHp = 7;

	private CharacterState boardState1character1;
	private CharacterState boardState2character1;
	private CharacterState boardState3character1;
	private CharacterState boardState1character2;
	private CharacterState boardState2character2;
	private CharacterState boardState3character2;
	private CharacterState boardState1character3;
	private CharacterState boardState2character3;
	private CharacterState boardState3character3;

	private List<CharacterState> boardState1CharacterStates;
	private List<CharacterState> boardState2CharacterStates;
	private List<CharacterState> boardState3CharacterStates;

	private BoardState boardState1;
	private BoardState boardState2;
	private BoardState boardState3;

	private Turn character1Turn;
	private Turn character3Turn;

	private List<Turn> expectedTurns;
	private Round expectedRound;

	@Before
	public void setup() {
		boardState1character1 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState1character1CurrentHp)
				.build();

		boardState1character2 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState1character2CurrentHp)
				.build();

		boardState1character3 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState1character3CurrentHp)
				.build();

		boardState1CharacterStates = Arrays.asList(boardState1character1, boardState1character2, boardState1character3);

		boardState1 = BoardState.builder().characterStates(boardState1CharacterStates).build();

		boardState2character1 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState2character1CurrentHp)
				.build();

		boardState2character2 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState2character2CurrentHp)
				.build();

		boardState2character3 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState2character3CurrentHp)
				.build();

		boardState2CharacterStates = Arrays.asList(boardState2character1, boardState2character2, boardState2character3);

		boardState2 = BoardState.builder().characterStates(boardState2CharacterStates).build();

		boardState3character1 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState3character1CurrentHp)
				.build();

		boardState3character2 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState3character2CurrentHp)
				.build();

		boardState3character3 = CharacterState.builder().turnProvider(turnProviderMock).currentHp(boardState3character3CurrentHp)
				.build();

		boardState3CharacterStates = Arrays.asList(boardState3character1, boardState3character2, boardState3character3);

		boardState3 = BoardState.builder().characterStates(boardState3CharacterStates).build();

		character1Turn = Turn.builder().initiatorIndex(0).initialBoardState(boardState1).finalBoardState(boardState2)
				.build();
		character3Turn = Turn.builder().initiatorIndex(2).initialBoardState(boardState2).finalBoardState(boardState3)
				.build();

		expectedTurns = Arrays.asList(character1Turn, character3Turn);

		expectedRound = Round.builder().turns(expectedTurns).initialBoardState(boardState1).finalBoardState(boardState3)
				.build();

		when(turnProviderMock.getTurn(0, boardState1)).thenReturn(character1Turn);
		when(turnProviderMock.getTurn(2, boardState2)).thenReturn(character3Turn);

		unitUnderTest = new RoundProvider();
	}

	@Test
	public void getRound() {
		Round result = unitUnderTest.getRound(boardState1);

		assertEquals(expectedRound, result);
		verify(turnProviderMock, times(2)).getTurn(anyInt(), any());
		verify(turnProviderMock, times(1)).getTurn(eq(0), eq(boardState1));
		verify(turnProviderMock, times(1)).getTurn(eq(2), eq(boardState2));
		verify(turnProviderMock, never()).getTurn(eq(1), any());
	}
}
