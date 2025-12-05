package com.curd.basicProject.service;

import com.curd.basicProject.entites.Priority;
import com.curd.basicProject.entites.Task;
import java.util.List;

public interface TaskService {
    Task addTask(Task task);
    List<Task> getAllTasks();
    List<Task> getTaskByPriority(Priority priority);
    Task getTaskById(long id);
    Task updateTask(long id, Task updatedTask);
    void deleteTaskById(long id);
}

