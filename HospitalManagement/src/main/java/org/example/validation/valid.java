package org.example.validation;

import java.util.Scanner;

public class valid {

    public int getValidatedChoice(Scanner scanner) {
        int choice = -1;
        boolean valid = false;
        while (!valid) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }
}
