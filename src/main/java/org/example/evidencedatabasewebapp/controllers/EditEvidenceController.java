package org.backend.evidencedatabasewebapp.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.evidencedatabasewebapp.dtos.EditEvidenceForm;
import org.backend.evidencedatabasewebapp.services.EvidenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EditEvidenceController {
    private final EvidenceService evidenceService;

    @PostMapping(value = "/edit-evidence")
    public ResponseEntity<?> editEvidence(@RequestBody EditEvidenceForm editEvidenceForm) {
        String caseNumber = editEvidenceForm.getCaseNumber();
        String caseDescription = editEvidenceForm.getCaseDescription();
        try {
            evidenceService.updateEvidenceDescription(caseNumber, caseDescription);
            return ResponseEntity.ok("Дело успешно обновлено");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
