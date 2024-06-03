package org.backend.evidencedatabasewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {
    @GetMapping(value = "/login")
    public String loginForm(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }

        model.addAttribute("title", "Вход");
        return "login";
    }
}
