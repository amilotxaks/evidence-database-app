package org.example.evidencedatabasewebapp.services;

import lombok.RequiredArgsConstructor;
import org.example.evidencedatabasewebapp.dtos.AddEvidenceForm;
import org.example.evidencedatabasewebapp.entities.Evidence;
import org.example.evidencedatabasewebapp.repository.EvidenceRepository;
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
                    .caseDescription(form.getCaseDescription())
                    .build();
            evidenceRepository.save(evidence);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Невозможно создать доказательство. Нарушение уникальности CaseNumber.");
        }
    }
}