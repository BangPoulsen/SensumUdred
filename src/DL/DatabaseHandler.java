/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DL;

import BL.Case;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author malte
 */
public class DatabaseHandler {

    Connection db;
    
    String url = "jdbc:postgresql://stampy.db.elephantsql.com:5432/pjgbvjcy";
    String username = "pjgbvjcy";
    String pasword = "eLDL8lqV2NwnApxtHn9DtBQorsPYEwls";

    public DatabaseHandler() {
            //C:\Users\Malte Hesk\Documents\Github\C
    }

    
    
    public void run() {
        

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            db = DriverManager.getConnection(url, username, pasword);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM person");

            while (rs.next()) {
                String CPRnummer = rs.getString("id");
                System.out.println("CPRnummer: " + "" + CPRnummer);
            }

        } catch (SQLException e) {
            e.printStackTrace();


        }

    }

        public String getCPR(String CPR){

        try {

            db = DriverManager.getConnection(url, username, pasword);

            Statement st = db.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM person");



            while (rs.next()) {

                String CPRnummer = rs.getString("id");

                System.out.println("CPRnummer: " + CPRnummer);

                return CPRnummer;

            }



        } catch (SQLException e) {

            e.printStackTrace();

        }



        return "CPR not found";

    }


/*
    public String getAllInfoCitizen(String name){

        try {

            db = DriverManager.getConnection(url, username, pasword);

            Statement st = db.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM person where name = '" + name + "';");



            while (rs.next()) {

                String CPRnummer = rs.getString("name");

                System.out.println("Name: " + CPRnummer);

                return CPRnummer;

            }



        } catch (SQLException e) {

            e.printStackTrace();

        }



        return "Name not found";

    }*/

    void createCase(Case caseI) {

        String fullName = caseI.getcCitizen().getCiName();
        String CPR = caseI.getcCitizen().getCiUserId();
        String phoneNumber = caseI.getcCitizen().getCiPhoneNumber();
        String email = caseI.getcCitizen().getCiEmail();
        String street = caseI.getcCitizen().getCiStreet();
        String streetNumber = caseI.getcCitizen().getCiStreetNumber();
        String floor = caseI.getcCitizen().getCiFloor();
        String zipCode = caseI.getcCitizen().getCiZipCode();
        String journalNumber;

        //SQL Stuff
        //TODO catch duplicate id's
        try {
            db = DriverManager.getConnection(url, username, pasword);
            Statement st = db.createStatement();
            st.executeUpdate("insert into person (type, password, id, rights, email, phone, name) values ('Borger', '12345678', '" + CPR + "', 'GODMODE', '" + email + "', '" + phoneNumber + "', '" + fullName + "')");
            st.executeUpdate("insert into adress (id, street, number, floor, zipcode) values ('" + CPR + "', '" + street + "', '" + streetNumber + "', '" + floor + "', '" + zipCode + "')");
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
    }
}

