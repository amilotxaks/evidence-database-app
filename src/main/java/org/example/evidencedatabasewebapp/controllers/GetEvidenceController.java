package org.backend.evidencedatabasewebapp.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.evidencedatabasewebapp.dtos.GetEvidenceForm;
import org.backend.evidencedatabasewebapp.entities.Evidence;
import org.backend.evidencedatabasewebapp.services.EvidenceService;
import org.backend.evidencedatabasewebapp.userDetails.UserDetailsImpl;
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
public class GetEvidenceController {
    private final EvidenceService evidenceService;
    private final Validator validator;

    @GetMapping(value = "/")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("title", "Поиск судебного дела");
        model.addAttribute("username", username);
        model.addAttribute("GetEvidenceForm", new GetEvidenceForm());
        return "get-evidence";
    }

    @PostMapping(value = "/")
    public String getEvidence(@ModelAttribute("GetEvidenceForm") GetEvidenceForm form, Model model, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("title", "Поиск судебного дела");
        model.addAttribute("username", username);

        validator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "get-evidence";
        }

        String caseNumber = form.getCaseNumber();
        Optional<Evidence> evidence = evidenceService.findByCaseNumber(caseNumber);
        evidence.ifPresentOrElse(value -> model.addAttribute("evidence", value), () -> model.addAttribute("evidenceEmpty", "Дело не найдено в базе данных."));

        return "get-evidence";
    }
}
