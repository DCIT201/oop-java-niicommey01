package com.rentalsystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class VehicleFleet {
    @JsonProperty("Cars")
    private List<Car> cars;

    @JsonProperty("Trucks")
    private List<Truck> trucks;

    @JsonProperty("Motorcycles")
    private List<Motorcycle> motorcycles;

    // Getters
    public List<Car> getCars() {
        return cars;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public List<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    // Setters
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public void setMotorcycles(List<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }
}