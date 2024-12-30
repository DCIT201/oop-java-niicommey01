package com.rentalsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.Random;

public class RentalAgency {
    private VehicleFleet vehicleFleet;
    private List<Customer> customers = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    // Read JSON file
    public RentalAgency() {
        try {
            this.vehicleFleet = JSONHandler.readVehicles(); // Assign directly
            if (this.vehicleFleet == null) {
                System.err.println("Failed to read vehicles data from JSON.");
            } else {
                System.out.println("Vehicle fleet data successfully loaded.");
            }
        } catch (IOException e) {
            System.err.println("Failed to load vehicles from JSON: " + e.getMessage());
            this.vehicleFleet = new VehicleFleet(); // Initialize with empty fleet in case of failure
        }
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> allVehicles = new ArrayList<>();
        if (vehicleFleet != null) {
            if (vehicleFleet.getCars() != null) allVehicles.addAll(vehicleFleet.getCars());
            if (vehicleFleet.getTrucks() != null) allVehicles.addAll(vehicleFleet.getTrucks());
            if (vehicleFleet.getMotorcycles() != null) allVehicles.addAll(vehicleFleet.getMotorcycles());
        }
        return allVehicles;
    }

    // To get vehicles by type
    public List<? extends Vehicle> getVehiclesByType(String type) {
        if (vehicleFleet == null) {
            return new ArrayList<>();
        }
        switch (type.toLowerCase()) {
            case "cars":
                return vehicleFleet.getCars() != null ? vehicleFleet.getCars() : new ArrayList<>();
            case "trucks":
                return vehicleFleet.getTrucks() != null ? vehicleFleet.getTrucks() : new ArrayList<>();
            case "motorcycles":
                return vehicleFleet.getMotorcycles() != null ? vehicleFleet.getMotorcycles() : new ArrayList<>();
            default:
                return new ArrayList<>();
        }
    }

    // Displaying the fleet
    public void displayFleet() throws IOException {
        if (vehicleFleet != null) {
            JSONHandler.readVehicles();
        } else {
            System.err.println("Vehicle fleet data is not available.");
        }
    }

    // Customer registration
    public Customer registerCustomer() {
        System.out.println("Please enter your full name: ");
        String fullName = scanner.nextLine();

        // Generate ID based on name
        String customerID = generateIDFromName(fullName);

        System.out.println("Please enter your phone number");
        String phoneNumber = scanner.nextLine();

        System.out.println("Please enter your email address: ");
        String email = scanner.nextLine();

        Customer newCustomer = new Customer(fullName, customerID, phoneNumber, email, false, true);
        customers.add(newCustomer);

        // Load existing customers
        CustomerRegistry registry = null;
        try {
            registry = JSONHandler.readCustomers();
        } catch (IOException e) {
            System.err.println("Failed to read customers from JSON: " + e.getMessage());
        }
        List<Customer> customerList = registry != null ? registry.getCustomers() : new ArrayList<>();
        if (customerList == null) {
            customerList = new ArrayList<>();
        }

        // Check for duplicate customer IDs
        if (customerList.stream().anyMatch((c -> c.getCustomerID().equals(customerID)))) {
            System.err.println("Customer ID already exists. Please try again.");
            return null;
        } else {
            customerList.add(newCustomer);
        }

        // Update JSON
        try {
            JSONHandler.updateCustomers(registry);
        } catch (IOException e) {
            System.err.println("Failed to update customers JSON: " + e.getMessage());
        }

        System.out.println("Customer registered with ID: " + customerID);
        return newCustomer;
    }

    // Method to generate ID from name
    private String generateIDFromName(String fullName) {
        String[] names = fullName.split("\\s+");
        if (names.length < 2) {
            return fullName.substring(0, Math.min(1, fullName.length())).toUpperCase() +
                    generateRandomNumbers(4);
        } else {
            String firstName = names[0];
            String lastName = names[names.length - 1]; // Assuming the last word is the last name
            return firstName.substring(0, Math.min(1, firstName.length())).toUpperCase() +
                    lastName.toUpperCase() +
                    generateRandomNumbers(4);
        }
    }

