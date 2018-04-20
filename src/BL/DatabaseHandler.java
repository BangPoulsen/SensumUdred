/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

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
                String CPRnummer = rs.getString("CPRnummer");
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
                String CPRnummer = rs.getString("CPRnummer");
                System.out.println("CPRnummer: " + CPRnummer);
                return CPRnummer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "CPR not found";
    }
    
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
    }

    void createCase(String fullName, 
                    String CPR, 
                    String phoneNumber, 
                    String email, 
                    String address, 
                    String floor, 
                    String zipCode, 
                    String journalNumber) {
        
        //SQL Stuff
        
        
        
    }
}

