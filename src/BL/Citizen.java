package BL;

public class Citizen {

    /** asd
     * attributes for creating a new citizen
     */
    private String ciName;
    private String ciAdress;
    private String ciPhoneNumber;
    private String ciEmail;

    /**
     * constructor for creating a instance of a citizen
     */
    public Citizen(String name, String adress, String phoneNumber, String email) {
        ciName = name;
        ciAdress = adress;
        ciPhoneNumber = phoneNumber;
        ciEmail = email;
    }

    public String getCiName() {
        return ciName;
    }

    public void setCiName(String ciName) {
        this.ciName = ciName;
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