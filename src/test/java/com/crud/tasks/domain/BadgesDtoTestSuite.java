package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BadgesDtoTestSuite {

    @Test
    public void testGetVotes() {
        //Given
        BadgesDto badgesDto = new BadgesDto(1, null);
        //When
        int votes = badgesDto.getVotes();
        //Then
        assertEquals(1, votes);
    }

    @Test
    public void testGetAttachmentsByTypeDto() {
        //Given
        TrelloDto trello = new TrelloDto(1, 1);
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto(trello);
        BadgesDto badgesDto = new BadgesDto(1, attachmentsByTypeDto);
        //When
        AttachmentsByTypeDto attachmentsByTypeDtoResult = badgesDto.getAttachmentsByType();
        //Then
        assertEquals(attachmentsByTypeDto, attachmentsByTypeDtoResult);
    }
}