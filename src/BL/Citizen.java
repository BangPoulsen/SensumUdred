package BL;

public class Citizen {

    /**
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
}