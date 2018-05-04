package Business;

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
/**
 *   This method is a getter for rName
 *   This is the same for the rest of the "get" methods in Relative
 *   @return String current value of rName
 */
    public String getrName() { return rName; }
/**
 *   This method is a setter for rName
 *   This is the same for the rest of the "set" methods in Relative
 *   @param rName sets the initial value of rName
 */
    public void setrName(String rName) { this.rName = rName; }

    public String getrAdress() { return rAdress; }

    public void setrAdress(String rAdress) { this.rAdress = rAdress; }

    public String getrPhoneNumber() { return rPhoneNumber; }

    public void setrPhoneNumber(String rPhoneNumber) { this.rPhoneNumber = rPhoneNumber; }

    public String getrEmail() { return rEmail; }

    public void setrEmail(String rEmail) { this.rEmail = rEmail; }
}