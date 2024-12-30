# Rental System Documentation

## Overview
The Rental System is a Java-based application that allows users to rent and return vehicles such as cars, trucks, and motorcycles. The system manages customer registrations, vehicle rentals, and returns, and maintains data persistence using JSON files.  

## Project Structure
The project is organized as follows:  
- src/main/java/com/rentalsystem: Contains the main Java source files.
- src/main/resources: Contains the JSON files for data persistence.

## Key Classes

### 1. RentalSystem
The entry point of the application. It handles user interactions and coordinates the rental and return processes.  

### 2. RentalAgency
Manages the fleet of vehicles and customer registrations. It provides methods for renting and returning vehicles, as well as displaying available vehicles.  

### 3. Customer
Represents a customer in the system. It includes customer details such as name, ID, phone number, email, and rental status.  

### 4. Vehicle
An abstract class representing a vehicle. It includes common properties like vehicle ID, brand, model, and availability status.  

### 5. Car, Truck, Motorcycle
Subclasses of Vehicle representing specific types of vehicles. Each subclass includes additional properties relevant to the vehicle type.  

### 6. VehicleFleet
Represents the fleet of vehicles, including lists of cars, trucks, and motorcycles.  

### 7. JSONHandler
Handles reading and writing JSON files for data persistence. It provides methods to read and update customer and vehicle data.  

### JSON Files

####  Customers.json
Stores customer data in JSON format. Each customer entry includes details such as name, ID, phone number, email, and rental status.  

#### vehicles.json
Stores vehicle data in JSON format. It includes lists of cars, trucks, and motorcycles.  

### Usage
#### Running the Application
To run the application, execute the main method in the RentalSystem class. The application will prompt the user to log in or register, and then allow them to rent or return vehicles.  

#### Registering a Customer
1. The user is prompted to enter their full name, phone number, and email address.
2. A unique customer ID is generated based on the name.
3. The customer details are saved to the Customers.json file.

#### Logging In
1. The user is prompted to enter their customer ID.
2. The system checks the Customers.json file for the entered ID.
3. If the ID is found, the user is logged in; otherwise, an error message is displayed.

#### Renting a Vehicle
1. The user is prompted to enter the vehicle ID they want to rent.
2. The system checks if the vehicle is available.
3. If available, the user is prompted to enter the number of days for the rental.
4. The rental cost is calculated and displayed.
5. The vehicle's status is updated to rented, and the customer's rental status is updated.

#### Returning a Vehicle
1. The user is prompted to enter the vehicle ID they want to return.
2. The system checks if the vehicle is currently rented.
3. If rented, the vehicle's status is updated to available, and the customer's rental status is updated.

### Error Handling
The application includes error handling for various scenarios, such as:  
- Invalid customer ID during login.
- Duplicate customer ID during registration.
- Invalid vehicle ID during rental and return.
- JSON read/write errors.

### Dependencies
The project uses the following dependencies:  
- Jackson for JSON processing.
- Maven for project management and build automation.

### Conclusion
The Rental System provides a simple and efficient way to manage vehicle rentals and returns. It ensures data persistence using JSON files and includes error handling for a smooth user experience.