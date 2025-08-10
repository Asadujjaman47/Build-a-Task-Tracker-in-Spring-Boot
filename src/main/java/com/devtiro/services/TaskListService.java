package com.devtiro.services;

import com.devtiro.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskLists(TaskList taskList);
}
