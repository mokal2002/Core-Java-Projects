<h1>Core Java Projects</h1>
<hr>
<h1>SecureBank</h1>

## Overview

**SecureBank** is a Core Java-based banking system designed to manage user accounts and handle various banking operations securely and efficiently. The project offers a user-friendly interface for essential banking functions such as account creation, money transactions, and balance inquiries.

## Features

- **User Registration and Login**: Secure user registration and login functionality.
- **Account Management**: Create and manage bank accounts with unique identifiers.
- **Money Transactions**:
  - **Debit**: Withdraw money securely from accounts.
  - **Credit**: Deposit money into accounts.
  - **Transfer**: Transfer funds between accounts securely.
- **Balance Inquiry**: Check the current balance of the user's account.
- **Data Persistence**: Utilizes MySQL to securely store user and account data.
- **Error Handling and Transaction Management**: Ensures smooth operation with transaction rollbacks on failure to maintain data integrity.

## Technologies Used

- **Java**: Core language for implementing the banking system.
- **MySQL**: Database management for storing user and account data.
- **JDBC**: Java Database Connectivity for MySQL interactions.
- **Maven**: Project management and build tool.

## Getting Started

### Prerequisites

- **Java JDK** (version 8 or higher)
- **MySQL** (version 5.7 or higher)
- **Maven** (version 3.6 or higher)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/mokal2002/SecureBank.git
   cd SecureBank
<hr>

# Hospital Management System

A simple console-based Hospital Management System developed in Core Java using MySQL and JDBC. This system manages patients, doctors, and appointments, providing basic CRUD functionalities.

## Features

- **Add Patient**: Register a new patient by entering their name, age, and gender.
- **View Patients**: Display a list of all patients with their details.
- **View Doctors**: Display a list of all doctors along with their specializations.
- **Book Appointment**: Schedule an appointment between a patient and a doctor for a specified date.

## Technologies Used

- **Java**: Core Java for application logic.
- **JDBC**: For connecting and interacting with the MySQL database.
- **MySQL**: Database to store patient, doctor, and appointment data.

## Project Structure

- **`config`**: Contains the `AppConstants` class for database configuration.
- **`service`**: Contains service classes (`DoctorService` and `PatientService`) that handle business logic.
- **`validation`**: Includes the `valid` class for input validation.
- **`HospitalManagementSystem.java`**: The main class that drives the application.

## Database Schema

- **Doctor Table**: 
  - `id`: INT, Primary Key
  - `name`: VARCHAR(255)
  - `specialization`: VARCHAR(255)

- **Patient Table**:
  - `id`: INT, Primary Key
  - `name`: VARCHAR(255)
  - `age`: INT
  - `gender`: VARCHAR(50)

- **Appointments Table**:
  - `id`: INT, Primary Key
  - `patient_id`: INT, Foreign Key references `Patient(id)`
  - `doctor_id`: INT, Foreign Key references `Doctor(id)`
  - `appointment_date`: DATE

## Setup and Usage

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/HospitalManagementSystem.git
   cd HospitalManagementSystem

<h3>3. Car Rental System</h3>

