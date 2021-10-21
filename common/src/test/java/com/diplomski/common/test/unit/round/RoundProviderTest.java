package com.diplomski.common.test.unit.round;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.round.Round;
import com.diplomski.common.round.RoundProvider;
import com.diplomski.common.turn.ITurnProvider;
import com.diplomski.common.turn.Turn;

public class RoundProviderTest {
	private ITurnProvider character1TurnProviderMock = mock(ITurnProvider.class);
	private ITurnProvider character2TurnProviderMock = mock(ITurnProvider.class);
	private ITurnProvider character3TurnProviderMock = mock(ITurnProvider.class);
	private RoundProvider unitUnderTest;

	private UUID character1Id = UUID.fromString("8b521099-18fd-4810-953d-bc4dde0eae14");
	private UUID character2Id = UUID.fromString("3e5aee3a-41e6-402c-a42d-6da8adc7cac9");
	private UUID character3Id = UUID.fromString("5394c3eb-c5b3-4698-a05f-9e4298f58de7");

	private final int boardState1character1CurrentHp = 20;
	private final int boardState2character1CurrentHp = 20;
	private final int boardState3character1CurrentHp = 10;
	private final int boardState1character2CurrentHp = 10;
	private final int boardState2character2CurrentHp = 0;
	private final int boardState3character2CurrentHp = 0;
	private final int boardState1character3CurrentHp = 7;
	private final int boardState2character3CurrentHp = 7;
	private final int boardState3character3CurrentHp = 7;

	private PlayerBattleCharacterState boardState1character1;
	private PlayerBattleCharacterState boardState2character1;
	private PlayerBattleCharacterState boardState3character1;
	private PlayerBattleCharacterState boardState1character2;
	private PlayerBattleCharacterState boardState2character2;
	private PlayerBattleCharacterState boardState3character2;
	private PlayerBattleCharacterState boardState1character3;
	private PlayerBattleCharacterState boardState2character3;
	private PlayerBattleCharacterState boardState3character3;

	private LinkedHashMap<UUID, IBattleCharacterState> boardState1CharacterStates;
	private LinkedHashMap<UUID, IBattleCharacterState> boardState2CharacterStates;
	private LinkedHashMap<UUID, IBattleCharacterState> boardState3CharacterStates;

	private BoardState boardState1;
	private BoardState boardState2;
	private BoardState boardState3;

	private Turn character1Turn;
	private Turn character3Turn;

	private List<Turn> expectedTurns;
	private Round expectedRound;

	@Before
	public void setup() {
		boardState1character1 = PlayerBattleCharacterState.builder()
				.party(Party.ENEMY)
				.id(character1Id)
				.turnProvider(character1TurnProviderMock)
				.currentHp(boardState1character1CurrentHp)
				.build();

		boardState1character2 = PlayerBattleCharacterState.builder()
				.party(Party.PLAYER)
				.id(character2Id)
				.turnProvider(character2TurnProviderMock)
				.currentHp(boardState1character2CurrentHp)
				.build();

		boardState1character3 = PlayerBattleCharacterState.builder()
				.party(Party.PLAYER)
				.id(character3Id)
				.turnProvider(character3TurnProviderMock)
				.currentHp(boardState1character3CurrentHp)
				.build();

		boardState1CharacterStates = new LinkedHashMap<>();
		boardState1CharacterStates.put(character1Id, boardState1character1);
		boardState1CharacterStates.put(character2Id, boardState1character2);
		boardState1CharacterStates.put(character3Id, boardState1character3);

		boardState1 = BoardState.builder()
				.characterStates(boardState1CharacterStates)
				.build();

		boardState2character1 = PlayerBattleCharacterState.builder()
				.party(Party.ENEMY)
				.id(character1Id)
				.turnProvider(character1TurnProviderMock)
				.currentHp(boardState2character1CurrentHp)
				.build();

		boardState2character2 = PlayerBattleCharacterState.builder()
				.party(Party.PLAYER)
				.id(character2Id)
				.turnProvider(character2TurnProviderMock)
				.currentHp(boardState2character2CurrentHp)
				.build();

		boardState2character3 = PlayerBattleCharacterState.builder()
				.party(Party.PLAYER)
				.id(character3Id)
				.turnProvider(character3TurnProviderMock)
				.currentHp(boardState2character3CurrentHp)
				.build();

		boardState2CharacterStates = new LinkedHashMap<>();
		boardState2CharacterStates.put(character1Id, boardState2character1);
		boardState2CharacterStates.put(character2Id, boardState2character2);
		boardState2CharacterStates.put(character3Id, boardState2character3);

		boardState2 = BoardState.builder().characterStates(boardState2CharacterStates).build();

		boardState3character1 = PlayerBattleCharacterState.builder()
				.party(Party.ENEMY)
				.id(character1Id)
				.turnProvider(character1TurnProviderMock)
				.currentHp(boardState3character1CurrentHp)
				.build();

		boardState3character2 = PlayerBattleCharacterState.builder()
				.party(Party.PLAYER)
				.id(character2Id)
				.turnProvider(character2TurnProviderMock)
				.currentHp(boardState3character2CurrentHp)
				.build();

		boardState3character3 = PlayerBattleCharacterState.builder()
				.party(Party.PLAYER)
				.id(character3Id)
				.turnProvider(character3TurnProviderMock)
				.currentHp(boardState3character3CurrentHp)
				.build();

		boardState3CharacterStates = new LinkedHashMap<>();
		boardState3CharacterStates.put(character1Id, boardState3character1);
		boardState3CharacterStates.put(character2Id, boardState3character2);
		boardState3CharacterStates.put(character3Id, boardState3character3);

		boardState3 = BoardState.builder().characterStates(boardState3CharacterStates).build();

		character1Turn = Turn.builder()
				.initiatorId(character1Id)
				.initialBoardState(boardState1)
				.finalBoardState(boardState2)
				.build();

		character3Turn = Turn.builder()
				.initiatorId(character3Id)
				.initialBoardState(boardState2)
				.finalBoardState(boardState3)
				.build();

		expectedTurns = Arrays.asList(character1Turn, character3Turn);

		expectedRound = Round.builder()
				.turns(expectedTurns)
				.initialBoardState(boardState1)
				.finalBoardState(boardState3)
				.build();

		when(character1TurnProviderMock.getTurn(boardState1)).thenReturn(character1Turn);
		when(character3TurnProviderMock.getTurn(boardState2)).thenReturn(character3Turn);

		unitUnderTest = new RoundProvider();
	}

	@Test
	public void getRound() {
		Round result = unitUnderTest.getRound(boardState1);

		assertEquals(expectedRound, result);
		verify(character1TurnProviderMock, times(1)).getTurn(eq(boardState1));
		verify(character3TurnProviderMock, times(1)).getTurn(eq(boardState2));
	}

	@Test
	public void getRound_endAfterFirstTurn() {
		boardState1character1.setParty(Party.PLAYER);
		boardState2character1.setParty(Party.PLAYER);
		boardState3character1.setParty(Party.PLAYER);

		boardState1character2.setParty(Party.ENEMY);
		boardState2character2.setParty(Party.ENEMY);
		boardState3character2.setParty(Party.ENEMY);

		expectedRound.setTurns(Arrays.asList(character1Turn));

		expectedRound.setFinalBoardState(boardState2);

		Round result = unitUnderTest.getRound(boardState1);

		assertEquals(expectedRound, result);
		verify(character1TurnProviderMock, times(1)).getTurn(eq(boardState1));
	}
}
