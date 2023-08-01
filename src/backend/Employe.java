package backend;

import java.util.Date;

public class Employe {
    private String username;
    private int ID;
    private double hourlyRate;
    private double overtimeRate;
    private Date hireDate;
    private String socialSecurity;
    private String poste;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Employe(String username, int ID, double hourlyRate, double overtimeRate, Date hireDate, String socialSecurity, String poste) {
        this.username = username;
        this.ID = ID;
        this.hourlyRate = hourlyRate;
        this.overtimeRate = overtimeRate;
        this.hireDate = hireDate;
        this.socialSecurity = socialSecurity;
        this.poste = poste;
    }
}
