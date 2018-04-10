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
/**
 * 
 * @return 
 */
    public String getCdate() {
        return cdate;
    }
/**
 * 
 * @param cdate 
 */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
/**
 * 
 * @return 
 */
    public String getcStatus() {
        return cStatus;
    }
/**
 * 
 * @param cStatus 
 */
    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }
/**
 * 
 * @return 
 */
    public String getcResponsible() {
        return cResponsible;
    }
/**
 * 
 * @param cResponsible 
 */
    public void setcResponsible(String cResponsible) {
        this.cResponsible = cResponsible;
    }
/**
 * 
 * @return 
 */
    public String getcCitizen() {
        return cCitizen;
    }
/**
 * 
 * @param cCitizen 
 */
    public void setcCitizen(String cCitizen) {
        this.cCitizen = cCitizen;
    }
/**
 * 
 * @return 
 */
    public String getcJournal() {
        return cJournal;
    }
/**
 * 
 * @param cJournal 
 */
    public void setcJournal(String cJournal) {
        this.cJournal = cJournal;
    }
    
} 




