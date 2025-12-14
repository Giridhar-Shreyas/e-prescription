package com.nordhealth.ePrescriptions.prescription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    
    @Autowired
    PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllprescriptions() {
		List<Prescription> prescriptions = new ArrayList<>();
		prescriptionRepository.findAll().forEach(prescriptions::add);
		return prescriptions;
	}

}
