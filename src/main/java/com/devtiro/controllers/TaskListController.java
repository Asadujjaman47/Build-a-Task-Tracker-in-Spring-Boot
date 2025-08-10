package com.devtiro.controllers;

import com.devtiro.domain.dto.TaskListDto;
import com.devtiro.domain.entities.TaskList;
import com.devtiro.mapper.TaskListMapper;
import com.devtiro.services.TaskListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists")
@Tag(name = "Task Lists", description = "APIs for managing task lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    @Operation(summary = "Get all task lists", description = "Retrieves a list of all available task lists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved task lists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskListDto.class)))
    })
    public ResponseEntity<List<TaskListDto>> listTaskLists() {
        List<TaskListDto> taskLists = taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
        return ResponseEntity.ok(taskLists);
    }

    @PostMapping
    @Operation(summary = "Create a new task list", description = "Creates a new task list with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task list created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskListDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskLists(
                taskListMapper.fromDto(taskListDto)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(taskListMapper.toDto(createdTaskList));
    }

}
