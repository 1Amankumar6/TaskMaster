package com.curd.basicProject.service;

import com.curd.basicProject.entites.Task;
import com.curd.basicProject.entites.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    List<Task> getTasksByUserId(Long id);
}
