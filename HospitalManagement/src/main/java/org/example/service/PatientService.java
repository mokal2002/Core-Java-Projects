package org.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientService {

    private final Connection connection;
    private final Scanner scanner;

    public PatientService(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        scanner.nextLine();
        System.out.println("===================== Add Patient =====================");
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        int age = 0;
        boolean validAge = false;

        while (!validAge) {
            System.out.print("Enter Patient Age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                validAge = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for age.");
                scanner.next(); // Consume the invalid input
            }
        }

        scanner.nextLine();

        System.out.print("Enter Patient Gender: ");
        String gender = scanner.nextLine();
        System.out.println("=======================================================");

        try {
            String query = "INSERT INTO patient(name, age, gender) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("===============================================");

                System.out.println("Patient Added Successfully.");
                System.out.println("===============================================");

            } else {
                System.out.println("???????????????????????????????????????????????");

                System.out.println("Failed to Add Patient");
                System.out.println("???????????????????????????????????????????????");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id) {
        String query = "select *from patient where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void viewPatient() {
        String query = "SELECT * FROM patient";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("========================= Patients ==========================");
            System.out.println("+------------+------------------------+-----------+---------+");
            System.out.println("| Patient ID | Name                   | Age       | Gender  |");
            System.out.println("+------------+------------------------+-----------+---------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10d | %-22s | %-9d | %-7s |\n", id, name, age, gender);
                System.out.println("+------------+------------------------+-----------+---------+");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching patients: " + e.getMessage());
        }
    }

    public void bookAppointment(PatientService patient, DoctorService doctor, Connection connection, Scanner scanner) {
        System.out.println("==================== Book Appointment ====================");
        System.out.print("Enter Patient ID: ");
        int patientID = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorID = scanner.nextInt();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        System.out.println("==========================================================");

        if (patient.getPatientById(patientID) && doctor.getDoctorById(doctorID)) {
            if (checkDoctorAvailability(doctorID, appointmentDate, connection)) {
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery)) {
                    preparedStatement.setInt(1, patientID);
                    preparedStatement.setInt(2, doctorID);
                    preparedStatement.setString(3, appointmentDate);
                    int rowAffected = preparedStatement.executeUpdate();
                    if (rowAffected > 0) {
                        System.out.println("==========================================================");

                        System.out.println("Appointment Booked");
                        System.out.println("==========================================================");

                    } else {
                        System.out.println("==========================================================");

                        System.out.println("Failed to Book Appointment!");
                        System.out.println("==========================================================");

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("==========================================================");

                System.out.println("Doctor is not available on this date.");
                System.out.println("==========================================================");

            }
        } else {
            System.out.println("==========================================================");

            System.out.println("Invalid Patient or Doctor ID.");
            System.out.println("==========================================================");

        }
    }

    public boolean checkDoctorAvailability(int doctorID, String appointmentDate, Connection connection) {
        String query = "select COUNT(*) from appointments where doctor_id = ? and appointment_date = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorID);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
