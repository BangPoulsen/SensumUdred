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
            st.executeUpdate("insert into person (type, password, id, email, phone, name) values ('Borger', 'password', '" + CPR + "', '" + email + "', '" + phoneNumber + "', '" + fullName + "')");
            st.executeUpdate("insert into adress (id, street, number, floor, zipcode) values ('" + CPR + "', '" + street + "', '" + streetNumber + "', '" + floor + "', '" + zipCode + "')");
            st.executeUpdate("insert into sag (caseid, kin, support, consultant, responsible, citizen) values ('" + journalNumber + "', 'NULL', 'NULL', 'NULL', 'NULL', '" + CPR + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
    }

    //TODO searchCase()

    public void closeConnection(){
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchCase() {
        
    }

    public void editCase() {
        
    }

    public void deleteCase() {
        
    }

    public boolean loginAttempt(String username, String userPassword) {
       
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Person WHERE type = '" + username + "' AND password = '" + userPassword + "';");
            
            while (rs.next()) {
                                String type = rs.getString("email");
                                String password = rs.getString("password");
                                String id = rs.getString("id");
                                //String adress = rs.getString("adress");
                                String email = rs.getString("email");
                                String phone = rs.getString("phone");
                                String name = rs.getString("name");
                                
                                
                       
                                
                                
                                
                                System.out.println(type + " " + password + " " + id + " "  + " " + email + " " + phone + " " + name);
                                
                                return true;
                            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
        
    }

}

