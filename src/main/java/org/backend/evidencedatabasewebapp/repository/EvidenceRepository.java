package org.backend.evidencedatabasewebapp.repository;

import org.backend.evidencedatabasewebapp.entities.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    Optional<Evidence> findByCaseNumber(String caseNumber);

    List<Evidence> findAllBySeverity(String severity);
}
