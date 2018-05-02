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
    private String cEventuelNotes;
    private String cauthor;

    public String getcauthor() { return cauthor;
    }

    public void setcauthor(String cauthor) {this.cauthor = cauthor;
    }

    public SupportPerson getcSupport() {
        return cSupport;
    }

    public void setcSupport(SupportPerson cSupport) {
        this.cSupport = cSupport;
    }

    public Consultant getcDoctor() {
        return cDoctor;
    }

    public void setcDoctor(Consultant cDoctor) {
        this.cDoctor = cDoctor;
    }

    public Relative getcRelative() {
        return cRelative;
    }

    public void setcRelative(Relative cRelative) {
        this.cRelative = cRelative;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getcEventuelNotes() {
        return cEventuelNotes;
    }

    public void setcEventuelNotes(String cEventuelNotes) {
        this.cEventuelNotes = cEventuelNotes;
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
        this.cEventuelNotes=eventuelNotes;
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

    public Caseworker getcResponsible() {
        return cResponsible;
    }

    public void setcResponsible(Caseworker cResponsible) {
        this.cResponsible = cResponsible;
    }

    public Citizen getcCitizen() {return cCitizen;   }

    public void setcCitizen(Citizen cCitizen) {
        this.cCitizen = cCitizen;
    }

    public Journal getcJournal() {
        return cJournal;
    }

    public void setcJournal(Journal cJournal) {
        this.cJournal = cJournal;
    }

    public String getcID(){return this.cID;

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





