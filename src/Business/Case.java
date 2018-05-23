package Business;

public class Case {
    /**
     * attributes for creating a new case
     */
    private String cdate;
    private String cStatus;
    private Citizen cCitizen;
    private String cID;
    private String cNotes;
    private String cauthor;

    public String getcauthor() {
        return cauthor;
    }

    public void setcauthor(String cauthor){
        this.cauthor = cauthor; 
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getcNotes() {
        return cNotes;
    }

    public void setcNotes(String cNotes) {
        this.cNotes = cNotes;
    }

    /**
     * constructor for creating a instance of a case
     */
    public Case(Citizen cCitizen,
                String caseID,
                String eventuelNotes,
                String author ) {
        
        this.cCitizen = cCitizen;
        this.cID = caseID;
        this.cNotes =eventuelNotes;
        this.cauthor=author;
    }
/**
 *   This method is a getter for cdate
 *   This is the same for the rest of the "get" methods in Case
 *   @return String current value of cdate
 */
    public String getCdate() {
        return cdate;
    }
/**
 *   This method is a setter for cdate
 *   This is the same for the rest of the "set" methods in Case
 *   @param cdate sets the initial value of cdate
 */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public Citizen getcCitizen() { return cCitizen; }

    public void setcCitizen(Citizen cCitizen) {
        this.cCitizen = cCitizen;
    }

    public String getcID(){ return this.cID; }

}





