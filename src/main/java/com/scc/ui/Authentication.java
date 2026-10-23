package com.scc.ui;

import com.scc.dao.UserDao;
import com.scc.model.User;

import java.util.Scanner;

public class Authentication {
    private final UserDao userDao;
    private final Scanner scanner;

    public Authentication(UserDao userDao, Scanner scanner) {
        this.userDao = userDao;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("==================================================");
        System.out.println(" Hat Collection Display and Store Management System");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n[1] Register");
            System.out.println("[2] Login");
            System.out.println("[3] Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: handleRegistration(); break;
                case 2: handleLogin(); break;
                case 3: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void handleRegistration() {
        System.out.println("\n--- REGISTRATION ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("\nSelect User Role:");
        System.out.println("[1] Customer / Collector");
        System.out.println("[2] Store Clerk / Sales Staff");
        System.out.println("[3] Store Administrator / Manager");
        System.out.print("Choose role (1-3): ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        String role = switch (roleChoice) {
            case 1 -> "Customer / Collector";
            case 2 -> "Store Clerk / Sales Staff";
            case 3 -> "Store Administrator / Manager";
            default -> "Customer / Collector";
        };

        User newUser = new User(username, password, role);
        if (userDao.registerUser(newUser)) {
            System.out.println("Registration successful! Account status is PENDING.");
        }
    }

    private void handleLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDao.loginUser(username, password);
        if (user != null) {
            boolean isManager = "Store Administrator / Manager".equalsIgnoreCase(user.getRole());

            if (!isManager && "PENDING".equalsIgnoreCase(user.getStatus())) {
                System.out.println("\nLogin Denied: Your account is pending administrator approval.");
                return;
            }

            System.out.println("\nLogin Successful! Welcome, " + user.getUsername() + "!");
            routeUserByRole(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void routeUserByRole(User user) {
        switch (user.getRole()) {
            case "Customer / Collector" -> new CustomerMenu(scanner, user).displayMenu();
            case "Store Clerk / Sales Staff" -> new ClerkMenu(scanner, user).displayMenu();
            case "Store Administrator / Manager" -> new AdminMenu(userDao, scanner, user).displayMenu();
            default -> System.out.println("Unknown role.");
        }
    }
}