package com.nordhealth.ePrescriptions.prescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "api/public/prescriptions", method=RequestMethod.GET)
    List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllprescriptions();
    }
    

}
