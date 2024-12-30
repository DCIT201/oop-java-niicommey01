package com.rentalsystem;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = Truck.class, name = "truck"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "motorcycle")
})

public abstract class Vehicle {
    private String vehicleID;
    private String brand;
    private String model;
    private boolean isAvailable;
    private boolean isRented = false;

    public Vehicle(String vehicleID, String brand, String model, boolean isAvailable) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.isAvailable = isAvailable;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
        this.setAvailable(!rented);
    }

    public void rent() {
        if (isAvailable()) {
            this.isRented = true;
            this.isAvailable = false;
        } else {
            throw new IllegalStateException("Vehicle is not available for rent");
        }
    }

    public void returnVehicle() {
        this.isRented = false;
        this.isAvailable = true;
    }

    public abstract double calculateRentalCost(int days);
    public abstract boolean isAvailableForRent();

    public boolean isEmpty() {
        return vehicleID == null || vehicleID.isEmpty() ||
        brand == null || brand.isEmpty() ||
                model == null || model.isEmpty();
    }
}
