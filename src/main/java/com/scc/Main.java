package com.scc;

import com.scc.dao.DatabaseInitializer;
import com.scc.dao.UserDao;
import com.scc.ui.Authentication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the SQLite database and tables
        DatabaseInitializer.initializeDatabase();

        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);

        Authentication auth = new Authentication(userDao, scanner);
        auth.start();
    }
}