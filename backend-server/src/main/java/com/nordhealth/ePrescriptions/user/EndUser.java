package com.nordhealth.ePrescriptions.user;

import java.util.List;

import com.nordhealth.ePrescriptions.prescription.Prescription;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class EndUser {
    
    @Id
    Long Id;
    String name;
    String address;
    String phoneNumber;
    String email;
    @OneToMany(mappedBy = "user")
    List<Prescription> prescription;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Prescription> getPrescription() {
        return prescription;
    }
    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }
    
    
}
