package backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.packtpub.db.Main.scanner;

public class TimeLogSystem {
    private static List<Employee> employees;
    private List<Project> projects;
    private AssignmentManager assignmentManager;
    private String adminUsername;
    private String adminPassword;
    private static final int maxProjectsPerEmployee = 2; // Default value for NPE (Number of Projects per Employee)

    private Employee loggedInUser;

    public static final String JSON_FILENAME = "timeLogSystem.json";

    public void setLoggedInUser(Employee loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public TimeLogSystem(Employee loggedInUser, String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        employees = new ArrayList<>();
        projects = new ArrayList<>();
        assignmentManager = new AssignmentManager();
        this.loggedInUser = loggedInUser;

        // Charger les donnees depuis le fichier JSON s'il existe
        loadFromJson(JSON_FILENAME);
    }

    // Méthode pour sauvegarder les données dans un fichier JSON
    private void saveToJson(String filename) {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour charger les données depuis un fichier JSON
    private void loadFromJson(String filename) {
        try (Reader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            TimeLogSystem loadedTimeLogSystem = gson.fromJson(reader, TimeLogSystem.class);

            // Mettre à jour les données actuelles
            employees = employees;
            this.projects = loadedTimeLogSystem.projects;
            this.assignmentManager = loadedTimeLogSystem.assignmentManager;
        } catch (IOException e) {
            // Le fichier JSON n'existe pas encore ou ne peut pas être lu, c'est normal
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        TimeLogSystem.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public AssignmentManager getAssignmentManager() {
        return assignmentManager;
    }

    public void setAssignmentManager(AssignmentManager assignmentManager) {
        this.assignmentManager = assignmentManager;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    // Methods to manage employees and projects

    public void assignEmployeeToProject(Employee employee, Project project) {
        assignmentManager.assignEmployeeToProject(employee, project);
    }

    public List<Project> getEmployeeAssignments(Employee employee) {
        return assignmentManager.getEmployeeAssignments(employee);
    }

    // Other methods for TimeLogSystem functionalities



    public static int getMaxProjectsPerEmployee() {
        return maxProjectsPerEmployee;
    }

    public boolean authenticateAdmin(String username, String password) {
        // Vérifie l'authentification de l'administrateur
        return adminUsername.equals(username) && adminPassword.equals(password);
    }

    public boolean authenticateUser(String username, String password) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Employee getEmployeeByUsername(String username) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                return employee;
            }
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveToJson(JSON_FILENAME);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void updateEmployee(Employee employee) {
        // Recherche l'employé dans la liste et met à jour ses informations si trouvé
        Employee existingEmployee = findEmployeeById(employee.getId());
        if (existingEmployee != null) {
            existingEmployee.setUsername(employee.getUsername());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setBaseHourlyRate(employee.getBaseHourlyRate());
            existingEmployee.setOvertimeHourlyRate(employee.getOvertimeHourlyRate());

        }
    }

    public void addProject(Project project) {
        projects.add(project);
        saveToJson(JSON_FILENAME);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public void updateProject(Project project) {
        // Recherche le projet dans la liste et met à jour ses informations si trouvé
        Project existingProject = findProjectById(project.getId());
        if (existingProject != null) {
            existingProject.setName(project.getName());
            existingProject.setStartDate(project.getStartDate());
            existingProject.setEndDate(project.getEndDate());
            existingProject.setBudgetedHours(project.getBudgetedHours());

        }
    }

    public Employee findEmployeeById(String id) {
        for (Employee employee : employees) {
            if (Objects.equals(employee.getId(), id)) {
                return employee;
            }
        }
        return null;
    }

    public Project findProjectById(int id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public List<Project> getProjectsForEmployee(Employee employee) {
        List<Project> assignedProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getAssignedEmployees().contains(employee)) {
                assignedProjects.add(project);
            }
        }
        return assignedProjects;
    }

    public double calculateEmployeeSalary(Employee employee, Date startDate, Date endDate) {
        double totalSalary = 0.0;
        for (TimeEntry timeEntry : employee.getTimeEntries()) {
            if (timeEntry.getDate().compareTo(startDate) >= 0 && timeEntry.getDate().compareTo(endDate) <= 0) {
                totalSalary += calculateTimeEntrySalary(timeEntry, employee);
            }
        }
        return totalSalary;
    }

    private double calculateTimeEntrySalary(TimeEntry timeEntry, Employee employee) {
        double basePay = timeEntry.getHoursWorked() * employee.getBaseHourlyRate();
        double overtimePay = (timeEntry.getHoursWorked() - employee.getStandardHoursPerWeek()) * employee.getOvertimeHourlyRate();
        return basePay + overtimePay;
    }

    public double calculateTotalBasePay() {
        double totalBasePay = 0.0;
        for (Employee employee : employees) {
            totalBasePay += employee.getBaseHourlyRate() * employee.getTotalRegularHoursWorked();
        }
        return totalBasePay;
    }

    public double calculateTotalOvertimePay() {
        double totalOvertimePay = 0.0;
        for (Employee employee : employees) {
            totalOvertimePay += employee.getOvertimeHourlyRate() * employee.getTotalOvertimeHoursWorked();
        }
        return totalOvertimePay;
    }

    public void addTimeEntry(TimeEntry timeEntry) {
        assignmentManager.addTimeEntry(timeEntry);
    }

    public Employee getLoggedInUser() {
        return loggedInUser;
    }

    public Project getAssignedProjectByName(String projectName) {
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null; // Aucun projet trouvé avec le nom donné
    }

    public static Employee authenticateUser() {
        System.out.println("Authentification de l'utilisateur :");
        System.out.print("Nom d'utilisateur : ");
        String username = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        // Vérifier l'authentification de l'utilisateur

        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return employee; // Retourner l'employé authentifié
            }
        }

        System.out.println("Authentification échouée pour l'utilisateur.");
        return null;
    }

    public static Employee authenticateAdmin() {
        System.out.println("Authentification de l'administrateur :");
        System.out.print("Nom d'utilisateur : ");
        String username = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        // Vérifier l'authentification de l'administrateur
        if (username.equals("admin") && password.equals("admin")) {
            return new Employee(username, "adminID", 0.0, 0.0, new Date(), null, "", "Admin", password); // Retourner l'administrateur authentifié
        }

        System.out.println("Authentification échouée pour l'administrateur.");
        return null;
    }
}

