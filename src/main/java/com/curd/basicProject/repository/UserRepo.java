package com.curd.basicProject.repository;

import com.curd.basicProject.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
