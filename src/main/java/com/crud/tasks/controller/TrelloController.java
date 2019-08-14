package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {
        Optional<List<TrelloBoardDto>> optionalTrelloBoards = trelloClient.getTrelloBoards();
        optionalTrelloBoards.ifPresent(this::writesBoardsToConsoleWhichHaveIdAndKodillaInName);
        optionalTrelloBoards.ifPresent(trelloBoards ->trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " " + trelloBoardDto.getId());
            System.out.println("This board contains lists:");
            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " " + trelloList.isClosed()));
        }));
    }

    private void writesBoardsToConsoleWhichHaveIdAndKodillaInName(List<TrelloBoardDto> trelloBoards) {
        trelloBoards.stream()
                .filter(trelloBoardDto ->
                        !trelloBoardDto.getId().isEmpty() &&
                                !trelloBoardDto.getName().isEmpty() &&
                                trelloBoardDto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
