package com.curd.basicProject.service.imp;

import com.curd.basicProject.dto.TaskUpdateDTO;
import com.curd.basicProject.entites.Priority;
import com.curd.basicProject.entites.Status;
import com.curd.basicProject.entites.Task;
import com.curd.basicProject.repository.TaskRepo;
import com.curd.basicProject.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Override
    public Task saveTask(Task task) {
        return repo.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskUpdateDTO dto) {
        Task task = getTaskById(id);

        if (StringUtils.hasText(dto.getDescription())) {
            task.setDescription(dto.getDescription());
        }
        if (dto.getPriority() != null) {
            task.setPriority(dto.getPriority());
        }

        return repo.save(task);
    }

    @Override
    public List<Task> getTasksByFilters(Priority priority, Long userId, Status status) {
        List<Task> filtered = new ArrayList<>();

        if (priority != null && userId != null && status != null) {
            return repo.findByPriorityAndUserIdAndStatus(priority, userId, status);
        } else if (priority != null && userId != null) {
            filtered.addAll(repo.findByPriority(priority));
            filtered.removeIf(task -> !task.getUser().getId().equals(userId));
        } else if (priority != null) {
            return repo.findByPriority(priority);
        } else if (userId != null) {
            return repo.findByUserId(userId);
        } else if (status != null) {
            return repo.findByStatus(status);
        } else {
            return repo.findAll();
        }

        return filtered;
    }
}
