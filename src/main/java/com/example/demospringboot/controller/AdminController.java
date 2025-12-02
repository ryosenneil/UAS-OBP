package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Admin;
import com.example.demospringboot.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {

        if (request.getSession().getAttribute("loggedInStaff") != null) {
            return "redirect:/staff";
        }

        return "staffLogin";
    }

    @PostMapping("/validatelogin")
public String validateLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

    Admin admin = adminService.validateLogin(username, password);

    if (admin != null) {
        session.setAttribute("loggedInStaff", admin);
        return "redirect:/staff";
    } else {
        model.addAttribute("error", "Username atau password salah!");
        return "staffLogin";
    }
}


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/auth/login";
    }
}
