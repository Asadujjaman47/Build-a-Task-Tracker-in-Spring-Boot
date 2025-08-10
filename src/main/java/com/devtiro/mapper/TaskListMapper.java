package com.devtiro.mapper;

import com.devtiro.domain.dto.TaskListDto;
import com.devtiro.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
