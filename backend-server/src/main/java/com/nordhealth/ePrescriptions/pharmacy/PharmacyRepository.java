package com.nordhealth.ePrescriptions.pharmacy;

import org.springframework.data.repository.CrudRepository;

public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {
    
}
