package com.nordhealth.ePrescriptions.user;

import java.util.List;


import com.nordhealth.ePrescriptions.userInterface.User;
import com.nordhealth.ePrescriptions.prescription.Prescription;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="end_user")
public class EndUser extends User{
    

    private String address;

    @OneToMany(mappedBy = "user")
    private List<Prescription> prescription;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Prescription> getPrescription() {
        return prescription;
    }

    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }
    
    
}
