//package com.curd.basicProject.controller;
//
//import com.curd.basicProject.dto.TaskUpdateDTO;
//import com.curd.basicProject.entites.Priority;
//import com.curd.basicProject.entites.Status;
//import com.curd.basicProject.entites.Task;
//import com.curd.basicProject.service.TaskService;
//import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//    private final TaskService service;
//
//    public TaskController(TaskService service) {
//        this.service = service;
//    }
//
//    // Single GET method for all tasks or filtered by priority
//    // Current: /api/tasks/priority/HIGH → path variable
//    // Desired: /api/tasks?priority=HIGH → query parameter → change controller to @RequestParam
//    @GetMapping
//    public List<Task> getAllTasks(@RequestParam(required = false) String priority) {
//        if (priority == null || priority.isBlank()) {
//            return service.getAllTasks(); // no filter
//        }
//
//        com.curd.basicProject.entites.Priority p;
//        try {
//            p = com.curd.basicProject.entites.Priority.valueOf(priority.trim().toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("Invalid priority: " + priority);
//        }
//        return service.getTaskByPriority(p);
//    }
//
//    @PostMapping
//    public Task addTask(@Valid @RequestBody Task task) {
//        return service.addTask(task);
//    }
//
//    @GetMapping("/{id}")
//    public Task getTaskById(@PathVariable long id) {
//        return service.getTaskById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Task updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
//        return service.updateTask(id, updatedTask);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteTask(@PathVariable long id) {
//        service.deleteTaskById(id);
//        return "Deleted successfully";
//    }
//
//    @PatchMapping("/{id}")
//    public Task updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO dto){
//        Task task = service.getTaskById(id);
//        if(dto.getDescription() != null)
//            task.setDescription(dto.getDescription());
//        if(dto.getPriority() != null)
//            task.setPriority(dto.getPriority());
//        return service.saveTask(task);
//    }
//
//    // Partial update
////    @PatchMapping("/{id}")
////    public Task updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO dto) {
////        return service.updateTask(id, dto);
////    }
//
//    // Filter tasks
//    @GetMapping
//    public List<Task> getTasks(
//            @RequestParam(required = false) Priority priority,
//            @RequestParam(required = false) Long userId,
//            @RequestParam(required = false) Status status
//    ) {
//        return service.getTasksByFilters(priority, userId, status);
//    }
//}

package com.curd.basicProject.controller;

import com.curd.basicProject.dto.TaskUpdateDTO;
import com.curd.basicProject.entites.Priority;
import com.curd.basicProject.entites.Status;
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

    // GET /tasks → all tasks or filtered by query params
    @GetMapping
    public List<Task> getTasks(
            @RequestParam(required = false) String priority, // optional string
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Status status
    ) {
        Priority p = null;
        if (priority != null && !priority.isBlank()) {
            try {
                p = Priority.valueOf(priority.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid priority: " + priority);
            }
        }
        return service.getTasksByFilters(p, userId, status);
    }

    // GET /tasks/{id} → get task by id
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable long id) {
        return service.getTaskById(id);
    }

    // POST /tasks → add new task
    @PostMapping
    public Task addTask(@Valid @RequestBody Task task) {
        return service.addTask(task);
    }

    // PUT /tasks/{id} → update entire task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
        return service.updateTask(id, updatedTask);
    }

    // PATCH /tasks/{id} → partial update (priority / description)
    @PatchMapping("/{id}")
    public Task partialUpdateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO dto) {
        Task task = service.getTaskById(id);
        if (dto.getDescription() != null)
            task.setDescription(dto.getDescription());
        if (dto.getPriority() != null)
            task.setPriority(dto.getPriority());
        return service.saveTask(task);
    }

    // DELETE /tasks/{id} → delete task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable long id) {
        service.deleteTaskById(id);
        return "Deleted successfully";
    }
}
