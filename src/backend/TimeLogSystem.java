package backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeLogSystem {

    public TimeLogSystem() {
        employees = new ArrayList<>();
        projects = new ArrayList<>();
        assignments = new ArrayList<>();
    }
    public List<Projet> getProjects() {
        return projects;
    }
    public static List<Employe> employees;
    public static List<Projet> projects;
    public static List<Assignation> assignments;

    public void addEmployee(Employe employe) {
        employees.add(employe);
    }
    public void updateEmployee(Employe employe) {
        // Vérifier si l'employé existe, puis mettre à jour ses informations
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getID() == employe.getID()) {
                employees.set(i, employe);
                break;
            }
        }
    }
    public void deleteEmployee(int employeeID) {
        // Vérifier si l'employé existe, puis le supprimer de la liste
        employees.removeIf(employee -> employee.getID() == employeeID);
    }

    public void addProject(Projet project) {
        projects.add(project);
    }
    public void updateProject(Projet projet) {
        // Vérifier si le projet existe, puis mettre à jour ses informations
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getID() == projet.getID()) {
                projects.set(i, projet);
                break;
            }
        }
    }
    public void deleteProject(int projectID) {
        // Vérifier si le projet existe, puis le supprimer de la liste
        projects.removeIf(project -> project.getID() == projectID);
    }

    public void assignProject(Employe employe, Projet project, String discipline, Date startTime) {
        // Vérifier si l'employé existe et si le projet est valide, puis créer l'assignation
        Assignation assignment = new Assignation(employe, project, discipline, startTime, null);
        assignments.add(assignment);
    }

    public String generateProjectStatusReport() {
        StringBuilder report = new StringBuilder();
        for (Projet project : projects) {
            report.append("Project: ").append(project.getName()).append("\n");

            double totalWorkedHours = getTotalWorkedHoursForProject(project);
            report.append("Total Worked Hours: ").append(totalWorkedHours).append("\n");

            double totalBudget = project.getTotalBudget();
            double completionPercentage = (totalWorkedHours / totalBudget) * 100;
            report.append("Completion Percentage: ").append(completionPercentage).append("%").append("\n");

            // ... Add more project status details if needed

            report.append("\n");
        }
        return report.toString();
    }

    public String generateEmployeeSalaryReport(int employeeID) {
        Employe employee = findEmployeeByID(employeeID);
        if (employee == null) {
            return "Employee not found.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Employee: ").append(employee.getName()).append("\n");

        // Calculate and add employee salary details (replace with your calculations)
        double totalWorkedHours = getTotalWorkedHoursForEmployee(employee);
        double grossSalary = employee.calculateGrossSalary(totalWorkedHours);
        double netSalary = employee.calculateNetSalary(grossSalary);

        report.append("Total Worked Hours: ").append(totalWorkedHours).append("\n");
        report.append("Gross Salary: ").append(grossSalary).append("\n");
        report.append("Net Salary: ").append(netSalary).append("\n");

        // ... Add more employee salary details if needed

        return report.toString();
    }

    private double getTotalWorkedHoursForProject(Projet project) {
        // Calculate the total worked hours for the project by summing up the worked hours for each assignment
        double totalWorkedHours = 0;
        for (Assignment assignment : assignments) {
            if (assignment.getProject().equals(project)) {
                totalWorkedHours += assignment.getWorkedHours();
            }
        }
        return totalWorkedHours;
    }

    private double getTotalWorkedHoursForEmployee(Employee employee) {
        // Calculate the total worked hours for the employee by summing up the worked hours for each assignment
        double totalWorkedHours = 0;
        for (Assignment assignment : assignments) {
            if (assignment.getEmployee().equals(employee)) {
                totalWorkedHours += assignment.getWorkedHours();
            }
        }
        return totalWorkedHours;
    }


}
