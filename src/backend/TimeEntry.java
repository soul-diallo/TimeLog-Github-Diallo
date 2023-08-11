package backend;

import java.util.Date;

public class TimeEntry {
    private Date date;
    private double hoursWorked;
    private Project project;
    private String discipline;
    private Employee employee;
    private Date timestamp;

    // Constructeur, getters, setters

    public TimeEntry(Employee employee, Project project, String discipline, double hoursWorked, Date workDate) {
        this.date = new Date();
        this.hoursWorked = hoursWorked;
        this.project = project;
        this.discipline = discipline;
        this.employee = employee;
        this.timestamp = workDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public Project getProject() {
        return project;
    }

    public String getDiscipline() {
        return discipline;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setTimestampEnd(Date timestampEnd) {
        this.timestamp = timestampEnd;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // ... (autres méthodes)
}

