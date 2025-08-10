package com.devtiro.mapper.impl;

import com.devtiro.domain.dto.TaskDto;
import com.devtiro.domain.entities.Task;
import com.devtiro.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null, // Assuming the Task entity has a field for tasks that is not set here, adjust as necessary
                null, // Assuming the Task entity has a field for created that is not set here,
                null  // Assuming the Task entity has a field for updated that is not set here,
        );

    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
