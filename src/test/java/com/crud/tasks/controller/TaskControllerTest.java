package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldFetchEmptyTaskList() throws Exception {
        //Given
        List<TaskDto> taskList = new ArrayList<>();
        when(dbService.getAllTasks()).thenReturn(new ArrayList<>());
        when(taskMapper.mapToTaskDtoList(ArgumentMatchers.any())).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTaskList() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "title 1", "content 1"));
        taskList.add(new Task(2L, "title 2", "content 2"));

        List<TaskDto> mappedListToListTaskDto = new ArrayList<>();
        mappedListToListTaskDto.add(new TaskDto(1L, "title 1", "content 1"));
        mappedListToListTaskDto.add(new TaskDto(2L, "title 2", "content 2"));

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(ArgumentMatchers.any())).thenReturn(mappedListToListTaskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title 1")))
                .andExpect(jsonPath("$[0].content", is("content 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("title 2")))
                .andExpect(jsonPath("$[1].content", is("content 2")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(1L, "title 1", "content 1");
        TaskDto mappedTask = new TaskDto(1L, "title 1", "content 1");
        when(dbService.getTask(task.getId())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any())).thenReturn(mappedTask);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title 1")))
                .andExpect(jsonPath("$.content", is("content 1")));

    }


    @Test
    public void shouldThrowTaskNotFoundException() {
        //Given
        when(dbService.getTask(anyLong())).thenReturn(Optional.empty());
        //When & Then
        try {
            mockMvc.perform(get("/v1/task/getTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("taskId", "1"));
        } catch (Exception e) {
            assertEquals(TaskNotFoundException.class, e.getCause().getClass());
        }
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title 1", "content 1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "title 1", "content 1");
        TaskDto taskDto = new TaskDto(1L, "title 1", "content 1");

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title 1")))
                .andExpect(jsonPath("$.content", is("content 1")));
    }
}