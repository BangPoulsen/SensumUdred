package BL;

public class Relative {
    /**
     * attributes for creating a new relative
     */
    private String rName;
    private String rAdress;
    private String rPhoneNumber;
    private String rEmail;

    /**
     * constructor for creating a instance of a relative
     */
    public Relative(String name, String adress, String phoneNumber, String email) {
        rName =name;
        rAdress=adress;
        rPhoneNumber=phoneNumber;
        rEmail=email;
        //for testing purposes
        //System.out.println("relative name: " + rName );

    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrAdress() {
        return rAdress;
    }

    public void setrAdress(String rAdress) {
        this.rAdress = rAdress;
    }

    public String getrPhoneNumber() {
        return rPhoneNumber;
    }

    public void setrPhoneNumber(String rPhoneNumber) {
        this.rPhoneNumber = rPhoneNumber;
    }

    public String getrEmail() {
        return rEmail;
    }

    public void setrEmail(String rEmail) {
        this.rEmail = rEmail;
    }
    
    
}