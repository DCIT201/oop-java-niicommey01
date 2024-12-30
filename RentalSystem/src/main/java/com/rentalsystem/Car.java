package com.rentalsystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;

public class Car extends Vehicle{
    @JsonProperty("type")
    private final String type = "car";
    private String carType; // E.g., Luxury, Sedan, SUV
    private boolean withChauffeur;
    public static final double baseRate = 100.00;

    public Car(
            @JsonProperty("vehicleID") String vehicleID,
            @JsonProperty("brand") String brand,
            @JsonProperty("model") String model,
            @JsonProperty("isAvailable") boolean isAvailable,
            @JsonProperty("carType") String carType,
            @JsonProperty("withChauffeur") boolean withChauffeur
    ) {
        super(vehicleID, brand, model, isAvailable);
        this.carType = carType;
        this.withChauffeur = withChauffeur;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public boolean isWithChauffeur() {
        return withChauffeur;
    }

    public void setWithChauffeur(boolean withChauffeur) {
        this.withChauffeur = withChauffeur;
    }

    @Override
    public double calculateRentalCost(int days) {
        double surcharge1 = carType.equals("Electric") ? 30.0 : 0.0;
        double surcharge2 = withChauffeur ? 100.0 : 0.0;
        double surcharges = surcharge1 + surcharge2;
        return (baseRate + surcharges) * days;
    }

    @Override
    public boolean isAvailableForRent() {
        return isAvailable();
    }

    public void displayCarDetails() {
        System.out.println("Car ID: " + getVehicleID());
        System.out.println("Car Brand: " + getBrand());
        System.out.println("Car Model: " + getModel());
        System.out.println("With Chauffeur: " + ((withChauffeur)? "Yes" : "No"));
        System.out.println("Availability: " + (isAvailable() ? "Yes" : "No"));
    }

}
