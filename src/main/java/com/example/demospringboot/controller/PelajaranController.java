package com.example.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.entity.Pelajaran;
import com.example.demospringboot.service.PelajaranService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pelajaran")
public class PelajaranController {

    @Autowired
    private PelajaranService pelajaranService;

    private boolean notLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute("loggedInStaff") == null;
    }

    @GetMapping({ "", "/" })
    public String pelajaranPage(Model model, HttpServletRequest request) {
        if (notLoggedIn(request)) return "redirect:/auth/login";

        model.addAttribute("pelajaranList", pelajaranService.getAllPelajaran());
        model.addAttribute("pelajaran", new Pelajaran());
        return "pelajaran";
    }

    @GetMapping("/{id}")
    public String getPelajaranById(@PathVariable Long id, Model model, HttpServletRequest request) {
        if (notLoggedIn(request)) return "redirect:/auth/login";

        model.addAttribute("pelajaranList", pelajaranService.getAllPelajaran());
        model.addAttribute("pelajaran", pelajaranService.getPelajaranById(id));
        return "pelajaran";
    }

    @PostMapping("/submit")
    public String submitPelajaran(
            @ModelAttribute Pelajaran pelajaran,
            @RequestParam(required = false) String add,
            @RequestParam(required = false) String edit,
            @RequestParam(required = false) String delete,
            HttpServletRequest request) {

        if (notLoggedIn(request)) return "redirect:/auth/login";

        Long id = pelajaran.getId();

        if (add != null) {
            pelajaran.setId(null);
            pelajaranService.addPelajaran(pelajaran);
        } else if (edit != null && id != null) {
            pelajaranService.updatePelajaran(id, pelajaran);
        } else if (delete != null && id != null) {
            pelajaranService.deletePelajaran(id);
        }

        return "redirect:/pelajaran";
    }
}
