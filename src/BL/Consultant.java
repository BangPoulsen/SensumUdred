package BL;

public class Consultant {
    /**
     * attributes for creating a new consultant
     */
    private String cName;
    private String cAdress;
    private String cPhoneNumber;
    private String cEmail;

    /**
     * constructor for creating a instance of a consultant
     */
    public Consultant(String name, String adress, String phoneNumber, String email) {
        cName = name;
        cAdress = adress;
        cPhoneNumber = phoneNumber;
        cEmail = email;
    }
/**
 *   This method is a getter for cName
 *   This is the same for the rest of the "get" methods in Consultant
 *   @return String current value of cName
 */
    public String getcName() {
        return cName;
    }
/**
 *   This method is a setter for cName
 *   This is the same for the rest of the "set" methods in Consultant
 *   @param cName sets the initial value of cName
 */
    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAdress() {
        return cAdress;
    }

    public void setcAdress(String cAdress) {
        this.cAdress = cAdress;
    }

    public String getcPhoneNumber() {
        return cPhoneNumber;
    }

    public void setcPhoneNumber(String cPhoneNumber) {
        this.cPhoneNumber = cPhoneNumber;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }
    
    
    
}

