package com.curd.basicProject.controller;

import com.curd.basicProject.entites.Task;
import com.curd.basicProject.entites.User;
import com.curd.basicProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service ;

    UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saved = service.addUser(user);
        return ResponseEntity.ok(saved);
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // Get User By Id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByUser(@PathVariable Long id) {
        return service.getTasksByUserId(id);
    }
}
