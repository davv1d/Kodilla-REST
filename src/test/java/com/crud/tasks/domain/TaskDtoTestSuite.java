package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskDtoTestSuite {

    @Test
    public void getId() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        long id = taskDto.getId();
        //Then
        assertEquals(1L, id);
    }

    @Test
    public void getTitle() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        String title = taskDto.getTitle();
        //Then
        assertEquals("title", title);
    }

    @Test
    public void getContent() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        String content = taskDto.getContent();
        //Then
        assertEquals("content", content);
    }

    @Test
    public void noArgsConstructor() {
        //Given
        //When
        TaskDto taskDto = new TaskDto();
        //Then
        assertEquals(0, taskDto.getId());
        assertNull(taskDto.getTitle());
        assertNull(taskDto.getContent());
    }
}