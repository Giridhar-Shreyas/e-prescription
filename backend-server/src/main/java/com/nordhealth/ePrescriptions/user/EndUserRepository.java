package com.nordhealth.ePrescriptions.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface EndUserRepository extends CrudRepository<EndUser, Long>{
    Optional<EndUser> findByUsername(String username);
    Optional<EndUser> findByEmail(String email);
    Optional<EndUser> findByKeycloakId(String keycloakId);
}
