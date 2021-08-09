package com.diplomski.common.test.unit.battle;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.battle.Battle;
import com.diplomski.common.battle.BattleProvider;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.IBoard;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.ICharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.round.IRoundProvider;
import com.diplomski.common.round.Round;

public class BattleProviderTest {
	private IBoardStateProvider boardStateProviderMock = mock(IBoardStateProvider.class);
	private IRoundProvider roundProviderMock = mock(IRoundProvider.class);

	private IBoard boardMock = mock(IBoard.class);
	
	private final int ROUND_COUNT_LIMIT = 5;
	private String character1Id = "Player 1";
	private String character2Id = "Player 2";
	private String character3Id = "Enemy 1";
	private Party character1Party = Party.PLAYER;
	private Party character2Party = Party.PLAYER;
	private Party character3Party = Party.ENEMY;

	private int boardState1character1CurrentHp = 10;
	private int boardState2character1CurrentHp = 5;
	private int boardState3character1CurrentHp = 3;
	private int boardState1character2CurrentHp = 20;
	private int boardState2character2CurrentHp = 0;
	private int boardState3character2CurrentHp = 0;
	private int boardState1character3CurrentHp = 55;
	private int boardState2character3CurrentHp = 25;
	private int boardState3character3CurrentHp = 0;

	private PlayerBattleCharacterState boardState1character1;
	private PlayerBattleCharacterState boardState2character1;
	private PlayerBattleCharacterState boardState3character1;
	private PlayerBattleCharacterState boardState1character2;
	private PlayerBattleCharacterState boardState2character2;
	private PlayerBattleCharacterState boardState3character2;
	private PlayerBattleCharacterState boardState1character3;
	private PlayerBattleCharacterState boardState2character3;
	private PlayerBattleCharacterState boardState3character3;

	private List<ICharacterState> initialCharacterStates;
	private LinkedHashMap<String, IBattleCharacterState> boardState1CharacterStates;
	private LinkedHashMap<String, IBattleCharacterState> boardState2CharacterStates;
	private LinkedHashMap<String, IBattleCharacterState> boardState3CharacterStates;

	private BoardState boardState1;
	private BoardState boardState2;
	private BoardState boardState3;

	private Round round1;
	private Round round2;

	private List<Round> rounds;
	private Battle expectedBattle;

	private BattleProvider unitUnderTest;

	@Before
	public void setup() {
		boardState1character1 = PlayerBattleCharacterState.builder().id(character1Id)
				.currentHp(boardState1character1CurrentHp).party(character1Party).build();

		boardState1character2 = PlayerBattleCharacterState.builder().id(character2Id)
				.currentHp(boardState1character2CurrentHp).party(character2Party).build();

		boardState1character3 = PlayerBattleCharacterState.builder().id(character3Id)
				.currentHp(boardState1character3CurrentHp).party(character3Party).build();

		initialCharacterStates = Arrays.asList(boardState1character1, boardState1character2, boardState1character3);

		boardState1CharacterStates = new LinkedHashMap<>();
		boardState1CharacterStates.put(character1Id, boardState1character1);
		boardState1CharacterStates.put(character2Id, boardState1character2);
		boardState1CharacterStates.put(character3Id, boardState1character3);

		boardState1 = BoardState.builder().characterStates(boardState1CharacterStates).build();

		boardState2character1 = PlayerBattleCharacterState.builder().id(character1Id)
				.currentHp(boardState2character1CurrentHp).party(character1Party).build();

		boardState2character2 = PlayerBattleCharacterState.builder().id(character2Id)
				.currentHp(boardState2character2CurrentHp).party(character2Party).build();

		boardState2character3 = PlayerBattleCharacterState.builder().id(character3Id)
				.currentHp(boardState2character3CurrentHp).party(character3Party).build();

		boardState2CharacterStates = new LinkedHashMap<>();
		boardState2CharacterStates.put(character1Id, boardState2character1);
		boardState2CharacterStates.put(character2Id, boardState2character2);
		boardState2CharacterStates.put(character3Id, boardState2character3);

		boardState2 = BoardState.builder().characterStates(boardState2CharacterStates).build();

		boardState3character1 = PlayerBattleCharacterState.builder().id(character1Id)
				.currentHp(boardState3character1CurrentHp).party(character1Party).build();

		boardState3character2 = PlayerBattleCharacterState.builder().id(character2Id)
				.currentHp(boardState3character2CurrentHp).party(character2Party).build();

		boardState3character3 = PlayerBattleCharacterState.builder().id(character3Id)
				.currentHp(boardState3character3CurrentHp).party(character3Party).build();

		boardState3CharacterStates = new LinkedHashMap<>();
		boardState3CharacterStates.put(character1Id, boardState3character1);
		boardState3CharacterStates.put(character2Id, boardState3character2);
		boardState3CharacterStates.put(character3Id, boardState3character3);

		boardState3 = BoardState.builder().characterStates(boardState3CharacterStates).build();

		round1 = Round.builder().initialBoardState(boardState1).finalBoardState(boardState2).build();
		round2 = Round.builder().initialBoardState(boardState2).finalBoardState(boardState3).build();

		rounds = Arrays.asList(round1, round2);

		expectedBattle = Battle.builder().initialBoardState(boardState1).finalBoardState(boardState3).rounds(rounds)
				.winningParty(Optional.of(Party.PLAYER)).isBattleComplete(true).build();

		when(boardStateProviderMock.getInitialBoardState(any(), any())).thenReturn(boardState1);
		when(roundProviderMock.getRound(any())).thenReturn(round1).thenReturn(round2);

		unitUnderTest = new BattleProvider(boardStateProviderMock, roundProviderMock);
	}

	@Test
	public void testGetBattle() {
		Battle result = unitUnderTest.getBattle(initialCharacterStates, ROUND_COUNT_LIMIT, boardMock);

		assertEquals(expectedBattle, result);
		verify(boardStateProviderMock, times(1)).getInitialBoardState(eq(initialCharacterStates), eq(boardMock));
		verify(roundProviderMock, times(2)).getRound(any());
		verify(roundProviderMock, times(1)).getRound(eq(boardState1));
		verify(roundProviderMock, times(1)).getRound(eq(boardState2));
		verify(roundProviderMock, never()).getRound(eq(boardState3));
	}
}
