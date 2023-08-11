package utils;

import backend.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static utils.JsonUtils.gson;


public class MenuUtils {
    public static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void displayAdminMenu(TimeLogSystem timeLogSystem, Scanner scanner) {
        while (true) {
            System.out.println("****** Menu Administrateur ******");
            System.out.println("1. Afficher la liste des projets");
            System.out.println("2. Afficher la liste des employés");
            System.out.println("3. Ajouter un employé");
            System.out.println("4. Ajouter un projet");
            System.out.println("5. Assigner un projet à un employé");
            System.out.println("6. Générer un rapport des salaires depuis une date");
            System.out.println("7. Générer le talon de paie pour la dernière période de paie");
            System.out.println("8. Générer le total des salaires");
            System.out.println("9. Quitter");

            int choice = getUserChoice();

            switch (choice) {
                case 1 -> displayProjects(timeLogSystem);
                case 2 -> displayEmployees(timeLogSystem);
                case 3 -> addEmployee(timeLogSystem, scanner);
                case 4 -> addProject(timeLogSystem, scanner);
                case 5 -> assignProjectToEmployee(timeLogSystem, scanner);
                case 6 -> generateSalaryReportSinceDate(timeLogSystem);
                case 7 -> generateLastPaycheckReport(timeLogSystem);
                case 8 -> generateTotalSalariesReport(timeLogSystem);

                case 9 -> {
                    return; // Quitter le menu
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void generateProjectStatusReport(TimeLogSystem timeLogSystem) {
        System.out.println("****** Rapport d'état des projets ******");
        for (Project project : timeLogSystem.getProjects()) {
            System.out.println("Projet : " + project.getName());
            System.out.println("Identifiant : " + project.getIdentificationNumber());
            System.out.println("État d'avancement :");
            for (String discipline : project.getBudgetedHours().keySet()) {
                double hoursWorked = project.calculateTotalHoursForDiscipline(discipline);
                double budgetedHours = project.getBudgetedHoursForDiscipline(discipline);
                double percentageComplete = (hoursWorked / budgetedHours) * 100;
                System.out.println("  Discipline : " + discipline);
                System.out.println("  Pourcentage d'avancement : " + percentageComplete + "%");
            }
            System.out.println();
        }
    }


    private static void assignProjectToEmployee(TimeLogSystem timeLogSystem, Scanner scanner) {
        displayEmployees(timeLogSystem);

        System.out.print("Entrez l'ID de l'employé : ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne vide

        displayProjects(timeLogSystem);

        System.out.print("Entrez l'ID du projet : ");
        int projectId = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne vide

        timeLogSystem.assignEmployeeToProject(timeLogSystem.findEmployeeById(String.valueOf(employeeId)), timeLogSystem.findProjectById(projectId));
        System.out.println("Le projet a été assigné à l'employé avec succès.");

        // Sauvegarder dans le fichier JSON
        JsonUtils.saveToJson(timeLogSystem, TimeLogSystem.JSON_FILENAME);
    }

    private static void addProject(TimeLogSystem timeLogSystem, Scanner scanner) {
        System.out.print("Entrez le nom du projet : ");
        String projectName = scanner.nextLine();

        // Demander les heures budgétées pour chaque discipline
        Map<String, Double> budgetedHours = new HashMap<>();
        for (String discipline : DisciplineManager.getAllDisciplines()) {
            System.out.print("Entrez le nombre d'heures budgétées pour la discipline '" + discipline + "' : ");
            double hours = scanner.nextDouble();
            budgetedHours.put(discipline, hours);
        }
        System.out.println("Entrer l'id du projet: ");
        int id = scanner.nextInt();

        // Créer le projet et l'ajouter au système
        Project project = new Project(new Date(), null, budgetedHours, projectName, id);
        timeLogSystem.addProject(project);

        System.out.println("Le projet a été ajouté avec succès.");

        // Sauvegarder dans le fichier JSON
        JsonUtils.saveToJson(timeLogSystem, TimeLogSystem.JSON_FILENAME);
    }

    private static void addEmployee(TimeLogSystem timeLogSystem, Scanner scanner) {


        System.out.print("Entrez le nom d'utilisateur : ");
        String username = scanner.nextLine();

        System.out.print("Entrez le mot de passe : ");
        String password = scanner.nextLine();

        System.out.print("Entrez le taux horaire de base : ");
        double baseHourlyRate = scanner.nextDouble();

        System.out.print("Entrez le taux horaire supplémentaire : ");
        double overtimeHourlyRate = scanner.nextDouble();



        // Créer l'employé et l'ajouter au système
        Employee employee = new Employee(username, password, baseHourlyRate, overtimeHourlyRate);
        timeLogSystem.addEmployee(employee);

        System.out.println("L'employé a été ajouté avec succès.");

        // Sauvegarder dans le fichier JSON
        JsonUtils.saveToJson(timeLogSystem, TimeLogSystem.JSON_FILENAME);
    }

    public static void displayUserMenu(TimeLogSystem timeLogSystem, Scanner scanner) {
        Employee loggedInUser = timeLogSystem.getLoggedInUser();

        while (true) {
            System.out.println("****** Menu Employé - Bonjour, " + loggedInUser.getUsername() + "! ******");
            System.out.println("1. Afficher la liste des projets assignés");
            System.out.println("2. Enregistrer une entrée de temps");
            System.out.println("3. Générer un rapport des salaires depuis une date");
            System.out.println("4. Signaler le début d'une activité");
            System.out.println("5. Signaler la fin d'une activité");
            System.out.println("6. Afficher le nombre d'heures travaillées");
            System.out.println("7. Quitter");

            int choice = getUserChoice();

            switch (choice) {
                case 1 -> displayAssignedProjects(loggedInUser);
                case 2 -> promptForTimeEntry(loggedInUser,timeLogSystem,scanner);
                case 3 -> generateSalaryReportSinceDateForUser(loggedInUser, timeLogSystem, scanner);
                case 4 -> {
                    System.out.print("Entrez le nom du projet : ");
                    String projectName = scanner.nextLine();
                    System.out.print("Entrez la discipline : ");
                    String discipline = scanner.nextLine();
                    loggedInUser.startActivity(projectName, discipline, timeLogSystem, scanner);
                }
                case 5 -> loggedInUser.endActivity(timeLogSystem);
                case 6 -> displayWorkedHours(loggedInUser, scanner);
                case 7 -> {
                    return; // Quitter le menu
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void displayWorkedHours(Employee loggedInUser, Scanner scanner) {
        System.out.print("Entrez la date de début (au format yyyy-MM-dd HH:mm:ss) : ");
        String startDateStr = scanner.nextLine();
        Date startDate = DateUtils.parseDate(startDateStr, "yyyy-MM-dd HH:mm:ss");

        System.out.print("Entrez la date de fin (au format yyyy-MM-dd HH:mm:ss) : ");
        String endDateStr = scanner.nextLine();
        Date endDate = DateUtils.parseDate(endDateStr, "yyyy-MM-dd HH:mm:ss");

        double baseHours = loggedInUser.calculateBaseHoursForPeriod(startDate, endDate);
        double overtimeHours = loggedInUser.calculateOvertimeHoursForPeriod(startDate, endDate);

        System.out.println("Heures de base travaillées : " + baseHours);
        System.out.println("Heures supplémentaires travaillées : " + overtimeHours);
    }


    private static void generateSalaryReportSinceDateForUser(Employee loggedInUser, TimeLogSystem timeLogSystem, Scanner scanner) {
        System.out.println("Entrez la date à partir de laquelle vous souhaitez générer le rapport (jj/mm/aaaa) :");
        String dateString = scanner.nextLine();

        Date startDate = DateUtils.parseDate(dateString,"dd-mm-yyyy");
        double totalSalary = timeLogSystem.calculateEmployeeSalary(loggedInUser, startDate, new Date());

        System.out.println("Rapport des salaires depuis " + dateString + " pour l'employé " + loggedInUser.getUsername());
        System.out.println("Salaire brut total : " + totalSalary);
        System.out.println("Salaire net total : " + (totalSalary * 0.6)); // Simplification pour le salaire net

        // Affichage en JSON
        System.out.println("Rapport JSON :");
        System.out.println(JsonUtils.toJson(Map.of(
                "startDate", dateString,
                "employee", loggedInUser.getUsername(),
                "totalGrossSalary", totalSalary,
                "totalNetSalary", totalSalary * 0.6
        )));
    }


    private static void promptForTimeEntry(Employee loggedInUser, TimeLogSystem timeLogSystem, Scanner scanner) {
        scanner = new Scanner(System.in);

        System.out.println("Entrez l'identifiant du projet :");
        int projectId = Integer.parseInt(scanner.nextLine());

        Project project = timeLogSystem.findProjectById(projectId);

        if (project == null) {
            System.out.println("Projet introuvable avec l'identifiant : " + projectId);
            return;
        }

        System.out.println("Entrez la discipline :");
        String discipline = scanner.nextLine();

        System.out.println("Entrez le nombre d'heures travaillées :");
        double hoursWorked = Double.parseDouble(scanner.nextLine());

        // Ajouter l'entrée de temps
        TimeEntry timeEntry = new TimeEntry(loggedInUser,project, discipline, hoursWorked, new Date());
        timeLogSystem.addTimeEntry(timeEntry);

        System.out.println("Enregistrement de l'entrée de temps réussi.");
    }


    private static void displayAssignedProjects(Employee loggedInUser) {
        Set<Project> assignedProjects = loggedInUser.getAssignedProjects();

        if (assignedProjects.isEmpty()) {
            System.out.println("Aucun projet assigné pour l'employé " + loggedInUser.getUsername());
        } else {
            System.out.println("Projets assignés pour l'employé " + loggedInUser.getUsername() + ":");
            for (Project project : assignedProjects) {
                System.out.println("Projet : " + project.getName());
                System.out.println("Identifiant : " + project.getIdentificationNumber());
                System.out.println("Heures budgétées : " + project.getBudgetedHoursForDiscipline(loggedInUser.getPosition()));
                System.out.println();
            }
        }
    }


    private static void generateTotalSalariesReport(TimeLogSystem timeLogSystem) {
        double totalGrossSalaries = timeLogSystem.calculateTotalBasePay();
        double totalNetSalaries = timeLogSystem.calculateTotalOvertimePay();

        System.out.println("Rapport des totaux des salaires :");
        System.out.println("Total des salaires bruts : " + totalGrossSalaries);
        System.out.println("Total des salaires nets : " + totalNetSalaries);
    }

    private static void generateLastPaycheckReport(TimeLogSystem timeLogSystem) {
        Employee loggedInUser = timeLogSystem.getLoggedInUser();

        if (loggedInUser == null) {
            System.out.println("Aucun utilisateur connecté. Veuillez vous connecter en tant qu'employé.");
            return;
        }

        System.out.println("Talon de paie pour la dernière période de paie :");
        System.out.println("Salaire brut : " + timeLogSystem.getLoggedInUser().calculateLastPaycheck());
        System.out.println("Salaire net : " + timeLogSystem.getLoggedInUser().calculateLastPaycheck() * 0.6);
    }

    private static void generateSalaryReportSinceDate(TimeLogSystem timeLogSystem) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Veuillez entrer la date (au format yyyy-MM-dd HH:mm:ss) : ");
        String dateString = scanner.nextLine();
        Date startDate = DateUtils.parseDate(dateString, "yyyy-MM-dd HH:mm:ss");

        double salary = timeLogSystem.getLoggedInUser().calculateSalarySince(startDate, new Date());
        System.out.println("Rapport des valeurs en salaire depuis " + startDate + " :");
        System.out.println("Salaire brut : " + salary);
        System.out.println("Salaire net : " + salary * 0.6);
    }

    public static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Veuillez sélectionner une option : ");
        while (!scanner.hasNextInt()) {
            System.out.print("Option invalide. Veuillez saisir un nombre : ");
            scanner.next();
        }

        return scanner.nextInt();
    }

    public static void displayProjects(TimeLogSystem timeLogSystem) {
        List<Project> projects = timeLogSystem.getProjects();

        System.out.println("------ Liste des projets : -------");
        for (Project project : projects) {
            
            String projectJson = gson.toJson(project);
            System.out.println(projectJson);
        }
    }

    public static void displayEmployees(TimeLogSystem timeLogSystem) {
        List<Employee> employees = timeLogSystem.getEmployees();

        System.out.println("------ Liste des employés : -------");
        for (Employee employee : employees) {
            String employeeJson = gson.toJson(employee);
            System.out.println(employeeJson);
        }
    }


    public static String promptForUsername() {
        System.out.print("Nom d'utilisateur : ");
        return scanner.next();
    }

    public static String promptForPassword() {
        System.out.print("Mot de passe : ");
        return scanner.next();
    }

    public static String promptForProjectName() {
        System.out.print("Nom du projet : ");
        return scanner.next();
    }

    public static String promptForEmployeeID() {
        System.out.print("ID de l'employé : ");
        return scanner.next();
    }

    public static Date promptForStartDate() {
        System.out.print("Date de début (yyyy-MM-dd) : ");
        String input = scanner.next();
        try {
            return dateFormat.parse(input);
        } catch (ParseException e) {
            System.out.println("Format de date incorrect. Veuillez entrer la date au format yyyy-MM-dd.");
            return promptForStartDate();
        }
    }

    public static Date promptForEndDate() {
        System.out.print("Date de fin (yyyy-MM-dd) : ");
        String input = scanner.next();
        try {
            return dateFormat.parse(input);
        } catch (ParseException e) {
            System.out.println("Format de date incorrect. Veuillez entrer la date au format yyyy-MM-dd.");
            return promptForEndDate();
        }
    }

    public static String promptForDiscipline() {
        System.out.print("Discipline : ");
        return scanner.next();
    }

    public static double promptForHoursWorked() {
        System.out.print("Heures travaillées : ");
        return scanner.nextDouble();
    }


    public static void displayProjectStatusReport(Project project) {
        System.out.println("=== Rapport d'état du Projet ===");
        System.out.println("Projet : " + project.getName());
        System.out.println("Identifiant : " + project.getIdentificationNumber());
        System.out.println("Date de début : " + project.getStartDate());
        System.out.println("Date de fin : " + project.getEndDate());
        System.out.println("----------------------------");

        for (Map.Entry<String, Double> entry : project.getActualHoursWorked().entrySet()) {
            String discipline = entry.getKey();
            double hoursWorked = entry.getValue();

            System.out.println("Discipline : " + discipline);
            System.out.println("Heures travaillées : " + hoursWorked);
            System.out.println("Heures budgétées : " + project.getBudgetedHours().getOrDefault(discipline, 0.0));
            System.out.println("Pourcentage d'avancement : " +
                    calculatePercentageCompletion(hoursWorked, project.getBudgetedHours().getOrDefault(discipline, 0.0)));
            System.out.println("----------------------------");
        }

        System.out.println("============================");
    }

    public static void displayGlobalProjectStatusReport(List<Project> projects) {
        System.out.println("=== Rapport d'état global des Projets ===");

        for (Project project : projects) {
            displayProjectStatusReport(project);
        }

        System.out.println("============================");
    }

    public static void displayTotalSalariesReport(double totalBasePay, double totalOvertimePay) {
        System.out.println("=== Rapport des Salaires Totaux ===");
        System.out.println("Total Salaire de Base : " + totalBasePay);
        System.out.println("Total Salaire supplémentaire : " + totalOvertimePay);
        System.out.println("Total Salaire Brut : " + (totalBasePay + totalOvertimePay));
        System.out.println("Salaire Net : " + (totalBasePay + totalOvertimePay) * 0.6);
        System.out.println("============================");
    }

    public static void displayAssignmentConfirmation(Employee employee, Project project) {
        System.out.println("L'employé " + employee.getUsername() + " a été assigné au projet " + project.getName() + ".");
    }

    public static void displayMaxProjectsExceededMessage(int maxProjects) {
        System.out.println("L'employé ne peut pas être assigné à plus de " +
                maxProjects + " projets.");
    }

    private static double calculatePercentageCompletion(double actualHours, double budgetedHours) {
        return (actualHours / budgetedHours) * 100;
    }

}

