package backend;



import com.google.gson.annotations.Expose;
import utils.DateUtils;

import java.util.*;

public class Employee {
    private static final double MAX_REGULAR_HOURS_PER_DAY = 8;
    @Expose
    private String username;
    @Expose
    private String id;
    @Expose
    private double baseHourlyRate;
    @Expose
    private double overtimeHourlyRate;
    @Expose
    private Date hireDate;
    @Expose
    private Date departureDate;
    @Expose
    private String socialSecurityNumber;
    @Expose
    private String position;
    @Expose
    private Map<String, Double> actualHoursWorked;
    @Expose
    private String password;
    private final transient List<TimeEntry> timeEntries = new ArrayList<>();
    private transient final Set<Project> assignedProjects = new HashSet<>();
    private TimeEntry currentActivity;

    public Employee(String username, String id, double baseHourlyRate, double overtimeHourlyRate, Date hireDate,
                    Date departureDate, String socialSecurityNumber, String position, String password) {
        this.username = username;
        this.id = id;
        this.baseHourlyRate = baseHourlyRate;
        this.overtimeHourlyRate = overtimeHourlyRate;
        this.hireDate = hireDate;
        this.departureDate = departureDate;
        this.socialSecurityNumber = socialSecurityNumber;
        this.position = position;
        this.password = password;
        this.actualHoursWorked = new HashMap<>();
    }


    public Employee(String username, String password, double baseHourlyRate, double overtimeHourlyRate) {
        this.username = username;
        this.baseHourlyRate = baseHourlyRate;
        this.overtimeHourlyRate = overtimeHourlyRate;
        this.password = password;
    }

    // Getters and setters for attributes
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBaseHourlyRate(double baseHourlyRate) {
        this.baseHourlyRate = baseHourlyRate;
    }

    public double getOvertimeHourlyRate() {
        return overtimeHourlyRate;
    }

    public void setOvertimeHourlyRate(double overtimeHourlyRate) {
        this.overtimeHourlyRate = overtimeHourlyRate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setActualHoursWorked(Map<String, Double> actualHoursWorked) {
        this.actualHoursWorked = actualHoursWorked;
    }

    public double calculateBasePay(double hoursWorked) {
        return hoursWorked * baseHourlyRate;
    }

    public double calculateOvertimePay(double overtimeHours) {
        return overtimeHours * overtimeHourlyRate;
    }

    public Map<String, Double> getActualHoursWorked() {
        return actualHoursWorked;
    }

    public String getPassword() {
        return password;
    }

    public double getBaseHourlyRate() {
        return baseHourlyRate;
    }

    public double getTotalRegularHoursWorked() {
        double totalRegularHours = 0.0;
        for (TimeEntry timeEntry : timeEntries) {
            if (!isOvertime(timeEntry)) {
                totalRegularHours += timeEntry.getHoursWorked();
            }
        }
        return totalRegularHours;
    }

    public double getTotalOvertimeHoursWorked() {
        double totalOvertimeHours = 0.0;
        for (TimeEntry timeEntry : timeEntries) {
            if (isOvertime(timeEntry)) {
                totalOvertimeHours += timeEntry.getHoursWorked();
            }
        }
        return totalOvertimeHours;
    }

    public List<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    public Set<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void addAssignedProject(Project project) {
        assignedProjects.add(project);
    }

    public void removeAssignedProject(Project project) {
        assignedProjects.remove(project);
    }

    private boolean isOvertime(TimeEntry timeEntry) {
        // Détermine si une entrée de temps est en heures supplémentaires en fonction des heures de l'employé
        return timeEntry.getHoursWorked() > getStandardHoursPerWeek();
    }

    double getStandardHoursPerWeek() {
        // Retourne le nombre d'heures régulières par semaine pour l'employé (à définir selon vos besoins)
        return 40.0;
    }

    public void addTimeEntry(TimeEntry timeEntry) {
        timeEntries.add(timeEntry);
    }

    public double calculateLastPaycheck() {
        Date currentDate = new Date();
        Date lastPayPeriodStartDate = DateUtils.getLastPayPeriodStartDate(currentDate);

        return calculateSalarySince(lastPayPeriodStartDate, currentDate);
    }

    public double calculateSalarySince(Date startDate, Date endDate) {
        double totalSalary = 0.0;

        for (TimeEntry timeEntry : timeEntries) {
            if (timeEntry.getDate().after(startDate) && timeEntry.getDate().before(endDate)) {
                double baseHourlyRate = getBaseHourlyRate();
                double overtimeHourlyRate = getOvertimeHourlyRate();

                double hoursWorked = timeEntry.getHoursWorked();
                double overtimeHours = hoursWorked - MAX_REGULAR_HOURS_PER_DAY;

                if (overtimeHours > 0) {
                    totalSalary += (MAX_REGULAR_HOURS_PER_DAY * baseHourlyRate) + (overtimeHours * overtimeHourlyRate);
                } else {
                    totalSalary += hoursWorked * baseHourlyRate;
                }
            }
        }

        return totalSalary;
    }

    public void startActivity(String project, String discipline, TimeLogSystem timeLogSystem, Scanner scanner) {
        if (currentActivity != null) {
            System.out.println("Vous avez déjà commencé une activité. Veuillez la terminer d'abord.");
            return;
        }

        Project assignedProject = timeLogSystem.getAssignedProjectByName(project);
        if (assignedProject == null) {
            System.out.println("Projet non assigné ou introuvable.");
            return;
        }

        currentActivity = new TimeEntry(this, assignedProject, discipline, 0.0, new Date());
        System.out.println("Activité commencée sur le projet : " + project + ", discipline : " + discipline);
    }

    public void endActivity(TimeLogSystem timeLogSystem) {
        if (currentActivity == null) {
            System.out.println("Aucune activité en cours. Veuillez commencer une activité d'abord.");
            return;
        }

        currentActivity.setTimestampEnd(new Date());
        timeEntries.add(currentActivity);
        currentActivity = null;
        System.out.println("Activité terminée.");
    }


    public double calculateBaseHoursForPeriod(Date startDate, Date endDate) {
        double totalBaseHours = 0.0;
        for (TimeEntry entry : timeEntries) {
            if (entry.getTimestamp().after(startDate) && entry.getTimestamp().before(endDate)) {
                totalBaseHours += entry.getHoursWorked();
            }
        }
        return totalBaseHours;
    }

    public double calculateOvertimeHoursForPeriod(Date startDate, Date endDate) {
        double totalOvertimeHours = 0.0;
        for (TimeEntry entry : timeEntries) {
            if (entry.getTimestamp().after(startDate) && entry.getTimestamp().before(endDate)) {
                totalOvertimeHours += entry.getHoursWorked();
            }
        }
        return totalOvertimeHours;
    }


}

