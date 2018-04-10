package BL;

public class Case {
    /**
     * attributes for creating a new case
     */
    private String cdate;
    private String cStatus;
    private String cResponsible;
    private String cCitizen;
    private String cJournal;

    /**
     * constructor for creating a instance of a case
     */
    public Case(String date, String status, String responsible, String citizen, String journal) {
        cdate = date;
        cStatus = status;
        cResponsible = responsible;
        cCitizen = citizen;
        cJournal = journal;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getcResponsible() {
        return cResponsible;
    }

    public void setcResponsible(String cResponsible) {
        this.cResponsible = cResponsible;
    }

    public String getcCitizen() {
        return cCitizen;
    }

    public void setcCitizen(String cCitizen) {
        this.cCitizen = cCitizen;
    }

    public String getcJournal() {
        return cJournal;
    }

    public void setcJournal(String cJournal) {
        this.cJournal = cJournal;
    }
    
} 




