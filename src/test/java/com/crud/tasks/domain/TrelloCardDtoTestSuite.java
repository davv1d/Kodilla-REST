package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloCardDtoTestSuite {

    @Test
    public void getName() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "1");
        //When
        String name = trelloCardDto.getName();
        //Then
        assertEquals("name", name);
    }

    @Test
    public void getDescription() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "1");
        //When
        String description = trelloCardDto.getDescription();
        //Then
        assertEquals("desc", description);
    }

    @Test
    public void getPos() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "1");
        //When
        String pos = trelloCardDto.getPos();
        //Then
        assertEquals("pos", pos);
    }

    @Test
    public void getListId() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "1");
        //When
        String listId = trelloCardDto.getListId();
        //Then
        assertEquals("1", listId);
    }

    @Test
    public void noArgsConstructor() {
        //Given
        //When
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        //Then
        assertNull(trelloCardDto.getDescription());
        assertNull(trelloCardDto.getListId());
        assertNull(trelloCardDto.getName());
        assertNull(trelloCardDto.getPos());
    }
}