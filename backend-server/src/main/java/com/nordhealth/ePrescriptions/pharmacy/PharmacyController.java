package com.nordhealth.ePrescriptions.pharmacy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class PharmacyController {
    
    @Autowired
    private PharmacyService pharmacyService;

    @RequestMapping(value = "api/public/pharmacies", method=RequestMethod.GET)
    List<Pharmacy> getAllPharmacies() {
        return pharmacyService.getAllPharmacy();
    }
    

}
