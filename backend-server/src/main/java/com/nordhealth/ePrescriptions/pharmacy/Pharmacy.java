package com.nordhealth.ePrescriptions.pharmacy;


import com.nordhealth.ePrescriptions.User;
import com.nordhealth.ePrescriptions.valueobjects.Coordinates;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pharmacies")
public class Pharmacy extends User{

    String regId;
    String name;
    String address;
    String website;
    String description;
    String openingHours;

    @Embedded
    @AttributeOverride(name="latitude", column=@Column(name="location_lat"))
    @AttributeOverride(name="longitude", column=@Column(name="location_lon"))
    private Coordinates location;

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    
    
}
