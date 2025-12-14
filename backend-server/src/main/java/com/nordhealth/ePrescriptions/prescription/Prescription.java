package com.nordhealth.ePrescriptions.prescription;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nordhealth.ePrescriptions.doctor.Doctor;
import com.nordhealth.ePrescriptions.user.EndUser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prescription {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    EndUser user;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    LocalDateTime createdTS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EndUser getUser() {
        return user;
    }

    public void setUser(EndUser user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(LocalDateTime createdTS) {
        this.createdTS = createdTS;
    }

    public LocalDateTime getUpdatedTS() {
        return updatedTS;
    }

    public void setUpdatedTS(LocalDateTime updatedTS) {
        this.updatedTS = updatedTS;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    LocalDateTime updatedTS;

    LocalDate expireDate;
}
