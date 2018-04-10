package BL;

public class Caseworker {

    /**
     * attributes for creating a new caseworker
     */
    private String cwName;
    private String cwAdress;
    private String cwPhoneNumber;
    private String cwEmail;

    /**
     * constructor for creating a instance of a caseworker
     */
    public Caseworker(String name, String adress, String phoneNumber, String email) {
        cwName = name;
        cwAdress = adress;
        cwPhoneNumber = phoneNumber;
        cwEmail = email;
        // for testing
        //System.out.println("caseworker name :"+ name + "caseworker adress : " + adress + "caseworker phonenumber :" + phoneNumber + "caseworker email : " + email );
    }

    public String getCwName() {
        return cwName;
    }

    public void setCwName(String cwName) {
        this.cwName = cwName;
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