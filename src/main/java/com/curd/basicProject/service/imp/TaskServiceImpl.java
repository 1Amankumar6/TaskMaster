package com.curd.basicProject.service.imp;

import com.curd.basicProject.entites.Priority;
import com.curd.basicProject.entites.Task;
import com.curd.basicProject.repository.TaskRepo;
import com.curd.basicProject.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskServiceImpl implements TaskService {

    private final TaskRepo repo;

    public TaskServiceImpl(TaskRepo repo) {
        this.repo = repo;
    }

    @Override
    public Task addTask(Task task) {
        return repo.save(task);
    }

    @Override
    public Task getTaskById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    @Override
    public Task updateTask(long id, Task updatedTask) {
        Task existing = getTaskById(id);
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setPriority(updatedTask.getPriority());
        return repo.save(existing);
    }

    @Override
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    @Override
    public void deleteTaskById(long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Task> getTaskByPriority(Priority priority) {
        return repo.findByPriority(priority);
    }
}
