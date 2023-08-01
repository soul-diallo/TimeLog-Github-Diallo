package backend;

import java.util.Date;

public class Assignation {
    private Employe employe;
    private Projet projet;
    private String discipline;
    private Date startTime;
    private Date endTime;

    public Assignation(Employe employe, Projet projet, String discipline, Date startTime, Date endTime) {
        this.employe = employe;
        this.projet = projet;
        this.discipline = discipline;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
