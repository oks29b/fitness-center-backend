package com.example.servingwebcontent.model.repository;

import com.example.servingwebcontent.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
