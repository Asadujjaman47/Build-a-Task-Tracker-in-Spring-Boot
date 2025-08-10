package com.devtiro.mapper;

import com.devtiro.domain.dto.TaskDto;
import com.devtiro.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
