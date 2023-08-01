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

}
