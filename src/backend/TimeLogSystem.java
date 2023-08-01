package backend;

import java.util.Date;
import java.util.List;

public class TimeLogSystem {



    public List<Employe> getEmployees() {
        return employees;
    }

    public List<Projet> getProjects() {
        return projects;
    }

    public List<Assignation> getAssignments() {
        return assignments;
    }

    public TimeLogSystem(List<Employe> employees, List<Projet> projects, List<Assignation> assignments) {
        this.employees = employees;
        this.projects = projects;
        this.assignments = assignments;
    }

    private List<Employe> employees;
    private List<Projet> projects;
    private List<Assignation> assignments;

//    public void assignProject(Employe employe, Projet project, String discipline, Date startTime) {
//        // Vérifier si l'employé existe et si le projet est valide, puis créer l'assignation
//        Assignation assignment = new Assignation(employe, project, discipline, startTime, null);
//        assignments.add(assignment);
}
