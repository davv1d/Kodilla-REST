package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloDtoTestSuite {

    @Test
    public void testGetBoard() {
        //Given
        TrelloDto trelloDto = new TrelloDto(1, 2);
        //When
        int board = trelloDto.getBoard();
        //Then
        assertEquals(1, board);
    }

    @Test
    public void testGetCard() {
        //Given
        TrelloDto trelloDto = new TrelloDto(1, 2);
        //When
        int card = trelloDto.getCard();
        //Then
        assertEquals(2, card);
    }

    @Test
    public void testNoArgsConstructor() {
        //Given
        //When
        TrelloDto trelloDto = new TrelloDto();
        //Then
        assertEquals(0, trelloDto.getCard());
        assertEquals(0, trelloDto.getBoard());
    }
}