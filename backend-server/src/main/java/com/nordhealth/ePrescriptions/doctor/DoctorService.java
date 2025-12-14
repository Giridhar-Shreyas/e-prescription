package com.nordhealth.ePrescriptions.doctor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
		doctorRepository.findAll().forEach(doctorList::add);
		return doctorList;
    }
}
