package BL;

public class Caseworker {

    /**
     * attributes for creating a new caseworker
     */
    private String cwName;
    private String cwAdress;
    private String cwPhoneNumber;
    private String cwEmail;
    private String cwUserID;

    /**
     * constructor for creating a instance of a caseworker
     */
    public Caseworker(String name, String adress, String phoneNumber, String email, String userId) {
        cwUserID=userId;
        cwName = name;
        cwAdress = adress;
        cwPhoneNumber = phoneNumber;
        cwEmail = email;
        // for testing
        //System.out.println("caseworker name :"+ name + "caseworker adress : " + adress + "caseworker phonenumber :" + phoneNumber + "caseworker email : " + email );
    }
/**
 *   This method is a getter for cwName
 *   This is the same for the rest of the "get" methods in Caseworker
 *   @return String current value of cwName
 */
    public String getCwName() {
        return cwName;
    }
/**
 *   This method is a setter for cdate
 *   This is the same for the rest of the "set" methods in Caseworker
 *   @param String cwName sets the initial value of cdate
 */
    public void setCwName(String cwName) {
        this.cwName = cwName;
    }

    public String getCwUserID() {
        return cwUserID;
    }

    public void setCwUserID(String cwUserID) {
        this.cwUserID = cwUserID;
    }

    public String getCwAdress() {
        return cwAdress;
    }

    public void setCwAdress(String cwAdress) {
        this.cwAdress = cwAdress;
    }

    public String getCwPhoneNumber() {
        return cwPhoneNumber;
    }

    public void setCwPhoneNumber(String cwPhoneNumber) {
        this.cwPhoneNumber = cwPhoneNumber;
    }

    public String getCwEmail() {
        return cwEmail;
    }

    public void setCwEmail(String cwEmail) {
        this.cwEmail = cwEmail;
    }

}