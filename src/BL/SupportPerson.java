package BL;

public class SupportPerson {
    /**
     * attributes for creating a new supportperson
     */
    private String sName;
    private String sAdress;
    private String sPhoneNumber;
    private String sEmail;
    private String sUserId;

    /**
     * constructor for creating a instance of a supportperson
     */
    public SupportPerson(String name, String adress, String phoneNumber, String email, String userId) {
        sUserId=userId;
        sName = name;
        sAdress = adress;
        sPhoneNumber = phoneNumber;
        sEmail = email;
    }
/**
 *   This method is a getter for sName
 *   This is the same for the rest of the "get" methods in SupportPerson
 *   @return String current value of sName
 */
    public String getsName() {
        return sName;
    }



    /**
 *   This method is a setter for sName
 *   This is the same for the rest of the "set" methods in SupportPerson
 *   @param String cdate sets the initial value of sName
 */
    public String getsUserId() {
        return sUserId;
    }

    public void setsUserId(String sUserId) {
        this.sUserId = sUserId;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAdress() {
        return sAdress;
    }

    public void setsAdress(String sAdress) {
        this.sAdress = sAdress;
    }

    public String getsPhoneNumber() {
        return sPhoneNumber;
    }

    public void setsPhoneNumber(String sPhoneNumber) {
        this.sPhoneNumber = sPhoneNumber;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }
    
}