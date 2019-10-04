package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloListDtoTestSuite {

    @Test
    public void getId() {
        //Given
        TrelloListDto listDto = new TrelloListDto("1", "name", false);
        //When
        String id = listDto.getId();
        //Then
        assertEquals("1", id);
    }

    @Test
    public void getName() {
        //Given
        TrelloListDto listDto = new TrelloListDto("1", "name", false);
        //When
        String name = listDto.getName();
        //Then
        assertEquals("name", name);
    }

    @Test
    public void isClosed() {
        //Given
        TrelloListDto listDto = new TrelloListDto("1", "name", false);
        //When
        boolean isClosed = listDto.isClosed();
        //Then
        assertFalse(isClosed);
    }

    @Test
    public void noArgsConstructor() {
        //Given
        //When
        TrelloListDto trelloListDto = new TrelloListDto();
        //Then
        assertNull(trelloListDto.getId());
        assertNull(trelloListDto.getName());
        assertFalse(trelloListDto.isClosed());
    }
}