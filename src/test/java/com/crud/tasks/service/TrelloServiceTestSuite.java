package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testCreatedTrelloCardReturnNull() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "1");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);
        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createdTrelloCard(trelloCardDto);
        //Then
        assertNull(createdTrelloCardDto);
        verify(emailService, times(0)).send(any(Mail.class));
    }

    @Test
    public void testCreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "shortUrl", null);
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        trelloService.createdTrelloCard(trelloCardDto);
        //Then
        verify(adminConfig, times(1)).getAdminMail();
        verify(emailService, times(1)).send(any(Mail.class));
    }

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, trelloBoardDtos.size());
    }
}