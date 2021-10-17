package com.diplomski.common.test.unit.board;

import static com.diplomski.common.dice.DiceType.D20;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.BoardStateProvider;
import com.diplomski.common.board.IBoard;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.ICharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.character.PlayerCharacterState;
import com.diplomski.common.dice.IDice;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.turn.ITurnProvider;
import com.diplomski.common.turn.ITurnProviderFactory;

public class BoardStateProviderTest {
	private IDiceFactory diceProviderFactory = mock(IDiceFactory.class);
	private IDice diceMock = mock(IDice.class);
	private ITurnProviderFactory turnProviderFactoryMock = mock(ITurnProviderFactory.class);
	private IBoard boardMock = mock(IBoard.class);

	private UUID character1Id = UUID.fromString("8b521099-18fd-4810-953d-bc4dde0eae14");
	private UUID character2Id = UUID.fromString("3e5aee3a-41e6-402c-a42d-6da8adc7cac9");
	private UUID character3Id = UUID.fromString("5394c3eb-c5b3-4698-a05f-9e4298f58de7");
	private Party character1Party = Party.PLAYER;
	private Party character2Party = Party.PLAYER;
	private Party character3Party = Party.ENEMY;
	private int character1dexterity = 13;
	private int character2dexterity = 14;
	private int character3dexterity = 15;
	private int character1Strength = 11;
	private int character2Strength = 16;
	private int character3Strength = 21;
	private int character1InitiativeRoll = 15;
	private int character2InitiativeRoll = 10;
	private int character3InitiativeRoll = 20;
	private int character1CurrentHp = 6;
	private int character2CurrentHp = 7;
	private int character3CurrentHp = 8;
	private int character1speed = 25;
	private int character2speed = 30;
	private int character3speed = 35;
	
	private PlayerBattleCharacterState character1;
	private PlayerBattleCharacterState character2;
	private PlayerBattleCharacterState character3;

	private PlayerCharacterState character1InitialState;
	private PlayerCharacterState character2InitialState;
	private PlayerCharacterState character3InitialState;

	private ITurnProvider character1TurnProviderMock = mock(ITurnProvider.class);
	private ITurnProvider character2TurnProviderMock = mock(ITurnProvider.class);
	private ITurnProvider character3TurnProviderMock = mock(ITurnProvider.class);

	private List<ICharacterState> initialCharacterStateList;
	private LinkedHashMap<UUID, IBattleCharacterState> expectedCharacterStateList;
	private BoardState expectedBoardState;

	private BoardStateProvider unitUnderTest;

	@Before
	public void setup() {
		character1InitialState = PlayerCharacterState.builder()
				.id(character1Id)
				.currentHp(character1CurrentHp)
				.dexterity(character1dexterity)
				.strength(character1Strength)
				.speed(character1speed)
				.party(character1Party)
				.build();

		character2InitialState = PlayerCharacterState.builder()
				.id(character2Id)
				.currentHp(character2CurrentHp)
				.dexterity(character2dexterity)
				.strength(character2Strength)
				.speed(character2speed)
				.party(character2Party)
				.build();

		character3InitialState = PlayerCharacterState.builder()
				.id(character3Id)
				.currentHp(character3CurrentHp)
				.dexterity(character3dexterity)
				.strength(character3Strength)
				.speed(character3speed)
				.party(character3Party)
				.build();

		character1 = PlayerBattleCharacterState.builder()
				.id(character1Id)
				.currentHp(character1CurrentHp)
				.dexterity(character1dexterity)
				.strength(character1Strength)
				.speed(character1speed)
				.party(character1Party)
				.turnProvider(character1TurnProviderMock)
				.build();

		character2 = PlayerBattleCharacterState.builder()
				.id(character2Id)
				.currentHp(character2CurrentHp)
				.dexterity(character2dexterity)
				.strength(character2Strength)
				.speed(character2speed)
				.party(character2Party)
				.turnProvider(character2TurnProviderMock)
				.build();

		character3 = PlayerBattleCharacterState.builder()
				.id(character3Id)
				.currentHp(character3CurrentHp)
				.dexterity(character3dexterity)
				.strength(character3Strength)
				.speed(character3speed)
				.party(character3Party)
				.turnProvider(character3TurnProviderMock)
				.build();

		initialCharacterStateList = Arrays
				.asList(character1InitialState, character2InitialState, character3InitialState);

		expectedCharacterStateList = new LinkedHashMap<>();
		expectedCharacterStateList.put(character3Id, character3);
		expectedCharacterStateList.put(character1Id, character1);
		expectedCharacterStateList.put(character2Id, character2);

		expectedBoardState = BoardState
				.builder()
				.board(boardMock)
				.characterStates(expectedCharacterStateList)
				.build();

		when(diceMock.getRoll()).thenReturn(character1InitiativeRoll).thenReturn(character2InitiativeRoll)
				.thenReturn(character3InitiativeRoll);

		when(diceProviderFactory.getDice(D20)).thenReturn(diceMock);

		when(turnProviderFactoryMock.getTurnProvider(eq(character1Id), any(), any(), any(), any()))
				.thenReturn(character1TurnProviderMock);
		when(turnProviderFactoryMock.getTurnProvider(eq(character2Id), any(), any(), any(), any()))
				.thenReturn(character2TurnProviderMock);
		when(turnProviderFactoryMock.getTurnProvider(eq(character3Id), any(), any(), any(), any()))
				.thenReturn(character3TurnProviderMock);

		unitUnderTest = new BoardStateProvider(turnProviderFactoryMock, diceProviderFactory);
	}

	@Test
	public void getInitialBoardState() {
		BoardState result = unitUnderTest.getInitialBoardState(initialCharacterStateList, boardMock);

		verify(diceMock, times(3)).getRoll();
		assertEquals(expectedBoardState, result);

		// Verify order
		Iterator<UUID> expectedIterator = expectedBoardState.getCharacterStates().keySet().iterator();
		Iterator<UUID> resultIterator = result.getCharacterStates().keySet().iterator();

		while (expectedIterator.hasNext() && resultIterator.hasNext()) {
			assertEquals(expectedIterator.next(), resultIterator.next());
		}
	}

}
