package com.nordhealth.ePrescriptions.prescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/prescriptios", method=RequestMethod.GET)
    List<Prescription> getAllPrescriptions(@RequestParam String param) {
        return prescriptionService.getAllprescriptions();
    }
    

}
