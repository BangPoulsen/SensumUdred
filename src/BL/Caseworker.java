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

}