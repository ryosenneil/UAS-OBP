package com.example.demospringboot.service;

import com.example.demospringboot.entity.Admin;
import com.example.demospringboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin validateLogin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }
}
