package com.devtiro.domain.dto;

import com.devtiro.domain.entities.TaskPriority;
import com.devtiro.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
