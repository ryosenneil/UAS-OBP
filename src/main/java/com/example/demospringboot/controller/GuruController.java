package com.example.demospringboot.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demospringboot.entity.Guru;
import com.example.demospringboot.service.GuruService;
import com.example.demospringboot.service.PelajaranService;

@Controller
@RequestMapping("/guru")
public class GuruController {

    @Autowired
    private GuruService guruService;

    @Autowired
    private PelajaranService pelajaranService;

    private boolean notLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute("loggedInStaff") == null;
    }

    @GetMapping({"", "/"})
    public String guruPage(Model model, HttpServletRequest request) {

        if (notLoggedIn(request)) return "redirect:/auth/login";

        model.addAttribute("listGuru", guruService.getAllGuru());
        model.addAttribute("listPelajaran", pelajaranService.getAllPelajaran());
        model.addAttribute("guruRec", new Guru());
        return "guru";
    }

    @GetMapping("/{id}")
    public String getGuruById(@PathVariable Long id, Model model, HttpServletRequest request) {

        if (notLoggedIn(request)) return "redirect:/auth/login";

        model.addAttribute("listGuru", guruService.getAllGuru());
        model.addAttribute("listPelajaran", pelajaranService.getAllPelajaran());
        model.addAttribute("guruRec", guruService.getGuruById(id));
        return "guru";
    }

    @PostMapping(value = "/submit", params = "add")
    public String addGuru(
            HttpServletRequest request,
            @ModelAttribute Guru guru,
            @RequestParam(value = "pelajaranIds", required = false) List<Long> pelajaranIds
    ) {
        if (notLoggedIn(request)) return "redirect:/auth/login";

        guruService.addGuru(guru, pelajaranIds);
        return "redirect:/guru";
    }

    @PostMapping(value = "/submit/{id}", params = "edit")
    public String editGuru(
            @PathVariable Long id,
            HttpServletRequest request,
            @ModelAttribute Guru guru,
            @RequestParam(value = "pelajaranIds", required = false) List<Long> pelajaranIds
    ) {
        if (notLoggedIn(request)) return "redirect:/auth/login";

        guruService.updateGuru(id, guru, pelajaranIds);
        return "redirect:/guru";
    }

    @PostMapping(value = "/submit/{id}", params = "delete")
    public String deleteGuru(@PathVariable Long id, HttpServletRequest request) {

        if (notLoggedIn(request)) return "redirect:/auth/login";

        guruService.deleteGuru(id);
        return "redirect:/guru";
    }
}
