package BL;

public class Commune {
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
    public Commune(String name, String adress, String phoneNumber, String email) {
        coName = name;
        coAdress = adress;
        coPhoneNumber = phoneNumber;
        coEmail = email;

    }
}