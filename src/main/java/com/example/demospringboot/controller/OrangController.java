package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Guru;
import com.example.demospringboot.entity.Orang;
import com.example.demospringboot.service.OrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orang")
public class OrangController {

    @Autowired
    private OrangService orangService;

    @GetMapping({"", "/"})
    public String orangPage(Model model) {

        model.addAttribute("orangRec", new Guru());

        model.addAttribute("listOrang", orangService.getAllOrang());

        return "orang";
    }


    @PostMapping("/submit")
    public String addOrang(@ModelAttribute("orangRec") Orang orang) {
        orangService.addOrang(orang);
        return "redirect:/orang";
    }


    @PostMapping("/submit/{id}")
    public String updateOrang(@PathVariable Long id,
                              @ModelAttribute("orangRec") Orang orang) {

        orangService.updateOrang(id, orang);
        return "redirect:/orang";
    }


    @GetMapping("/{id}")
    public String selectOrang(@PathVariable Long id, Model model) {

        model.addAttribute("orangRec", orangService.getOrangById(id));
        model.addAttribute("listOrang", orangService.getAllOrang());

        return "orang";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrang(@PathVariable Long id) {
        orangService.deleteOrang(id);
        return "redirect:/orang";
    }
}
