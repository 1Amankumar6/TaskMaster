package com.curd.basicProject.service;

import com.curd.basicProject.dto.TaskUpdateDTO;
import com.curd.basicProject.entites.Priority;
import com.curd.basicProject.entites.Task;
import com.curd.basicProject.entites.Status;
import java.util.List;

public interface TaskService {
    Task addTask(Task task);
    List<Task> getAllTasks();
    List<Task> getTaskByPriority(Priority priority);
    Task getTaskById(long id);
    Task updateTask(long id, Task updatedTask);
    void deleteTaskById(long id);
    Task saveTask(Task task);
    Task updateTask(Long id, TaskUpdateDTO dto);
    List<Task> getTasksByFilters(Priority priority, Long userId, Status status);
}

