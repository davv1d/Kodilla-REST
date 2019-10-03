package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list 2", true);
        List<TrelloListDto> trelloLists1 = Arrays.asList(trelloListDto1, trelloListDto2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board 1", trelloLists1);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "list 3", false);
        List<TrelloListDto> trelloLists2 = Collections.singletonList(trelloListDto3);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "board 2", trelloLists2);
        List<TrelloBoardDto> trelloBoardsDto = Arrays.asList(trelloBoardDto1, trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoardsResult = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(2, trelloBoardsResult.size());
        assertEquals("1", trelloBoardsResult.get(0).getId());
        assertEquals("2", trelloBoardsResult.get(1).getId());
        assertEquals("board 1", trelloBoardsResult.get(0).getName());
        assertEquals("board 2", trelloBoardsResult.get(1).getName());
        assertEquals(1, trelloBoardsResult.get(1).getLists().size());
        assertEquals(2, trelloBoardsResult.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "list 2", true);
        List<TrelloList> trelloLists1 = Arrays.asList(trelloList1, trelloList2);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board 1", trelloLists1);
        TrelloList trelloList3 = new TrelloList("3", "list 3", false);
        List<TrelloList> trelloLists2 = Collections.singletonList(trelloList3);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board 2", trelloLists2);
        List<TrelloBoard> trelloBoards = Arrays.asList(trelloBoard1, trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardsDtoResult = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(2, trelloBoardsDtoResult.size());
        assertEquals("1", trelloBoardsDtoResult.get(0).getId());
        assertEquals("2", trelloBoardsDtoResult.get(1).getId());
        assertEquals("board 1", trelloBoardsDtoResult.get(0).getName());
        assertEquals("board 2", trelloBoardsDtoResult.get(1).getName());
        assertEquals(1, trelloBoardsDtoResult.get(1).getLists().size());
        assertEquals(2, trelloBoardsDtoResult.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list 2", true);
        List<TrelloListDto> trelloListsDto = Arrays.asList(trelloListDto1, trelloListDto2);
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(2, trelloList.size());
        assertEquals("1", trelloList.get(0).getId());
        assertEquals("2", trelloList.get(1).getId());
        assertEquals("list 1", trelloList.get(0).getName());
        assertEquals("list 2", trelloList.get(1).getName());
        assertFalse(trelloList.get(0).isClosed());
        assertTrue(trelloList.get(1).isClosed());
    }


    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "list 2", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(2, trelloListsDto.size());
        assertEquals("1", trelloListsDto.get(0).getId());
        assertEquals("2", trelloListsDto.get(1).getId());
        assertEquals("list 1", trelloListsDto.get(0).getName());
        assertEquals("list 2", trelloListsDto.get(1).getName());
        assertFalse(trelloListsDto.get(0).isClosed());
        assertTrue(trelloListsDto.get(1).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name 1", "des 1", "pos 1", "listId 1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name 1", "des 1", "pos 1", "listId 1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }
}