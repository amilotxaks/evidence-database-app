package org.backend.evidencedatabasewebapp.services;

import lombok.RequiredArgsConstructor;
import org.backend.evidencedatabasewebapp.dtos.AddEvidenceForm;
import org.backend.evidencedatabasewebapp.entities.Evidence;
import org.backend.evidencedatabasewebapp.repository.EvidenceRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvidenceService {
    private final EvidenceRepository evidenceRepository;

    public Optional<Evidence> findByCaseNumber(String caseNumber) {
        return evidenceRepository.findByCaseNumber(caseNumber);
    }

    public void createEvidence(AddEvidenceForm form) {
        try {
            Evidence evidence = Evidence.builder()
                    .caseNumber(form.getCaseNumber())
                    .accused(form.getAccused())
                    .reason(form.getReason())
                    .caseType(form.getCaseType())
                    .severity(form.getSeverity())
                    .victim(form.getVictim())
                    .caseDescription(form.getCaseDescription())
                    .build();
            evidenceRepository.save(evidence);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Невозможно создать доказательство. Нарушение уникальности CaseNumber.");
        }
    }

    public void updateEvidenceDescription(String caseNumber, String caseDescription) {
        Evidence evidence = evidenceRepository.findByCaseNumber(caseNumber)
                .orElseThrow(() -> new IllegalArgumentException("Дело не найдено"));

        evidence.setCaseDescription(caseDescription);
        evidenceRepository.save(evidence);
    }
}
