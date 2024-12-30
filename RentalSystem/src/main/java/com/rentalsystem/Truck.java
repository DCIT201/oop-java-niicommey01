package com.rentalsystem;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Truck extends Vehicle {
    @JsonProperty("type")
    private final String type = "truck";
    private double cargoCapacity; // In tons
    private int numberOfAxles;
    private String trailerType;

    public Truck(
            @JsonProperty("vehicleID") String vehicleID,
            @JsonProperty("brand") String brand,
            @JsonProperty("model") String model,
            @JsonProperty("isAvailable") boolean isAvailable,
            @JsonProperty("cargoCapacity") double cargoCapacity,
            @JsonProperty("numberOfAxles") int numberOfAxles,
            @JsonProperty("trailerType") String trailerType
    ) {
        super(vehicleID, brand, model, isAvailable);
        this.cargoCapacity = cargoCapacity;
        this.numberOfAxles = numberOfAxles;
        this.trailerType = trailerType;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }
    
    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getNumberOfAxles() {
        return numberOfAxles;
    }

    public void setNumberOfAxles(int numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }

    public String getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    @Override
    public double calculateRentalCost(int days) {
        // Base rate + surcharge based on capacity
        double baseRate = 150.0;
        double surcharge = cargoCapacity * 10.0; // ₵10 per ton
        return (baseRate + surcharge) * days;
    }

    @Override
    public boolean isAvailableForRent() {
        return isAvailable();
    }
    

    public void displayTruckDetails() {
        System.out.println("Truck ID: " + getVehicleID());
        System.out.println("Truck model: " + getModel());
        System.out.println("Cargo Capacity: " + getCargoCapacity());
        System.out.println("Number of Axles: " + getNumberOfAxles());
        System.out.println("Trailer Type: " + getTrailerType());
        System.out.println("Availability: " + (isAvailable() ? "Yes" : "No"));
    }
    
}
