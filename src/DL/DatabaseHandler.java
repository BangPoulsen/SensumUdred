/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DL;

import BL.Case;

import java.sql.*;
import java.util.ArrayList;

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
            st.executeUpdate("insert into person (type, password, id, email, phone, name) values ('Borger', '12345678', '" + CPR + "', '" + email + "', '" + phoneNumber + "', '" + fullName + "')");
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

    public ArrayList<String> searchCase(String name) {
        try {
            Statement st = db.createStatement();
            st.executeQuery("select caseid, citizen from sag where citizen = (select id from person where name = '" + name +"')");
            ResultSet rs = st.getResultSet();
            ArrayList<String> results = new ArrayList<>();

            ResultSetMetaData rsmdt = rs.getMetaData();
            String caseString;
            while (rs.next()) {
                caseString = "";
                for (int i = 1; i <= rsmdt.getColumnCount(); i++){
                    caseString = caseString + rs.getString(i) + " ";
                }
                caseString.trim();
                results.add(caseString);
                System.out.println(caseString);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editCase() {
        
    }

    public void deleteCase() {
        
    }

}

