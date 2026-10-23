package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class CustomerMenu {
    private final Scanner scanner;
    private final User customerUser;

    public CustomerMenu(Scanner scanner, User customerUser) {
        this.scanner = scanner;
        this.customerUser = customerUser;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n==================================================");
            System.out.println("      CUSTOMER / COLLECTOR DASHBOARD - " + customerUser.getUsername());
            System.out.println("==================================================");
            System.out.println("[1] View Hat Collection Catalog");
            System.out.println("[2] View My Hat Wishlist");
            System.out.println("[3] Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: System.out.println("\n[Catalog feature]"); break;
                case 2: System.out.println("\n[Wishlist feature]"); break;
                case 3: System.out.println("Logging out..."); return;
                default: System.out.println("Invalid option.");
            }
        }
    }
}