package user_interface;

import backend.Employe;
import backend.TimeLogSystem;

import java.util.Scanner;

public class UserUI {
    private TimeLogSystem timeLogSystem;
    private Employe employe;

    public UserUI(TimeLogSystem timeLogSystem, Employe user) {
        this.timeLogSystem = timeLogSystem;
        this.employe = user;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== User Interface ===");
            System.out.println("1. Voir Status Projet");
            System.out.println("2. Voir Rapport Salaire Employé");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1 -> viewProjectStatus();
                case 2 -> viewEmployeeSalaryReport();
                case 3 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Méthodes spécifiques à l'interface utilisateur normal
    private void viewProjectStatus() {
        // ...
    }

    private void viewEmployeeSalaryReport() {
        // ...
    }
}

