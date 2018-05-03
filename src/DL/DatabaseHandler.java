/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DL;

import BL.Case;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malte
 */
public class DatabaseHandler {
    
    private static String url = "jdbc:postgresql://stampy.db.elephantsql.com:5432/pjgbvjcy";
    private static String username = "pjgbvjcy";
    private static String pasword = "eLDL8lqV2NwnApxtHn9DtBQorsPYEwls";

    private static Connection db;
    private static String user = "";

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
        String note = caseI.getcEventuelNotes();
        String author= caseI.getcauthor();
        String password = caseI.getcCitizen().getCiPassword();


        //SQL Stuff
        //TODO catch duplicate id's


        try {
            Statement st = db.createStatement();
            st.executeUpdate("insert into person (type, password, id, email, phone, name) values ('Borger', '" + password + "', '" + CPR + "', '" + email + "', '" + phoneNumber + "', '" + fullName + "')");
            st.executeUpdate("insert into adress (id, street, number, floor, zipcode) values ('" + CPR + "', '" + street + "', '" + streetNumber + "', '" + floor + "', '" + zipCode + "')");
            st.executeUpdate("insert into sag (caseid, kin, support, consultant, responsible, citizen) values ('" + journalNumber + "', 'NULL', 'NULL', 'NULL', 'NULL', '" + CPR + "')");
            st.executeUpdate("INSERT into journal (timestamp, note, caseid, author) values ('"+new Date().toString()+"',  '"+note+"','"+journalNumber+"','"+author+"')");
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

    public ResultSet searchCase(String name) {
        System.out.println(user);
        try {
            Statement st = db.createStatement();
            st.executeQuery("select sag.caseid, sag.citizen, person.name from sag inner join person on person.id = sag.citizen where sag.citizen in (select id from person where upper(name) like upper('" + name +"%'))");
            ResultSet rs = st.getResultSet();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editCase() {

    }

    public void deleteInfo(String id) {

        try {
            System.out.println(id);
            Statement st = db.createStatement();
            st.executeUpdate("begin; delete from person where person.id = '" + id + "'; delete from sag where sag.citizen = '" + id + "'; delete from journal using sag where journal.caseid = sag.caseid and sag.citizen = '" + id + "'; commit;");
            System.out.println("Case deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean loginAttempt(String username, String userPassword) {

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Person WHERE id = '" + username + "' AND password = '" + userPassword + "';");

            while (rs.next()) {
                                String type = rs.getString("email");
                                String password = rs.getString("password");
                                String id = rs.getString("id");
                                //String adress = rs.getString("adress");
                                String email = rs.getString("email");
                                String phone = rs.getString("phone");
                                String name = rs.getString("name");

                                System.out.println(type + " " + password + " " + id + " "  + " " + email + " " + phone + " " + name);
                                user = id;

                                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public String getId(String username) {

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT type FROM Person WHERE id = '" + username + "';");

            while (rs.next()) {
                                String id = rs.getString("type");
                                return id;
                            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<String> getCIDList() {
        ArrayList<String> caseIDs = new ArrayList<>();
        System.out.println(user);
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT caseid FROM sag;");

            while (rs.next()) {
                String id = rs.getString("caseid");
                caseIDs.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return caseIDs;
    }


    public ResultSet getCitizenInfo(String id) {
        System.out.println("Id: " + id);
        return getInfo(id);
    }

    public ResultSet getCitizenInfo() {
        System.out.println("User: " + user);
        return getInfo(user);
    }

    private ResultSet getInfo(String id) {
        try {
            Statement st = db.createStatement();
            st.executeQuery("select sag.caseid, person.name, person.id, person.phone, person.email, adress.street, adress.number, adress.floor, adress.zipcode, sag.consultant, sag.kin, sag.responsible, sag.support, journal.author, journal.timestamp, journal.note from sag inner join person on person.id = sag.citizen inner join adress on person.id = adress.id inner join journal on sag.caseid = journal.caseid where sag.citizen = '" + id + "';");
            ResultSet rs = st.getResultSet();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCurrentUser() {
        try {
            Statement st = db.createStatement();
            st.executeQuery("select name from person where id = '" + user + "';");
            ResultSet rs = st.getResultSet();
            while(rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void writeDate2file(long lockedDate) {
       
        
        try {
            FileWriter fw = new FileWriter(new File("lockedDate.txt"));
            
            fw.write(""+lockedDate);
            
            fw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long readDate() {
        
        try {

        Scanner input = new Scanner(new File("lockedDate.txt"));
        
        long date = 0;
            while (input.hasNextLine()) {
                date = Long.parseLong(input.nextLine());   
            }
            
            return date;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    public ResultSet getJournal(String id) {
        try {
            Statement st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeQuery("select journal.caseid, journal.timestamp, journal.author, journal.note from journal, sag where sag.citizen = '" + id + "' and sag.caseid = journal.caseid");
            ResultSet rs = st.getResultSet();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

