package BL;

public class Municipality {
    /**
     * attributes for creating a new municipality
     */
    private String muName;
    private String muAdress;
    private String muPhoneNumber;
    private String muEmail;

    /**
     * Constructor for creating a instance of a municipality
     * @param name The name of the municipality
     * @param adress The address of the municipality
     * @param phoneNumber the phone number of the municipality
     * @param email The email of the municipality
     */
    public Municipality(String name, String adress, String phoneNumber, String email) {
        muName = name;
        muAdress = adress;
        muPhoneNumber = phoneNumber;
        muEmail = email;
    }
/**
 *   This method is a getter for muName
 *   This is the same for the rest of the "get" methods in Municipality
 *   @return String current value of muName
 */
    public String getMuName() { return muName; }
/**
 *   This method is a setter for muName
 *   This is the same for the rest of the "set" methods in Municipality
 *   @param muName sets the initial value of muName
 */
    public void setMuName(String muName) { this.muName = muName; }

    public String getMuAdress() { return muAdress; }

    public void setMuAdress(String muAdress) {
        this.muAdress = muAdress;
    }

    public String getMuPhoneNumber() {
        return muPhoneNumber;
    }

    public void setMuPhoneNumber(String muPhoneNumber) { this.muPhoneNumber = muPhoneNumber; }

    public String getMuEmail() { return muEmail; }

    public void setMuEmail(String muEmail) { this.muEmail = muEmail; }
}