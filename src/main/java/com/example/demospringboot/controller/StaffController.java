package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Staff;
import com.example.demospringboot.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    private boolean isNotLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("loggedInStaff") == null;
    }

    @GetMapping({ "", "/" })
    public String staffPage(Model model, HttpServletRequest request) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

        model.addAttribute("staffList", staffService.getAllStaff());
        model.addAttribute("staffRec", new Staff());

        return "staff"; 
    }


    @GetMapping("/{id:\\d+}")
    public String getStaffById(@PathVariable Long id,
            Model model,
            HttpServletRequest request) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

        model.addAttribute("staffList", staffService.getAllStaff());
        model.addAttribute("staffRec", staffService.getStaffById(id));

        return "staff";
    }


    @PostMapping(value = "/submit", params = "add")
    public String addStaff(@ModelAttribute Staff staff,
            HttpServletRequest request) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

        staffService.addStaff(staff);
        return "redirect:/staff";
    }


    @PostMapping(value = "/submit/{id}", params = "edit")
    public String editStaff(@PathVariable Long id,
            @ModelAttribute Staff staff,
            HttpServletRequest request) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

        staffService.updateStaff(id, staff);
        return "redirect:/staff";
    }

    @PostMapping(value = "/submit/{id}", params = "delete")
    public String deleteStaff(@PathVariable Long id,
            HttpServletRequest request) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

        staffService.deleteStaff(id);
        return "redirect:/staff";
    }
}
