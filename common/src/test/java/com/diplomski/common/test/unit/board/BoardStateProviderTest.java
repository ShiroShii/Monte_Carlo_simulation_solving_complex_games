package com.diplomski.common.test.unit.board;

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

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.BoardStateProvider;
import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.dice.IDice;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.turn.ITurnProvider;
import com.diplomski.common.turn.ITurnProviderFactory;

public class BoardStateProviderTest {
	private IDiceFactory diceProviderFactory = mock(IDiceFactory.class);
	private IDice diceMock = mock(IDice.class);
	private ITurnProviderFactory turnProviderFactoryMock = mock(ITurnProviderFactory.class);

	private String character1Id = "Player 1";
	private String character2Id = "Player 2";
	private String character3Id = "Enemy 1";
	private Party character1Party = Party.PLAYER;
	private Party character2Party = Party.PLAYER;
	private Party character3Party = Party.ENEMY;
	private int character1dexterity = -1;
	private int character2dexterity = 0;
	private int character3dexterity = 1;
	private int character1InitiativeRoll = 15;
	private int character2InitiativeRoll = 10;
	private int character3InitiativeRoll = 20;
	private int character1CurrentHp = 5;
	private int character2CurrentHp = 6;
	private int character3CurrentHp = 7;
	private int character1ExhaustionLevel = 0;
	private int character2ExhaustionLevel = 1;
	private int character3ExhaustionLevel = 2;
	private int character1MaxHp = 10;
	private int character2MaxHp = 11;
	private int character3MaxHp = 12;

	private BattleCharacterState character1;
	private BattleCharacterState character2;
	private BattleCharacterState character3;

	private CharacterState character1InitialState;
	private CharacterState character2InitialState;
	private CharacterState character3InitialState;

	private ITurnProvider character1TurnProviderMock = mock(ITurnProvider.class);
	private ITurnProvider character2TurnProviderMock = mock(ITurnProvider.class);
	private ITurnProvider character3TurnProviderMock = mock(ITurnProvider.class);

	private List<CharacterState> initialCharacterStateList;
	private LinkedHashMap<String, BattleCharacterState> expectedCharacterStateList;
	private BoardState expectedBoardState;

	private BoardStateProvider unitUnderTest;

	@Before
	public void setup() {
		character1InitialState = CharacterState.builder().id(character1Id).currentHp(character1CurrentHp)
				.maxHp(character1MaxHp).dexterity(character1dexterity).exhaustionLevel(character1ExhaustionLevel)
				.party(character1Party).build();

		character2InitialState = CharacterState.builder().id(character2Id).currentHp(character2CurrentHp)
				.maxHp(character2MaxHp).dexterity(character2dexterity).exhaustionLevel(character2ExhaustionLevel)
				.party(character2Party).build();

		character3InitialState = CharacterState.builder().id(character3Id).currentHp(character3CurrentHp)
				.maxHp(character3MaxHp).dexterity(character3dexterity).exhaustionLevel(character3ExhaustionLevel)
				.party(character3Party).build();

		character1 = BattleCharacterState.builder().id(character1Id).currentHp(character1CurrentHp)
				.maxHp(character1MaxHp).dexterity(character1dexterity).exhaustionLevel(character1ExhaustionLevel)
				.party(character1Party).turnProvider(character1TurnProviderMock).build();

		character2 = BattleCharacterState.builder().id(character2Id).currentHp(character2CurrentHp)
				.maxHp(character2MaxHp).dexterity(character2dexterity).exhaustionLevel(character2ExhaustionLevel)
				.party(character2Party).turnProvider(character2TurnProviderMock).build();

		character3 = BattleCharacterState.builder().id(character3Id).currentHp(character3CurrentHp)
				.maxHp(character3MaxHp).dexterity(character3dexterity).exhaustionLevel(character3ExhaustionLevel)
				.party(character3Party).turnProvider(character3TurnProviderMock).build();

		initialCharacterStateList = Arrays.asList(character1InitialState, character2InitialState,
				character3InitialState);

		expectedCharacterStateList = new LinkedHashMap<>();
		expectedCharacterStateList.put(character3Id, character3);
		expectedCharacterStateList.put(character1Id, character1);
		expectedCharacterStateList.put(character2Id, character2);

		expectedBoardState = BoardState.builder().characterStates(expectedCharacterStateList).build();

		when(diceMock.getRoll()).thenReturn(character1InitiativeRoll).thenReturn(character2InitiativeRoll)
				.thenReturn(character3InitiativeRoll);

		when(diceProviderFactory.getD20()).thenReturn(diceMock);

		when(turnProviderFactoryMock.getTurnProvider(eq(character1Id), any())).thenReturn(character1TurnProviderMock);
		when(turnProviderFactoryMock.getTurnProvider(eq(character2Id), any())).thenReturn(character2TurnProviderMock);
		when(turnProviderFactoryMock.getTurnProvider(eq(character3Id), any())).thenReturn(character3TurnProviderMock);

		unitUnderTest = new BoardStateProvider(turnProviderFactoryMock, diceProviderFactory);
	}

	@Test
	public void getInitialBoardState() {
		BoardState result = unitUnderTest.getInitialBoardState(initialCharacterStateList);

		verify(diceMock, times(3)).getRoll();
		assertEquals(expectedBoardState, result);

		// Verify order
		Iterator<String> expectedIterator = expectedBoardState.getCharacterStates().keySet().iterator();
		Iterator<String> resultIterator = result.getCharacterStates().keySet().iterator();

		while (expectedIterator.hasNext() && resultIterator.hasNext()) {
			assertEquals(expectedIterator.next(), resultIterator.next());
		}
	}

}
