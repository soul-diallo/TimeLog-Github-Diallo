package UI;

import backend.Admin;
import backend.Employe;
import backend.TimeLogSystem;

import java.util.Scanner;

public class LoginUI {
    private TimeLogSystem timeLogSystem;
    private Admin admin;
    private Employe employe;

    public LoginUI(TimeLogSystem timeLogSystem, Admin admin, Employe employe) {
        this.timeLogSystem = timeLogSystem;
        this.admin = admin;
        this.employe = employe;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== TimeLog System ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1 -> {
                    if (login()) {
                        System.out.println("Login successful!");
                        if (admin != null) {
                            AdminUI adminInterface = new AdminUI(timeLogSystem, admin);
                            adminInterface.start();
                        } else if (employe != null) {
                            UserUI userInterface = new UserUI(timeLogSystem, employe);
                            userInterface.start();
                        } else {
                            System.out.println("Invalid user role.");
                        }
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                }
                case 2 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Vérifier les informations de login et retourner true si valide, sinon false
        if (admin != null && username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            return true;
        } else return employe != null && username.equals(employe.getUsername()) && password.equals(employe.getPassword());
    }
}

