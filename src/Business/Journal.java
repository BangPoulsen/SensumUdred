/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author malte
 */
class Journal {
    
    private String timeStamp;
    private String caseID;
    private String eventuelNotes;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getEventuelNotes() {
        return eventuelNotes;
    }

    public void setEventuelNotes(String eventuelNotes) {
        this.eventuelNotes = eventuelNotes;
    }
}
