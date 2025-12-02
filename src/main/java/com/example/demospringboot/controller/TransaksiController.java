package com.example.demospringboot.controller;

import com.example.demospringboot.entity.*;
import com.example.demospringboot.service.TransaksiService;
import com.example.demospringboot.repository.GuruRepository;
import com.example.demospringboot.repository.StaffRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/transaksi")
public class TransaksiController {

    private final TransaksiService transaksiService;
    private final GuruRepository guruRepo;
    private final StaffRepository staffRepo;

    public TransaksiController(
            TransaksiService transaksiService,
            GuruRepository guruRepo,
            StaffRepository staffRepo
    ) {
        this.transaksiService = transaksiService;
        this.guruRepo = guruRepo;
        this.staffRepo = staffRepo;
    }

    private boolean isNotLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("loggedInStaff") == null;
    }

    @GetMapping
    public String list(Model model, HttpServletRequest request) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

       
        List<Orang> orangList = new ArrayList<>();
        orangList.addAll(guruRepo.findAll());
        orangList.addAll(staffRepo.findAll());

        model.addAttribute("list", transaksiService.findAll());
        model.addAttribute("transaksi", new Transaksi());
        model.addAttribute("orangList", orangList);

        return "transaksi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model, HttpServletRequest request) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

        Transaksi trx = transaksiService.findById(id);

        List<Orang> orangList = new ArrayList<>();
        orangList.addAll(guruRepo.findAll());
        orangList.addAll(staffRepo.findAll());

        model.addAttribute("list", transaksiService.findAll());
        model.addAttribute("transaksi", trx);
        model.addAttribute("orangList", orangList);

        return "transaksi";
    }

    @PostMapping("/submit")
    public String submit(
            @ModelAttribute Transaksi transaksi,
            @RequestParam(required = false) String add,
            @RequestParam(required = false) String edit,
            @RequestParam(required = false) String delete,
            HttpServletRequest request
    ) {

        if (isNotLoggedIn(request))
            return "redirect:/auth/login";

        if (transaksi.getOrang() != null) {
            transaksi.setGaji(transaksi.getOrang().hitungGaji());
        }

        if (add != null) {
            transaksiService.save(transaksi);

        } else if (edit != null) {
            transaksiService.edit(transaksi);

        } else if (delete != null) {
            transaksiService.delete(transaksi.getId());
        }

        return "redirect:/transaksi";
    }
}
