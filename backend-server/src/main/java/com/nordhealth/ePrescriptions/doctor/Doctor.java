package com.nordhealth.ePrescriptions.doctor;

import java.time.LocalDateTime;
import java.util.List;

import com.nordhealth.ePrescriptions.prescription.Prescription;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GenerationType;

@Entity
@SequenceGenerator(sequenceName="doctor_seq", initialValue=1, name="doctor_seq")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "doctor_seq")
    Long Id;
    String name;
    String regId; 
    String workPlace;
    String address;
    
    // Specializations
    String specs;

    int phoneNumber;
    String email;


    LocalDateTime createdTimeStamp;

    LocalDateTime updatedTimeStamp;

    @OneToMany(mappedBy = "doctor")
    List<Prescription> prescription;
    
    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getRegId() {
        return regId;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecs() {
        return specs;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public Doctor() {
        
    }

    public List<Prescription> getPrescription() {
        return prescription;
    }

    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }


}
