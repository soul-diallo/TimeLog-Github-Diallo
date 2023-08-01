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

    public void addEmployee(Employe employe) {
        employees.add(employe);
    }

    public void addProject(Projet project) {
        projects.add(project);
    }

    public List<Projet> getProjects() {
        return projects;
    }
    private List<Employe> employees;
    private List<Projet> projects;
    private List<Assignation> assignments;

    public void assignProject(Employe employe, Projet project, String discipline, Date startTime) {
        // Vérifier si l'employé existe et si le projet est valide, puis créer l'assignation
        Assignation assignment = new Assignation(employe, project, discipline, startTime, null);
        assignments.add(assignment);
    }
}
