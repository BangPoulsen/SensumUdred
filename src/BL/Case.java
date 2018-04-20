package BL;

public class Case {
    /**
     * attributes for creating a new case
     */
    private String cdate;
    private String cStatus;
    private Caseworker cResponsible;
    private Citizen cCitizen;
    private SupportPerson cSupport;
    private Consultant cDoctor;
    private Relative cRelative;
    private String cID;
    private Journal cJournal;

    /**
     * constructor for creating a instance of a case
     */
    Case(String fullName, 
         String CPR, 
         String phoneNumber, 
         String email, 
         String address, 
         String floor, 
         String zipCode, 
         String journalNumber,
         String eventuelNotes) {
        
        cCitizen = new Citizen(fullName, address, phoneNumber, email,CPR);
        
        
        
        
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

    public Caseworker getcResponsible() {
        return cResponsible;
    }

    public void setcResponsible(Caseworker cResponsible) {
        this.cResponsible = cResponsible;
    }

    public Citizen getcCitizen() {
        return cCitizen;
    }

    public void setcCitizen(Citizen cCitizen) {
        this.cCitizen = cCitizen;
    }

    public String getcJournal() {
        return cJournal;
    }

    public void setcJournal(String cJournal) {
        this.cJournal = cJournal;
    }
    
    /**
     * The toString method for a case
     * @return A String with the date, status, responsible, citizen and journal of a case.
     */
    @Override
    public String toString(){
        return "Date: " + cdate + "\n" +
               "Status: " + cStatus + "\n" +
               "Responsible: " + cResponsible + "\n" +
               "Citizen: " + cCitizen + "\n" +
               "Journal: " + cJournal;
    }
    
} 




