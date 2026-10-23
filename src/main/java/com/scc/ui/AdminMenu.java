package com.scc.ui;

import com.scc.dao.UserDao;
import com.scc.model.User;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final UserDao userDao;
    private final Scanner scanner;
    private final User adminUser;

    public AdminMenu(UserDao userDao, Scanner scanner, User adminUser) {
        this.userDao = userDao;
        this.scanner = scanner;
        this.adminUser = adminUser;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n==================================================");
            System.out.println("      ADMIN / MANAGER DASHBOARD - " + adminUser.getUsername());
            System.out.println("==================================================");
            System.out.println("[1] View Pending Account Requests");
            System.out.println("[2] Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handlePendingApprovals();
                    break;
                case 2:
                    System.out.println("Logging out from Admin Dashboard...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void handlePendingApprovals() {
        List<User> pendingUsers = userDao.getUsersByStatus("PENDING");

        if (pendingUsers.isEmpty()) {
            System.out.println("\nNo pending account requests found.");
            return;
        }

        System.out.println("\n--- PENDING USER ACCOUNTS ---");
        System.out.printf("%-5s | %-20s | %-25s\n", "ID", "Username", "Role");
        System.out.println("--------------------------------------------------");
        for (User u : pendingUsers) {
            System.out.printf("%-5d | %-20s | %-25s\n", u.getId(), u.getUsername(), u.getRole());
        }

        System.out.print("\nEnter User ID to APPROVE (0 to go back): ");
        int userIdToApprove = scanner.nextInt();
        scanner.nextLine();

        if (userIdToApprove == 0) return;

        boolean found = pendingUsers.stream().anyMatch(u -> u.getId() == userIdToApprove);
        if (found) {
            if (userDao.updateUserStatus(userIdToApprove, "APPROVED")) {
                System.out.println("Success! User ID " + userIdToApprove + " has been APPROVED.");
            }
        } else {
            System.out.println("Invalid User ID.");
        }
    }
}