package org.example.evidencedatabasewebapp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.evidencedatabasewebapp.dtos.GetEvidenceForm;
import org.example.evidencedatabasewebapp.entities.Evidence;
import org.example.evidencedatabasewebapp.services.EvidenceService;
import org.example.evidencedatabasewebapp.userDetails.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final EvidenceService evidenceService;
    private final Validator validator;

    @GetMapping(value = "/")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("title", "Поиск судебного дела");
        model.addAttribute("username", username);
        model.addAttribute("GetEvidenceForm", new GetEvidenceForm());
        return "main";
    }

    @PostMapping(value = "/")
    public String getEvidence(@ModelAttribute("GetEvidenceForm") GetEvidenceForm form, Model model, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("title", "Поиск судебного дела");
        model.addAttribute("username", username);

        validator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "main";
        }

        String caseNumber = form.getCaseNumber();
        Optional<Evidence> evidence = evidenceService.findByCaseNumber(caseNumber);
        evidence.ifPresentOrElse(value -> model.addAttribute("evidence", value), () -> model.addAttribute("evidenceEmpty", "Дело не найдено в базе данных."));

        return "main";
    }
}