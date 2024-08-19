package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Car;
import entities.Customer;
import entities.Rental;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));

        } else {
            System.out.println("---------------------------------");
            System.out.println("Car is not available for rent.");
            System.out.println("---------------------------------");

        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);

        } else {
            System.out.println("---------------------------------");
            System.out.println("Car was not rented.");
            System.out.println("---------------------------------");

        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=============================");
            System.out.println("===== Car Rental System =====");
            System.out.println("1.      Rent a Car");
            System.out.println("2.      Return a Car");
            System.out.println("3.      Exit");
            System.out.println("=============================");
            System.out.println("-----------------------------");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            System.out.println("-----------------------------");

            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("============================");

                System.out.println("======== Rent a Car ========");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();
                System.out.println("============================");

                System.out.println("============================");

                System.out.println("====== Available Cars ======");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println("    " + car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }
                System.out.println("============================");

                System.out.println("---------------------------------------");
                System.out.print("Enter the car ID you want to rent: ");
                String carId = scanner.nextLine();

                System.out.print("Enter the number of days for rental: ");

                int rentalDays = scanner.nextInt();
                System.out.println("---------------------------------------");

                scanner.nextLine(); // Consume newline

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("======================================");
                    System.out.println("========= Rental Information =========");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);
                    System.out.println("======================================");

                    System.out.println("---------------------------------------");

                    System.out.print("Confirm rental (Y/N): ");
                    String confirm = scanner.nextLine();
                    System.out.println("---------------------------------------");

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("---------------------------------------");
                        System.out.println("Car rented successfully.");
                        System.out.println("---------------------------------------");

                    } else {
                        System.out.println("---------------------------------------");
                        System.out.println("Rental canceled.");
                        System.out.println("---------------------------------------");

                    }
                } else {
                    System.out.println("------------------------------------------------------");

                    System.out.println("Invalid car selection or car not available for rent.");
                    System.out.println("------------------------------------------------------");

                }
            } else if (choice == 2) {
                System.out.println("============================================");
                System.out.println("================ Return a Car ==============");
                System.out.print("Enter the car ID you want to return: ");
                String carId = scanner.nextLine();
                System.out.println("============================================");


                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("=======================================");
                        System.out.println("Car returned successfully by " + customer.getName());
                        System.out.println("=======================================");

                    } else {
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Car was not rented or rental information is missing.");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                    }
                } else {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                    System.out.println("Invalid car ID or car is not rented.");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                System.out.println("Invalid choice. Please enter a valid option.");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            }
        }

        System.out.println("##########################################");
        System.out.println("Thank you for using the Car Rental System!");
        System.out.println("##########################################");

        scanner.close();
    }

}