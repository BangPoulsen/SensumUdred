package BL;

public class Municipality {
    /**
     * attributes for creating a new commune
     */
    private String coName;
    private String coAdress;
    private String coPhoneNumber;
    private String coEmail;

    /**
     * constructor for creating a instance of a commune
     */
    public Municipality(String name, String adress, String phoneNumber, String email) {
        coName = name;
        coAdress = adress;
        coPhoneNumber = phoneNumber;
        coEmail = email;

    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getCoAdress() {
        return coAdress;
    }

    public void setCoAdress(String coAdress) {
        this.coAdress = coAdress;
    }

    public String getCoPhoneNumber() {
        return coPhoneNumber;
    }

    public void setCoPhoneNumber(String coPhoneNumber) {
        this.coPhoneNumber = coPhoneNumber;
    }

    public String getCoEmail() {
        return coEmail;
    }

    public void setCoEmail(String coEmail) {
        this.coEmail = coEmail;
    }
    
}