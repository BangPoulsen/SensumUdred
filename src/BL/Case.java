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

    /** This is kinda a change
     * constructor for creating a instance of a case
     */
    public Case(String date, String status, String responsible, String citizen, String journal) {
        cdate = date;
        cStatus = status;
        cResponsible = responsible;
        cCitizen = citizen;
        cJournal = journal;
    }
}
