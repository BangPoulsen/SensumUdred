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
     * munstructor for creating a instance of a municipality
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

    public void setCoAdress(String muAdress) {
        this.muAdress = muAdress;
    }

    public String getCoPhoneNumber() {
        return muPhoneNumber;
    }

    public void setCoPhoneNumber(String muPhoneNumber) {

        this.muPhoneNumber = muPhoneNumber;
    }

    public String getCoEmail() {

        return muEmail;
    }

    public void setCoEmail(String muEmail) {

        this.muEmail = muEmail;
    }
    
}