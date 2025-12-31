package com.nordhealth.ePrescriptions.doctor;

import java.time.LocalDateTime;
import java.util.List;

import com.nordhealth.ePrescriptions.User;
import com.nordhealth.ePrescriptions.prescription.Prescription;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "doctors")
public class Doctor extends User{
    
   
    private String regId; 
    private String workPlace;
    private String address;
    
    // Specializations
    private String specs;


    @OneToMany(mappedBy = "doctor")
    private List<Prescription> prescription;


    public String getRegId() {
        return regId;
    }


    public void setRegId(String regId) {
        this.regId = regId;
    }


    public String getWorkPlace() {
        return workPlace;
    }


    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getSpecs() {
        return specs;
    }


    public void setSpecs(String specs) {
        this.specs = specs;
    }


    public List<Prescription> getPrescription() {
        return prescription;
    }


    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }



}
