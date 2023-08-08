package backend;

import java.util.Date;

public class Employe {
    private int ID;
    private String username;
    private String password;
    private double hourlyRate;
    private double overtimeRate;

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    private String hireDate;
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

    public String getHireDate() {
        return hireDate;
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

    public Employe(int ID, String username, String password, double hourlyRate, double overtimeRate, String hireDate, String socialSecurity, String poste) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.hourlyRate = hourlyRate;
        this.overtimeRate = overtimeRate;
        this.hireDate = hireDate;
        this.socialSecurity = socialSecurity;
        this.poste = poste;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
