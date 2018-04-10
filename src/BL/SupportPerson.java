package BL;

public class SupportPerson {
    /**
     * attributes for creating a new supportperson
     */
    private String sName;
    private String sAdress;
    private String sPhoneNumber;
    private String sEmail;

    /**
     * constructor for creating a instance of a supportperson
     */
    public SupportPerson(String name, String adress, String phoneNumber, String email) {
        sName = name;
        sAdress = adress;
        sPhoneNumber = phoneNumber;
        sEmail = email;
    }

    public String getsName() {
        return sName;
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