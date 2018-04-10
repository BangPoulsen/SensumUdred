package BL;

public class Consultant {
    /**
     * attributes for creating a new consultant
     */
    private String cName;
    private String cAdress;
    private String cPhoneNumber;
    private String cEmail;

    /**
     * constructor for creating a instance of a consultant
     */
    public Consultant(String name, String adress, String phoneNumber, String email) {
        cName = name;
        cAdress = adress;
        cPhoneNumber = phoneNumber;
        cEmail = email;
    }
}