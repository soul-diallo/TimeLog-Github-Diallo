package backend;

import utils.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testMain {

    public testMain() {

    }

    public TimeLogSystem init() {
        Employee employee = null;
        TimeLogSystem timeLogSystem = new TimeLogSystem(employee,"admin", "admin");

        // Création des projets avec budgets pour toutes les disciplines
        Map<String, Double> budgetedHours1 = new HashMap<>();
        budgetedHours1.put("design1", 20.0);
        budgetedHours1.put("implementation", 30.0);
        budgetedHours1.put("test", 15.0);
        Project project1 = new Project(new Date(), new Date(), budgetedHours1, "projet1", 1);
        timeLogSystem.addProject(project1);

        Map<String, Double> budgetedHours2 = new HashMap<>();
        budgetedHours2.put("design2", 25.0);
        budgetedHours2.put("implementation", 35.0);
        budgetedHours2.put("test", 20.0);
        Project project2 = new Project(new Date(), new Date(), budgetedHours2, "projet2", 2);
        timeLogSystem.addProject(project2);

        Map<String, Double> budgetedHours3 = new HashMap<>();
        budgetedHours3.put("design1", 30.0);
        budgetedHours3.put("implementation", 40.0);
        budgetedHours3.put("test", 25.0);
        Project project3 = new Project(new Date(), new Date(), budgetedHours3, "projet3", 3);
        timeLogSystem.addProject(project3);

        // Création des employés avec noms d'usagers et IDs
        Employee admin = new Employee("admin", "0", 0.0, 0.0,
                new Date(), null, "", "Admin", "admin");
        timeLogSystem.addEmployee(admin);

        Employee employee1 = new Employee("employé1", "1", 15.0, 20.0,
                new Date(), null, "123-456-789", "Développeur", "password1");
        timeLogSystem.addEmployee(employee1);

        Employee employee2 = new Employee("employé2", "2", 18.0, 25.0,
                new Date(), null, "987-654-321", "Designer", "password2");
        timeLogSystem.addEmployee(employee2);

        Employee employee3 = new Employee("employé3", "3", 20.0, 30.0,
                new Date(), null, "555-123-456", "Testeur", "password3");
        timeLogSystem.addEmployee(employee3);

        // Assignation des employés à des projets avec les durées de travail
        timeLogSystem.assignEmployeeToProject(employee1, project1);
        timeLogSystem.assignEmployeeToProject(employee1, project2);
        timeLogSystem.assignEmployeeToProject(employee2, project2);
        timeLogSystem.assignEmployeeToProject(employee2, project3);
        timeLogSystem.assignEmployeeToProject(employee3, project3);
        timeLogSystem.assignEmployeeToProject(employee3, project1);

        // Date actuelle
        Date currentDate = new Date();

// Obtenez les disciplines depuis le DisciplineManager
        List<String> disciplines = DisciplineManager.getAllDisciplines();

// Générez les entrées de temps pour chaque employé et projet
        for (int day = -14; day < 0; day++) {
            Date workDate = DateUtils.addDays(currentDate, day);

            for (String discipline : disciplines) {
                double hoursWorked = 0.0;

                hoursWorked = 1.1;
                employee1.addTimeEntry(new TimeEntry(employee1, project1, discipline, hoursWorked, workDate));
                employee1.addTimeEntry(new TimeEntry(employee1, project2, discipline, hoursWorked, workDate));

                if (employee2 != null) {
                    hoursWorked = 1.2;
                    employee2.addTimeEntry(new TimeEntry(employee2, project2, discipline, hoursWorked, workDate));
                    employee2.addTimeEntry(new TimeEntry(employee2, project3, discipline, hoursWorked, workDate));
                }

                if (employee3 != null) {
                    hoursWorked = 1.3;
                    employee3.addTimeEntry(new TimeEntry(employee3, project3, discipline, hoursWorked, workDate));
                    employee3.addTimeEntry(new TimeEntry(employee3, project1, discipline, hoursWorked, workDate));
                }
            }
        }


//        // Affichage des informations des employés et des projets au format JSON
//        System.out.println("Informations des employés :");
//        for (Employee employee : timeLogSystem.getEmployees()) {
//            System.out.println(JsonUtils.toJson(employee));
//        }
//
//        System.out.println("\nInformations des projets :");
//        for (Project project : timeLogSystem.getProjects()) {
//            System.out.println(JsonUtils.toJson(project));
//        }
//
//         // Rapport
//        project1.generateProjectStatusReport();

//        // Convertir les employés en JSON et écrire dans un fichier
//        String employeesJson = JsonUtils.toJson(timeLogSystem.getEmployees());
//        JsonUtils.writeJsonToFile(employeesJson, "employees.json");
//
//        // Convertir les projets en JSON et écrire dans un fichier
//        String projectsJson = JsonUtils.toJson(timeLogSystem.getProjects());
//        JsonUtils.writeJsonToFile(projectsJson, "projects.json");

        return timeLogSystem;

    }
}
