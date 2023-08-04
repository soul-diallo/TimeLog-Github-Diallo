package backend;

import java.util.Date;

public class Admin {
    private int Id;
    private String username;
    private String password;
    private Role role;
    public Admin(int id, String username, String password, Role role) {
        Id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Méthode pour modifier les informations d'un employé
    public void updateEmployee(Employe employee) {
        // Vérifier si l'employé existe, puis mettre à jour ses informations
        for (int i = 0; i < TimeLogSystem.employees.size(); i++) {
            if (TimeLogSystem.employees.get(i).getID() == employee.getID()) {
                TimeLogSystem.employees.set(i, employee);
                break;
            }
        }
    }

    // Méthode pour supprimer un employé
    public void deleteEmployee(int employeeID) {
        // Vérifier si l'employé existe, puis le supprimer de la liste
        TimeLogSystem.employees.removeIf(employee -> employee.getID() == employeeID);
    }

    // Méthode pour modifier les informations d'un projet
    public void updateProject(Projet project) {
        // Vérifier si le projet existe, puis mettre à jour ses informations
        for (int i = 0; i < TimeLogSystem.projects.size(); i++) {
            if (TimeLogSystem.projects.get(i).getID() == project.getID()) {
                TimeLogSystem.projects.set(i, project);
                break;
            }
        }
    }

    // Méthode pour supprimer un projet
    public void deleteProject(int projectID) {
        // Vérifier si le projet existe, puis le supprimer de la liste
        TimeLogSystem.projects.removeIf(project -> project.getID() == projectID);
    }

    // Méthode pour assigner un projet à un employé
    public void assignProject(Employe employee, Projet project, String discipline, Date startTime) {
        // Vérifier si l'employé existe et si le projet est valide, puis créer l'assignation
        Assignation assignment = new Assignation(employee, project, discipline, startTime, null);
        TimeLogSystem.assignments.add(assignment);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
