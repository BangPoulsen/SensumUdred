package BL;

public class Citizen {

    /** 
     * attributes for creating a new citizen
     */
    private String ciName;
    private String ciStreet;
    private String ciStreetNumber;
    private String ciFloor;
    private String ciZipCode;
    private String ciPhoneNumber;
    private String ciEmail;
    private String ciUserId;

    /**
     * constructor for creating a instance of a citizen
     */
    public Citizen(String name, String street, String streetNumber, String floor, String zipCode, String phoneNumber, String email, String userId) {
        this.ciUserId=userId;
        this.ciName = name;
        this.ciStreet = street;
        this.ciStreetNumber = streetNumber;
        if (floor == "") {
            this.ciFloor = "NULL";
        } else {
            this.ciFloor = floor;
        }
        this.ciZipCode = zipCode;
        this.ciPhoneNumber = phoneNumber;
        this.ciEmail = email;
    }
/**
 *   This method is a getter for ciName
 *   This is the same for the rest of the "get" methods in Citizen
 *   @return String current value of ciName
 */
    public String getCiName() {
        return ciName;
    }
/**
 *   This method is a setter for ciName
 *   This is the same for the rest of the "set" methods in Citizen
 *   @param ciName sets the initial value of ciName
 */

    public void setCiName(String ciName) {
        this.ciName = ciName;
    }

    public String getCiUserId() {
        return this.ciUserId;
    }

    public void setCiUserId(String ciUserId) {
        this.ciUserId = ciUserId;
    }

    public String getCiStreet() { return this.ciStreet; }

    public void setCiStreet(String ciStreet) {
        this.ciStreet = ciStreet;
    }

    public String getCiStreetNumber() {return this.ciStreetNumber;}

    public void setCiStreetNumber(String ciStreetNumber) {this.ciStreetNumber = ciStreetNumber;}

    public String getCiFloor() {return this.ciFloor;}

    public void setCiFloor(String ciFloor) {this.ciFloor = ciFloor;}

    public String getCiZipCode() {return this.ciZipCode;}

    public void setCiZipCode(String ciZipCode) {this.ciZipCode = ciZipCode;}

    public String getCiPhoneNumber() {
        return this.ciPhoneNumber;
    }

    public void setCiPhoneNumber(String ciPhoneNumber) {
        this.ciPhoneNumber = ciPhoneNumber;
    }

    public String getCiEmail() {
        return this.ciEmail;
    }

    public void setCiEmail(String ciEmail) {
        this.ciEmail = ciEmail;
    }
    
}