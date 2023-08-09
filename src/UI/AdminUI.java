package UI;

import backend.Admin;
import backend.Employe;
import backend.Projet;
import backend.TimeLogSystem;

import java.util.Date;
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

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1 -> addEmployee();
                case 2 -> addProject();
                case 3 -> assignProject();
                case 4 -> viewProjectStatus();
                case 5 -> viewEmployeeSalaryReport();
                case 6 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entre l'id");
        int id = scanner.nextInt();

        System.out.print("Entrer l'username: ");
        String username = scanner.nextLine();

        System.out.print("Entrer le password: ");
        String password = scanner.nextLine();

        System.out.print("Entrer le hourlyRate: ");
        double hourlyRate = scanner.nextDouble();

        System.out.print("Entrer le overtimeRate: ");
        double overtimeRate = scanner.nextDouble();

        System.out.print("Entrer le hireDate: ");
        String hireDate = scanner.nextLine();

        System.out.print("Entrer le socialSecurity: ");
        String socialSecurity = scanner.nextLine();

        System.out.print("Entrer le poste: ");
        String poste = scanner.nextLine();

        Employe newEmploye = new Employe(id,username,password,hourlyRate,overtimeRate,hireDate,socialSecurity,poste);
        timeLogSystem.addEmployee(newEmploye);
        System.out.println("Employee creer successivement");
    }

    private void addProject() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrer l'id du projet: ");
        int id = scanner.nextInt();
        System.out.println();

        System.out.print("Entrer le nom du projet: ");
        String name = scanner.nextLine();
        System.out.println();

        System.out.print("Entrer la date de debut du projet: ");
        String startDate = scanner.nextLine();
        System.out.println();

        System.out.print("Entrer la date de fin du projet: ");
        String endDate = scanner.nextLine();
        System.out.println();

        System.out.print("Entrer le budget du projet: ");
        double budget = scanner.nextDouble();
        System.out.println();

        Projet newProjet = new Projet(id,name,startDate,endDate,budget);
        timeLogSystem.addProject(newProjet);
        System.out.println(timeLogSystem.getProjects().get(0).getName());
        System.out.println("Projet creer avec succes");
    }

    private void assignProject() {

    }

    private void viewProjectStatus() {

    }

    private void viewEmployeeSalaryReport() {
    }

}
