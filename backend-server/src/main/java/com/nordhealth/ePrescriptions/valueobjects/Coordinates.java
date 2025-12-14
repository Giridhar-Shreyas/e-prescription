package com.nordhealth.ePrescriptions.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Coordinates(double latitude, double longitude) {
}