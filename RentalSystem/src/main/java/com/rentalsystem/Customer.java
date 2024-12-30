package com.rentalsystem;

public class Customer {
    private String customerName;
    private String customerID;
    private String customerPhoneNumber;
    private String customerEmail;
    private boolean rentedAVehicle;
    private boolean returnedAVehicle;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer (String customerName, String customerID, String customerPhoneNumber, String customerEmail,
                     boolean rentedAVehicle, boolean returnedAVehicle) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.rentedAVehicle = rentedAVehicle;
        this.returnedAVehicle = returnedAVehicle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public boolean isRentedAVehicle() {
        return rentedAVehicle;
    }

    public void setRentedAVehicle(boolean rentedAVehicle) {
        this.rentedAVehicle = rentedAVehicle;
    }

    public boolean isReturnedAVehicle() {
        return returnedAVehicle;
    }

    public void setReturnedAVehicle(boolean returnedAVehicle) {
        this.returnedAVehicle = returnedAVehicle;
    }

    public void displayCustomerDetails() {
        System.out.println("Customer Name: " + getCustomerName());
        System.out.println("Customer ID: " + getCustomerID());
        System.out.println("Customer Phone Number:" + getCustomerPhoneNumber());
        System.out.println("Customer Email: " + getCustomerEmail());
        System.out.println("Rented a Vehicle already?: " + ((rentedAVehicle) ? "Yes" : "No"));
        System.out.println("Returned Vehicle?: " + ((returnedAVehicle) ? "Yes" : "No"));
        if (!returnedAVehicle) {
            System.out.println("You are not eligible to rent another car.");
        }
    }

}
