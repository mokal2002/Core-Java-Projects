package org.example;

import org.example.config.AppConstants;
import org.example.service.DoctorService;
import org.example.service.PatientService;
import org.example.validation.valid;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = AppConstants.DB_URL; // database url
    private static final String username = AppConstants.DB_USER; // database username
    private static final String password = AppConstants.DB_PASSWORD; // database password

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PatientService patient = new PatientService(connection, scanner);
            DoctorService doctor = new DoctorService(connection);
            valid v = new valid();

            while (true) {
                System.out.println("###############################################");
                System.out.println("========== HOSPITAL MANAGEMENT SYSTEM =========");
                System.out.println("             1. Add Patient");
                System.out.println("             2. View Patient");
                System.out.println("             3. View Doctor");
                System.out.println("             4. Book Appointment");
                System.out.println("             5. Exit");
                System.out.println("###############################################");
                System.out.println();
                System.out.println("===============================================");
                System.out.print("Enter Your Choice: ");

                int choice = v.getValidatedChoice(scanner);
                System.out.println("===============================================");

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        patient.viewPatient();
                        System.out.println();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        patient.bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("===============================================");
                        System.out.println("Thanks for using our services.");
                        System.out.println("Exiting the Program...");
                        System.out.println("===============================================");

                        return;
                    default:
                        System.out.println("===============================================");

                        System.out.println("Enter Valid Field.");
                        System.out.println("===============================================");

                        break;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

}
