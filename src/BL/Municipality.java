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
     * @param name
     * @param adress
     * @param phoneNumber
     * @param email
     */
    public Municipality(String name, String adress, String phoneNumber, String email) {
        muName = name;
        muAdress = adress;
        muPhoneNumber = phoneNumber;
        muEmail = email;

    }

    public String getMuName() {

        return muName;
    }

    public void setMuName(String muName) {

        this.muName = muName;
    }

    public String getMuAdress() {

        return muAdress;
    }

    public void setMuAdress(String muAdress) {
        this.muAdress = muAdress;
    }

    public String getMuPhoneNumber() {
        return muPhoneNumber;
    }

    public void setMuPhoneNumber(String muPhoneNumber) {

        this.muPhoneNumber = muPhoneNumber;
    }

    public String getMuEmail() {

        return muEmail;
    }

    public void setMuEmail(String muEmail) {

        this.muEmail = muEmail;
    }
    
}