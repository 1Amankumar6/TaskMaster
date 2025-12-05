package com.curd.basicProject.controller;

import com.curd.basicProject.entites.Task;
import com.curd.basicProject.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // Single GET method for all tasks or filtered by priority
    // Current: /api/tasks/priority/HIGH → path variable
    // Desired: /api/tasks?priority=HIGH → query parameter → change controller to @RequestParam
    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) String priority) {
        if (priority == null || priority.isBlank()) {
            return service.getAllTasks(); // no filter
        }

        com.curd.basicProject.entites.Priority p;
        try {
            p = com.curd.basicProject.entites.Priority.valueOf(priority.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid priority: " + priority);
        }
        return service.getTaskByPriority(p);
    }

    @PostMapping
    public Task addTask(@Valid @RequestBody Task task) {
        return service.addTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable long id) {
        return service.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
        return service.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable long id) {
        service.deleteTaskById(id);
        return "Deleted successfully";
    }
}