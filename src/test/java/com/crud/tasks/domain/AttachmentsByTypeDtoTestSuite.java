package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AttachmentsByTypeDtoTestSuite {

    @Test
    public void testGetTrello() {
        //Given
        TrelloDto trello = new TrelloDto(1, 1);
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto(trello);
        //When
        TrelloDto trelloResult = attachmentsByTypeDto.getTrello();
        //Then
        assertEquals(1, trelloResult.getBoard());
        assertEquals(1, trelloResult.getCard());
    }

    @Test
    public void testNoArgsConstructor() {
        //Given
        //When
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto();
        //Then
        assertNull(attachmentsByTypeDto.getTrello());
    }
}