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
}