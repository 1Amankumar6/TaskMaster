package com.curd.basicProject.repository;

import com.curd.basicProject.entites.Priority;
import com.curd.basicProject.entites.Task;
import com.curd.basicProject.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByPriority(Priority priority);
    List<Task> findByUser(User user);
}
