package org.backend.evidencedatabasewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Evidence Database Web App");
        return "index";
    }
}
