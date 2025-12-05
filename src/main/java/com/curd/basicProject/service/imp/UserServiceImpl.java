package com.curd.basicProject.service.imp;

import com.curd.basicProject.entites.Task;
import com.curd.basicProject.entites.User;
import com.curd.basicProject.repository.TaskRepo;
import com.curd.basicProject.repository.UserRepo;
import com.curd.basicProject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    public UserServiceImpl(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not present with the id: " + id));
    }

    @Override
    public List<Task> getTasksByUserId(Long id) {
        User user = getUserById(id); // will throw if not found
        return taskRepo.findByUser(user);
    }
}
