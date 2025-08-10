package com.devtiro.controllers;

import com.devtiro.domain.dto.TaskDto;
import com.devtiro.mapper.TaskMapper;
import com.devtiro.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
@Tag(name = "Tasks", description = "APIs for managing tasks within a task list")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    @Operation(summary = "Get all tasks in a task list", description = "Retrieves a list of all tasks in the specified task list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class)))
    })
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable("task_list_id") UUID taskListId) {
        List<TaskDto> tasks = taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();

        return ResponseEntity.ok(tasks);
    }
}
