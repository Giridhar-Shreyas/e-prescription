package com.nordhealth.ePrescriptions.pharmacy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PharmacyController {
    
    @Autowired
    private PharmacyService pharmacyService;

    @RequestMapping(value = "/pharmacies", method=RequestMethod.GET)
    List<Pharmacy> getAllPharmacies(@RequestParam String param) {
        return pharmacyService.getAllPharmacy();
    }
    

}
