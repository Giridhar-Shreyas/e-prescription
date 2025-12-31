package com.nordhealth.ePrescriptions.doctor;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long>{
    Optional<Doctor> findByUsername(String username);
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByKeycloakId(String keycloakId);
}
