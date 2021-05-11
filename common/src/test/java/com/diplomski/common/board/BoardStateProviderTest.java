package com.diplomski.common.board;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.dice.IDice;

public class BoardStateProviderTest {
	private IDice diceMock = mock(IDice.class);

	private String character1Id = "Player 1";
	private String character2Id = "Player 2";
	private String character3Id = "Enemy 1";
	private Party character1Party = Party.PLAYER;
	private Party character2Party = Party.PLAYER;
	private Party character3Party = Party.ENEMY;
	private int character1Dex = -1;
	private int character2Dex = 0;
	private int character3Dex = 1;
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
	private CharacterState character1;
	private CharacterState character2;
	private CharacterState character3;
	private List<CharacterState> fakeCharacterStateList;
	private List<CharacterState> expectedCharacterStateList;
	private BoardState expectedBoardState;

	private BoardStateProvider unitUnderTest;

	@Before
	public void setup() {
		character1 = CharacterState.builder().id(character1Id).currentHp(character1CurrentHp).maxHp(character1MaxHp)
				.dex(character1Dex).exhaustionLevel(character1ExhaustionLevel).party(character1Party).build();

		character2 = CharacterState.builder().id(character2Id).currentHp(character2CurrentHp).maxHp(character2MaxHp)
				.dex(character2Dex).exhaustionLevel(character2ExhaustionLevel).party(character2Party).build();

		character3 = CharacterState.builder().id(character3Id).currentHp(character3CurrentHp).maxHp(character3MaxHp)
				.dex(character3Dex).exhaustionLevel(character3ExhaustionLevel).party(character3Party).build();

		fakeCharacterStateList = Arrays.asList(character1, character2, character3);
		expectedCharacterStateList = Arrays.asList(character3, character1, character2);
		expectedBoardState = BoardState.builder().characterStates(expectedCharacterStateList).build();

		when(diceMock.getRoll()).thenReturn(character1InitiativeRoll).thenReturn(character2InitiativeRoll)
				.thenReturn(character3InitiativeRoll);

		unitUnderTest = new BoardStateProvider(diceMock);
	}

	@Test
	public void getInitialBoardState() {
		BoardState result = unitUnderTest.getInitialBoardState(fakeCharacterStateList);

		assertEquals(expectedBoardState, result);
		verify(diceMock, times(3)).getRoll();
	}

}
