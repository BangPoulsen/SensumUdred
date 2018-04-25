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
    
    private static String url = "jdbc:postgresql://stampy.db.elephantsql.com:5432/pjgbvjcy";
    private static String username = "pjgbvjcy";
    private static String pasword = "eLDL8lqV2NwnApxtHn9DtBQorsPYEwls";

    private static Connection db;

    static {
        try {
            db = DriverManager.getConnection(url, username, pasword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseHandler() {
        try {
            System.out.println(db.getMetaData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    public void run() {
        

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
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

    public void createCase(Case caseI) {

        String fullName = caseI.getcCitizen().getCiName();
        String CPR = caseI.getcCitizen().getCiUserId();
        String phoneNumber = caseI.getcCitizen().getCiPhoneNumber();
        String email = caseI.getcCitizen().getCiEmail();
        String street = caseI.getcCitizen().getCiStreet();
        String streetNumber = caseI.getcCitizen().getCiStreetNumber();
        String floor = caseI.getcCitizen().getCiFloor();
        String zipCode = caseI.getcCitizen().getCiZipCode();
        String journalNumber = caseI.getcID();

        //SQL Stuff
        //TODO catch duplicate id's
        try {
            Statement st = db.createStatement();
            st.executeUpdate("insert into person (type, password, id, email, phone, name) values ('Borger', '12345678', '" + CPR + "', '" + email + "', '" + phoneNumber + "', '" + fullName + "')");
            st.executeUpdate("insert into adress (id, street, number, floor, zipcode) values ('" + CPR + "', '" + street + "', '" + streetNumber + "', '" + floor + "', '" + zipCode + "')");
            st.executeUpdate("insert into sag (rights, caseid, kin, support, consultant, responsible, citizen) values ('NULL', '" + journalNumber + "', 'NULL', 'NULL', 'NULL', 'NULL', '" + CPR + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
    }

    public void closeConnection(){
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

