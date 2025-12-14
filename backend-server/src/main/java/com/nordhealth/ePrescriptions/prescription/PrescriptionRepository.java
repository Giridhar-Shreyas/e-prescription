package com.nordhealth.ePrescriptions.prescription;

import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long>{
    
}
