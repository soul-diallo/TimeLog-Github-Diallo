package backend;

import com.google.gson.annotations.Expose;

import java.util.*;

public class Project {
    @Expose
    private Date startDate;
    @Expose
    private Date endDate;
    @Expose
    private Map<String, Double> budgetedHours; // Discipline -> Budgeted Hours
    @Expose
    private String name;
    @Expose
    private int identificationNumber;
    private transient Map<String, Double> actualHoursWorked;
    private transient List<Employee> assignedEmployees;
    private final transient List<TimeEntry> timeEntries;

    public Project(Date startDate, Date endDate, Map<String, Double> budgetedHours, String name,
                   int identificationNumber) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetedHours = budgetedHours;
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.actualHoursWorked = new HashMap<>();
        this.timeEntries = new ArrayList<>();
    }

    // Getters and setters for attributes


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Map<String, Double> getBudgetedHours() {
        return budgetedHours;
    }

    public void setBudgetedHours(Map<String, Double> budgetedHours) {
        this.budgetedHours = budgetedHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public Map<String, Double> getActualHoursWorked() {
        return actualHoursWorked;
    }

    public int getId() {
        return identificationNumber;
    }

    public List<Employee> getAssignedEmployees() {
        return new ArrayList<>(assignedEmployees);
    }

    // Other methods for managing actual hours worked

    public void generateProjectStatusReport() {
        System.out.println("Rapport d'état pour le projet : " + name);

        for (String discipline : DisciplineManager.getAllDisciplines()) {
            double hoursWorked = calculateTotalHoursForDiscipline(discipline);
            double budgetedHoursForDiscipline = budgetedHours.getOrDefault(discipline, 0.0);

            double percentageComplete = (hoursWorked / budgetedHoursForDiscipline) * 100;

            System.out.println("Discipline : " + discipline);
            System.out.println("Heures travaillées : " + hoursWorked);
            System.out.println("Heures budgétées : " + budgetedHoursForDiscipline);
            System.out.println("Pourcentage d'avancement : " + percentageComplete + "%");
            System.out.println();
        }
    }

    public double calculateTotalHoursForDiscipline(String discipline) {
        double totalHours = 0.0;
        for (TimeEntry timeEntry : timeEntries) {
            if (timeEntry.getDiscipline().equals(discipline)) {
                totalHours += timeEntry.getHoursWorked();
            }
        }
        return totalHours;
    }

    public double getBudgetedHoursForDiscipline(String discipline) {
        for (Map.Entry<String, Double> entry : budgetedHours.entrySet()) {
            if (entry.getKey().equals(discipline)) {
                return entry.getValue();
            }
        }
        return 0.0; // Discipline introuvable, retourne 0.0 par défaut
    }


    public void addTimeEntry(TimeEntry timeEntry) {
        timeEntries.add(timeEntry);
    }

}

