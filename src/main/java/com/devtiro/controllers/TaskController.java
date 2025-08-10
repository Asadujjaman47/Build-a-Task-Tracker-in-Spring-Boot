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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
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

    @PostMapping
    @Operation(summary = "Create a new task", description = "Creates a new task in the specified task list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<TaskDto> createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskMapper.toDto(
                taskService.createTask(taskListId, taskMapper.fromDto(taskDto))
        );
        return ResponseEntity.status(201).body(createdTask);
    }

    @GetMapping("/{task_id}")
    @Operation(summary = "Get a specific task", description = "Retrieves details of a specific task in the task list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
//    public Optional<TaskDto> getTask(
//            @PathVariable("task_list_id") UUID taskListId,
//            @PathVariable("task_id") UUID taskId) {
//        return taskService.getTask(taskListId, taskId)
//                .map(taskMapper::toDto);
//    }
    public ResponseEntity<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {

        return taskService.getTask(taskListId, taskId)
                .map(taskMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{task_id}")
    @Operation(summary = "Update a specific task", description = "Updates details of a specific task in the task list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskMapper.toDto(
                taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto))
        );
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{task_id}")
    @Operation(summary = "Delete a specific task", description = "Deletes a specific task from the task list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
        return ResponseEntity.noContent().build();
    }

}
