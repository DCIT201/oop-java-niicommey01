package com.rentalsystem;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONHandler {
    private static final String VEHICLES_FILE_PATH = "RentalSystem/src/main/resources/vehicles.json";
    private static final String CUSTOMERS_FILE_PATH = "RentalSystem/src/main/resources/Customers.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();


    // Ignore Unknown Properties
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // Read Vehicle JSON file
    public static VehicleFleet readVehicles() throws IOException {
        return objectMapper.readValue(new File(VEHICLES_FILE_PATH), VehicleFleet.class);
    }

    // Update File after a vehicle has been rented or returned
    public static void updateVehicles(VehicleFleet fleet) throws IOException {
        objectMapper.writeValue(new File(VEHICLES_FILE_PATH), fleet);
    }

    // Read Customer JSON file
    public static CustomerRegistry readCustomers() throws IOException {
        File file = new File(CUSTOMERS_FILE_PATH);
        if (!file.exists()) {
            // If the file does not exist, create it with an empty customer registry
            objectMapper.writeValue(file, new CustomerRegistry());
        }
        return objectMapper.readValue(file, CustomerRegistry.class);
    }

    // Update it after customer registration
    public static void updateCustomers(CustomerRegistry registry) throws IOException {
        objectMapper.writeValue(new File(CUSTOMERS_FILE_PATH), registry);
    }
}

class CustomerRegistry {
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}