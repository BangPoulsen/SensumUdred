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
public class Journal {
    
    private String timeStamp;
    private String caseID;
    private String eventuelNotes;
    private String problemDesciption;
    private String problemAssesment;
    private String toDo;
    private String cID;
    private String author;

    public Journal(String timeStamp, String caseID, String problemDesciption, String problemAssesment, String toDo, String cID, String author) {
        this.timeStamp = timeStamp;
        this.caseID = caseID;
        this.problemDesciption = problemDesciption;
        this.problemAssesment = problemAssesment;
        this.toDo = toDo;
        this.cID = cID;
        this.author=author;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getcID() {return cID;

    }
    public void setcID(String cID) {this.cID = cID;
    }

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

    public String getProblemDesciption() {return problemDesciption;
    }

    public void setProblemDesciption(String problemDesciption) {this.problemDesciption = problemDesciption;
    }

    public String getProblemAssesment() {return problemAssesment;
    }

    public void setProblemAssesment(String problemAssesment) {
        this.problemAssesment = problemAssesment;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
