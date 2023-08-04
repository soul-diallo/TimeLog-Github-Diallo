package user_interface;

import backend.Admin;
import backend.TimeLogSystem;

import java.util.Scanner;

public class AdminUI {
    private TimeLogSystem timeLogSystem;
    private Admin admin;

    public AdminUI (TimeLogSystem timeLogSystem, Admin admin) {
        this.timeLogSystem = timeLogSystem;
        this.admin = admin;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running){
            System.out.println("=== Admin Interface ===");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Ajouter un projet");
            System.out.println("3. Assigner un projet à un employé");
            System.out.println("4. Voir Status Projet");
            System.out.println("5. Voir Rapport Salaire Employé");
            System.out.println("6. Logout");
            System.out.println("Choose an option: ");
        }
    }
}
