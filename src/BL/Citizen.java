package BL;

public class Citizen {

    /**
     * attributes for creating a new citizen
     */
    private String ciName;
    private String ciAdress;
    private String ciPhoneNumber;
    private String ciEmail;

    /*<<<<<<< HEAD
    =======
    
    >>>>>>> b1c6f0d3089a2b3a2a15c3455de671c2aa7bf7e4*/

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