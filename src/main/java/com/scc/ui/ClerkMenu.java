package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class ClerkMenu {
    private final Scanner scanner;
    private final User clerkUser;

    public ClerkMenu(Scanner scanner, User clerkUser) {
        this.scanner = scanner;
        this.clerkUser = clerkUser;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n==================================================");
            System.out.println("      STORE CLERK DASHBOARD - " + clerkUser.getUsername());
            System.out.println("==================================================");
            System.out.println("[1] Manage Hat Inventory");
            System.out.println("[2] Process Customer Sales");
            System.out.println("[3] Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: System.out.println("\n[Inventory Management]"); break;
                case 2: System.out.println("\n[Sales Processing]"); break;
                case 3: System.out.println("Logging out..."); return;
                default: System.out.println("Invalid option.");
            }
        }
    }
}