package com.rentalsystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;

public class Motorcycle extends Vehicle{
    @JsonProperty("type")
    private final String type = "motorcycle";
    private int engineCapacity; // In CC
    private String bikeType; // E.g., Sport bike, Cruiser, Dirt bike, Touring
    private boolean helmetIncluded;

    public Motorcycle(
            @JsonProperty("vehicleID") String vehicleID,
            @JsonProperty("brand") String brand,
            @JsonProperty("model") String model,
            @JsonProperty("isAvailable") boolean isAvailable,
            @JsonProperty("engineCapacity") int engineCapacity,
            @JsonProperty("bikeType") String bikeType,
            @JsonProperty("helmetIncluded") boolean helmetIncluded) {
        super(vehicleID, brand, model, isAvailable);
        this.engineCapacity = engineCapacity;
        this.bikeType = bikeType;
        this.helmetIncluded = helmetIncluded;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public boolean isHelmetIncluded() {
        return helmetIncluded;
    }

    public void setHelmetIncluded(boolean helmetIncluded) {
        this.helmetIncluded = helmetIncluded;
    }

    @Override
    public double calculateRentalCost(int days) {
        // BAse rate + surcharge for high engine capacity
        double baseRate = 70.0;
        double surcharge = engineCapacity > 500 ? 10.0 : 0.0;
        return (baseRate + surcharge) * days;
    }

    @Override
    public boolean isAvailableForRent() {
        return isAvailable();
    }


    public void displayMotorcycleDetails() {
        System.out.println("Motorcycle ID: " + getVehicleID());
        System.out.println("Brand:" + getBrand());
        System.out.println("Model: " + getModel());
        System.out.println("Engine Capacity: " + getEngineCapacity() + "CC");
        System.out.println("Type: " + getBikeType());
        System.out.println("Helmet Included?: " + ((helmetIncluded) ? "Yes" : "No"));
        System.out.println("Available: " + (isAvailable() ? "Yes" : "No"));
    }
}
