package com.nordhealth.ePrescriptions.pharmacy;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {
    
    Optional<Pharmacy> findByUsername(String username);
    Optional<Pharmacy> findByEmail(String email);
    Optional<Pharmacy> findByKeycloakId(String keycloakId);
}
