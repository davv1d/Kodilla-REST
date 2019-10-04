package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateCardTestInName() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test_name", "desc", "pos", "1");
        //When and Then
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void testValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "desc", "pos", "1");
        //When and Then
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void testValidateTrelloBoardsWithoutTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "name_board", trelloLists));

        //When
        List<TrelloBoard> trelloBoardsResult = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(trelloBoards.size(), trelloBoardsResult.size());
    }

    @Test
    public void testValidateTrelloBoardsWithTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        //When
        List<TrelloBoard> trelloBoardsResult = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertTrue(trelloBoardsResult.isEmpty());
    }
}