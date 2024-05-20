package org.example.evidencedatabasewebapp.repository;

import org.example.evidencedatabasewebapp.entities.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    Optional<Evidence> findByCaseNumber(String caseNumber);
}