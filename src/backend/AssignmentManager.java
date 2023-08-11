package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentManager {
    private final Map<Employee, List<Project>> employeeAssignments = new HashMap<>();

    public void assignEmployeeToProject(Employee employee, Project project) {
        if (!employeeAssignments.containsKey(employee)) {
            employeeAssignments.put(employee, new ArrayList<>());
        }

        if (employeeAssignments.get(employee).size() < TimeLogSystem.getMaxProjectsPerEmployee()) {
            employeeAssignments.get(employee).add(project);
            System.out.println("Employé " + employee.getUsername() + " assigné au projet " + project.getName());
        } else {
            System.out.println("L'employé ne peut pas être assigné à plus de " +
                    TimeLogSystem.getMaxProjectsPerEmployee() + " projets.");
        }
    }

    public void addTimeEntry(TimeEntry timeEntry) {
        // Logique d'ajout d'entrée de temps
        Employee employee = timeEntry.getEmployee();
        Project project = timeEntry.getProject();

        if (employeeAssignments.containsKey(employee) && employeeAssignments.get(employee).contains(project)) {
            // Ajouter l'entrée de temps à l'employé et au projet
            employee.addTimeEntry(timeEntry);
            project.addTimeEntry(timeEntry);
        } else {
            System.out.println("L'employé n'est pas assigné à ce projet.");
        }
    }

    public List<Project> getEmployeeAssignments(Employee employee) {
        return employeeAssignments.getOrDefault(employee, new ArrayList<>());
    }
}

