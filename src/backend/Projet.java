package backend;

import java.util.Date;

public class Projet {
    private String name;
    private int ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Projet(String name, int ID, Date startDate, Date endDate, double budget) {
        this.name = name;
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }

    private Date startDate;
    private Date endDate;
    private double budget;

}
