package com.scc;

import com.scc.dao.UserDao;
import com.scc.model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();

        System.out.println("==================================================");
        System.out.println(" Hat Collection Display and Store Management System");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n[1] Register");
            System.out.println("[2] Login");
            System.out.println("[3] Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- REGISTRATION ---");
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();

                    System.out.println("\nSelect User Role:");
                    System.out.println("[1] Customer / Collector");
                    System.out.println("[2] Store Clerk / Sales Staff");
                    System.out.println("[3] Store Administrator / Manager");
                    System.out.print("Choose role (1-3): ");
                    int roleChoice = scanner.nextInt();
                    scanner.nextLine();

                    String role;
                    switch (roleChoice) {
                        case 1: role = "Customer / Collector"; break;
                        case 2: role = "Store Clerk / Sales Staff"; break;
                        case 3: role = "Store Administrator / Manager"; break;
                        default:
                            System.out.println("Invalid role choice. Defaulting to Customer / Collector.");
                            role = "Customer / Collector";
                    }

                    User newUser = new User(regUsername, regPassword, role);
                    if (userDao.registerUser(newUser)) {
                        System.out.println("Registration successful! You can now log in.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    User loggedInUser = userDao.loginUser(loginUsername, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("\nLogin Successful! Welcome, " + loggedInUser.getUsername() + "!");
                        System.out.println("Access Level / Role: " + loggedInUser.getRole());
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}