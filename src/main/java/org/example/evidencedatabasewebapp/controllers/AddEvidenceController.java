package org.example.evidencedatabasewebapp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.evidencedatabasewebapp.dtos.AddEvidenceForm;
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

@Controller
@RequiredArgsConstructor
public class AddEvidenceController {
    private final Validator validator;
    private final EvidenceService evidenceService;

    @GetMapping(value = "/add-evidence")
    public String addEvidence(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("title", "Добавить новое дело");
        model.addAttribute("username", username);
        model.addAttribute("AddEvidenceForm", new AddEvidenceForm());
        return "add-evidence";
    }

    @PostMapping(value = "/add-evidence")
    public String evidenceSubmit(@ModelAttribute("AddEvidenceForm") AddEvidenceForm form, Model model, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("title", "Добавить новое дело");
        model.addAttribute("username", username);

        validator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "add-evidence";
        }

        try {
            evidenceService.createEvidence(form);
            model.addAttribute("addEvidenceSuccess", "Дело №" + form.getCaseNumber() + " успешно добавлено!");
        } catch (IllegalArgumentException ex) {
            model.addAttribute("addEvidenceError", ex.getMessage());
        }

        return "add-evidence";
    }
}