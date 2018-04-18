package BL;

public class Citizen {

    /** 
     * attributes for creating a new citizen
     */
    private String ciName;
    private String ciAdress;
    private String ciPhoneNumber;
    private String ciEmail;
    private String ciUserId;

    /**
     * constructor for creating a instance of a citizen
     */
    public Citizen(String name, String adress, String phoneNumber, String email, String userId) {
        ciUserId=userId;
        ciName = name;
        ciAdress = adress;
        ciPhoneNumber = phoneNumber;
        ciEmail = email;
    }
/**
 *   This method is a getter for ciName
 *   This is the same for the rest of the "get" methods in Citizen
 *   @return String current value of ciName
 */
    public String getCiName() {
        return ciName;
    }
/**
 *   This method is a setter for ciName
 *   This is the same for the rest of the "set" methods in Citizen
 *   @param String cdate sets the initial value of ciName
 */

    public void setCiName(String ciName) {
        this.ciName = ciName;
    }

    public String getCiUserId() {
        return ciUserId;
    }

    public void setCiUserId(String ciUserId) {
        this.ciUserId = ciUserId;
    }

    public String getCiAdress() {
        return ciAdress;

    }

    public void setCiAdress(String ciAdress) {
        this.ciAdress = ciAdress;
    }

    public String getCiPhoneNumber() {
        return ciPhoneNumber;
    }

    public void setCiPhoneNumber(String ciPhoneNumber) {
        this.ciPhoneNumber = ciPhoneNumber;
    }

    public String getCiEmail() {
        return ciEmail;
    }

    public void setCiEmail(String ciEmail) {
        this.ciEmail = ciEmail;
    }
    
}