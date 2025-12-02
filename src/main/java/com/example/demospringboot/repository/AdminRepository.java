package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsernameAndPassword(String username, String password);
}
