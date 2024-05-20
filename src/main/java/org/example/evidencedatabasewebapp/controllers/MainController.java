package org.example.evidencedatabasewebapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.evidencedatabasewebapp.entities.Evidence;
import org.example.evidencedatabasewebapp.repository.EvidenceRepository;
import org.example.evidencedatabasewebapp.userDetails.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final EvidenceRepository evidenceRepository;

    @GetMapping(value = "/")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("title", "Поиск судебного дела");
        model.addAttribute("username", username);
        return "main";
    }

    @PostMapping(value = "/")
    public String getEvidence(HttpServletRequest request, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();

        try {
            Long caseId = Long.parseLong(request.getParameter("caseName"));
            Optional<Evidence> evidence = evidenceRepository.findById(caseId);
            evidence.ifPresentOrElse(value -> model.addAttribute("evidence", value), () -> model.addAttribute("evidenceEmpty", "Дело не найдено в базе данных."));
            model.addAttribute("username", username);
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Неверный формат номера дела.");
        }

        return "main";
    }
}