    //Method to generate random numbers for the Customer ID
    private String generateRandomNumbers(int count) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            // Ensure numbers are unique by checking against existing numbers in StringBuilder
            int randomNumber;
            do {
                randomNumber = random.nextInt(10); // 0 to 9
            } while (sb.toString().contains(String.valueOf(randomNumber)));
            sb.append(randomNumber);
        }
        return sb.toString();
    }

    // Customer Login
    public Customer login() {
    System.out.println("Please enter your Customer ID: ");
    String customerID = scanner.nextLine().toUpperCase();

    CustomerRegistry registry = null;
    try {
        registry = JSONHandler.readCustomers();
    } catch (IOException e) {
        System.err.println("Failed to read customers from JSON: " + e.getMessage());
        return null;
    }

    List<Customer> customerList = registry != null ? registry.getCustomers() : new ArrayList<>();
    Customer customer = customerList.stream()
            .filter(c -> c.getCustomerID().equals(customerID))
            .findFirst()
            .orElse(null);

    if (customer == null) {
        System.err.println("Customer ID not found.");
    }

    return customer;
    }


    // Method to rent a vehicle
    public void rentVehicle(String vehicleID, Customer customer) {
        // Ensure the customer hasn't rented a vehicle yet
        if (customer.isRentedAVehicle()) {
            System.err.println("You must return your previously rented vehicle before renting another.");
            return;
        }

        Vehicle vehicle = getAllVehicles().stream()
                .filter(v -> v.getVehicleID().equalsIgnoreCase(vehicleID))
                .findFirst().orElse(null);

        if (vehicle == null) {
            System.err.println("Vehicle not found.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.err.println("Vehicle " + vehicleID + " is not available for rent.");
            return;
        }

        System.out.println("How many days do you want to rent this vehicle?");
        int days = 0;
        try {
            days = Integer.parseInt(scanner.nextLine());
            if (days <= 0) {
                throw new IllegalArgumentException("Number of days must be positive.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Please enter a valid number of days.");
            return;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        double cost = vehicle.calculateRentalCost(days);
        System.out.println("The cost to rent " + vehicle.getVehicleID() + " (" + vehicle.getBrand() + " " + vehicle.getModel() + ") for " + days + " days is GH₵" + String.format("%.2f", cost));

        vehicle.rent();
        customer.setRentedAVehicle(true);
        customer.setReturnedAVehicle(false);
        System.out.println("Vehicle " + vehicleID + " rented to " + customer.getCustomerName());

        try {
            JSONHandler.updateVehicles(vehicleFleet);
        } catch (IOException e) {
            System.err.println("Failed to update JSON: " + e.getMessage());
        }
    }

    // Method to return a vehicle
    public void returnVehicle(String vehicleID, Customer customer) throws IOException {
        Vehicle vehicle = getAllVehicles().stream()
                .filter(vehicle1 -> vehicle1.getVehicleID().equals(vehicleID))
                .findFirst().orElse(null);

        if( vehicle == null) {
            System.err.println("Vehicle not found.");
            return;
        }

        if (!vehicle.isRented()) {
            System.err.println("This vehicle is not currently rented.");
            return;
        }

        // Check if customer actually rented this vehicle
        if (!customer.isRentedAVehicle()) {
            System.err.println("You haven't rented any vehicle.");
            return;
        }

        vehicle.returnVehicle();
        customer.setReturnedAVehicle(false);
        customer.setReturnedAVehicle(true);
        System.out.println("Vehicle " + vehicleID + " returned by " + customer.getCustomerID());

        try {
            JSONHandler.updateVehicles(vehicleFleet);
        } catch (IOException e) {
            System.err.println("Failed to update JSON after return: " + e.getMessage());
        }
    }

    // Method to display available vehicles
    public void displayAvailableVehicles() {
        List<Vehicle> availableVehicles = getAllVehicles().stream()
                .filter(Vehicle::isAvailable)
                .collect(Collectors.toList());

        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles available for rent.");
        } else {
            System.out.println("Available Vehicles:");
            availableVehicles.forEach(v -> System.out.println("- " + v.getVehicleID() + " (" + v.getBrand() + " " + v.getModel() + ")"));
        }
    }
}