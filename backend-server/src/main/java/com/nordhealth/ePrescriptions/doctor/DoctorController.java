package com.nordhealth.ePrescriptions.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "api/public/doctors", method=RequestMethod.GET)
    List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
    

}
