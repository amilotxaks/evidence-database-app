package org.backend.evidencedatabasewebapp.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.evidencedatabasewebapp.entities.Evidence;
import org.backend.evidencedatabasewebapp.repository.EvidenceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/evidence")
@RequiredArgsConstructor
public class EvidenceController {

    private final EvidenceRepository evidenceRepository;

    @GetMapping("/{severity}")
    public String getCasesByDegree(@PathVariable("severity") String severity, Model model) {
        model.addAttribute("title", severity);
        List<Evidence> evidences = switch (severity) {
            case "minor" -> evidenceRepository.findAllBySeverity("Небольшой тяжести");
            case "moderate" -> evidenceRepository.findAllBySeverity("Средней тяжести");
            case "serious" -> evidenceRepository.findAllBySeverity("Тяжкое");
            case "severe" -> evidenceRepository.findAllBySeverity("Особо тяжкое");
            default -> evidenceRepository.findAll();
        };

        model.addAttribute("evidences", evidences);
        return "evidence-list";
    }
}