package com.rentalsystem;

import java.io.IOException;

public class RentalSystem {
    public static void main(String[] args) throws IOException {
        // Greeting message
        System.out.println("Hello and welcome to Nii's Rentals. " +
                "We have any vehicle of your choice; Car, trucks, motorcycles");
        System.out.println();
        System.out.println("""
                Pricing:\s
                • Cars have base price of GH₵100.00 a day. Additives like car type and chauffeur come with their own cost
                • Truck have a base price of GH₵150.00 day. Cargo type and cargo capacity will determine the overall costs\s
                • Motorcycles have a base price GH₵70.00. Bikes with more than 500CC come at a different price.""");
        System.out.println();
        System.out.println("Have a nice renting!");
        System.out.println();

        RentalAgency agency = null;
        try {
            agency = new RentalAgency();
            agency.displayFleet();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return; // Exit if we can't initialize the agency
        }

        // For space
        System.out.println(" ");

        // Customer Login or Registration
        Customer customer = null;
        while (customer == null) {
            System.out.println("Do you want to [L]ogin or [R]egister? ");
            String choice = agency.scanner.nextLine().toLowerCase();
            switch (choice) {
                case "l":
                    customer = agency.login();
                    if (customer == null) {
                        System.out.println("Login failed. Please try again or register.");
                    }
                    break;
                case "r":
                    customer = agency.registerCustomer();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 'L' for login or 'R' to register.");
            }

        }

        // Interaction loop
        while (true) {
            System.out.println("Would you like to [R]ent or [Re]turn a vehicle?");
            String choice = agency.scanner.nextLine().toLowerCase();

            switch (choice) {
                case "r":
                    agency.displayAvailableVehicles();
                    System.out.println("Enter the Vehicle ID you want to rent: ");
                    String vehicleIDToRent = agency.scanner.nextLine();
                    agency.rentVehicle(vehicleIDToRent, customer);
                    break;
                case "re":
                    System.out.println("Enter the Vehicle ID you want to return:");
                    String vehicleIDToReturn = agency.scanner.nextLine();
                    agency.returnVehicle(vehicleIDToReturn, customer);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 'R' for rent or 'Re' for return.");
                    continue;
            }

            System.out.println("Would you like to continue? [Y/N]");
            String continueChoice = agency.scanner.nextLine().toLowerCase();
            if (!continueChoice.equals("y")) {
                break;
            }
        }

        // Close the scanner when done
        if (agency != null) {
            agency.scanner.close();
        }
    }
}