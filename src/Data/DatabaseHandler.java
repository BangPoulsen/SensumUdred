/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Case;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


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

    /**
     * Adds new entries to the sql database tables thus creating a new case and its relevant contents.
     *
     * @param caseI Object which defines a case and its relevant contents.
     */
    public void createCase(Case caseI) throws IDExistException {

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
        String author = caseI.getcauthor();
        String password = caseI.getcCitizen().getCiPassword();




        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM person;");

            while (rs.next()) {
                if (rs.getString(1).equals(CPR)) {
                    throw new IDExistException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement st = db.createStatement();
            st.executeUpdate("" +
                "begin; " +
                "insert into person (type, password, id, email, phone, name) values ('Borger', '" + password + "', '" + CPR + "', '" + email + "', '" + phoneNumber + "', '" + fullName + "'); " +
                "insert into adress (id, street, number, floor, zipcode) values ('" + CPR + "', '" + street + "', '" + streetNumber + "', '" + floor + "', '" + zipCode + "');" +
                "insert into sag (caseid, kin, support, consultant, responsible, citizen) values ('" + journalNumber + "', 'NULL', 'NULL', 'NULL', 'NULL', '" + CPR + "');" +
                "insert into journal (timestamp, note, caseid, author) values ('" + new Date().toString() + "',  '" + note + "','" + journalNumber + "','" + author + "');" +
                "commit;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for a specific case using the ID of a person, thus returning a resultset containing mentioned info.
     *
     * @param name Should be the ID of a person. Is used in an sql querry to get info
     * @return a resultset containing information about a case using ID's of a person.
     */
    public ResultSet searchCase(String name) {

        try {
            Statement st = db.createStatement();
            st.executeQuery("select sag.caseid, sag.citizen, person.name from sag inner join person on person.id = sag.citizen where sag.citizen in (select id from person where upper(name) like upper('" + name + "%'))");
            ResultSet rs = st.getResultSet();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes entries from the database that pertains to a specific case, thus deleting a case.
     *
     * @param id Is the ID of a person, and is used in an sql querry to find and delete relevant entries.
     */
    public void deleteInfo(String id) {
        try {
            Statement st = db.createStatement();
            st.executeUpdate("" +
                "begin; " +
                "delete from person where person.id = '" + id + "'; delete from sag where sag.citizen = '" + id + "'; " +
                "delete from journal using sag where journal.caseid = sag.caseid and sag.citizen = '" + id + "'; " +
                "commit;");
            System.out.println("Case deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attemps to establish a connection to the database using the given username and userPassword,
     * to do this, it verifies whether the username and userPasswords match with an sql querry
     * and grants a connection accordingly.
     *
     * @param username A string that is the users username ...
     * @param userPassword A string that is the users password.
     * @return In case the querry is successful and a connection is established this method will return true.
     */
    public boolean loginAttempt(String username, String userPassword) {

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Person WHERE id = '" + username + 
                    "' AND password = '" + userPassword + "';");

            while (rs.next()) {

                String type = rs.getString("type");
                String password = rs.getString("password");
                String id = rs.getString("id");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String name = rs.getString("name");

                user = id;
                
                write2file("currentUser.txt", id + "\t" + type + "\t" + email + "\t" 
                        + phone + "\t" + name + "\t" + password);


                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 
     * @return an array of strings with info about the current user. Index 0 = id, 1 = type, 2 = email, 3 = phone, 4 = name, 5 = password.
     */
    
    public String[] getCurrentUserInfo(){
        Scanner input = new Scanner("currentUser.txt");
        
        while(input.hasNextLine()){
            String[] userInfo = input.nextLine().split("\t");
            return userInfo;
        }
        
        return null;
        
    }

    /**
     * Finds the type of the user.
     *
     * @param username Uses this as a persons name to find the corresponding type during an sql querry.
     * @return A string containing the type of the user.
     */
    
    public String getType(String username) {

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT type FROM Person WHERE id = '" + username + "';");

            while (rs.next()) {
                String type = rs.getString("type");
                return type;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a list of all the cases ID's using an sql querry.
     *
     * @return an array containing strings that are all the cases ID's.
     */
    public ArrayList<String> getCaseIDList() {
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

    /**
     * Gets a lot of info about a person.
     *
     * @param id Is the ID of a person.
     * @return a resultset containing the info.
     */
    public ResultSet getCitizenInfo(String id) {
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

    /**
     * Gets a lot of info about the user.
     *
     * @return a resultset containing the info.
     */
    public ResultSet getUserInfo() {
        System.out.println("User: " + user);
        
        return getInfo(user);
    }

    /**
     * Generates the resultset containing a lot of info about a person.
     *
     * @param id Takes an ID of a person that is used in an sql querry.
     * @return a resultset containing the gathered info.
     */
    public ResultSet getInfo(String id) {
        try {
            Statement st = db.createStatement();
            st.executeQuery("select person.name, person.id, person.phone, person.email, adress.street, adress.number, adress.floor, adress.zipcode from person inner join adress on person.id = adress.id where person.id = '" + id + "';");
            ResultSet rs = st.getResultSet();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the name of the current user.
     * Does an sql querry with the ID of the current user.
     *
     * @return A string containing the name of the current user.
     */
    public String getCurrentUser() {
        try {
            Statement st = db.createStatement();
            st.executeQuery("SELECT name FROM person WHERE id = '" + user + "';");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Writes the current date to a file.
     *
     * @param filename the name of the file written to.
     * @param text the text written to specified file.

     */

    public void write2file(String filename, String text) {
        
        FileWriter fw = null;

        try {

            fw = new FileWriter(new File(filename));

            fw = new FileWriter(new File(filename), false);

            fw.write(text);

            

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets a date from a file.
     *
     * @return A long containing the date.
     */
    public long readDate() {

        try {

            File lockedDate = new File("lockedDate.txt");

            Scanner input = null;
            if (lockedDate.exists()) {
                input = new Scanner(lockedDate);

                long date = 0;
                while (input.hasNextLine()) {
                    
                    String number = input.nextLine();
                    
                    if (!number.equals("")) {
                        System.out.println("Date number: " + number);
                        date = Long.parseLong(number);
                    }
                }

                return date;
            } else {
                return -1;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }


    /**
     * Gets a journal from a persons case.
     *
     * @param id A string containing a persons ID, used in an sql querry that matches the ID.
     * @return A resultset containing the journal of the persons case.
     */
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

    /**
     * Gets information about Sagsbehandlere, Støttepersoner og læger.
     *
     * @return A resultset containing the info.
     */

    public ResultSet getUsers() {
        try {
            Statement st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeQuery("SELECT name, id, type FROM Person WHERE type = 'Sagsbehandler' OR type = 'støtte' OR type = 'Læge'");
            ResultSet rs = st.getResultSet();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getUsers(String type) {
        try {
            Statement st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeQuery("SELECT name, id, type FROM Person WHERE type = '" + type + "'");
            ResultSet rs = st.getResultSet();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ResultSet getLogs() {
        try {
            Statement st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeQuery("SELECT * FROM log");
            ResultSet rs = st.getResultSet();
            
            
            
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    /**
     * Creates a user by adding relevant entries to the Database using an sql statement.
     *
     * @param name A string containing the new users name.
     * @param email A string containing the new users email.
     * @param phoneNumber A string containing the new users phonenumber.
     * @param id A string containing the new users ID.
     * @param password A string containing the new users password.
     * @param type A string containing the new users type.
     */
    public void createUser(String name, String email, String phoneNumber, String address, String floor, String zipCode, String id, String password, String type) {
        try {
            String[] streetSplit = address.split(" ");

            String street = "";
            for (String s: streetSplit) {
                if (streetSplit.length != 1) {
                    if (s == null ? streetSplit[streetSplit.length - 1] != null : !s.equals(streetSplit[streetSplit.length - 1])) {
                        street +=  s + " ";
                    }
                }
                else {
                    street = s;
                }
            }

            street = street.trim();

            String streetNumber = streetSplit[streetSplit.length - 1];
            Statement st = db.createStatement();
            //type, password, id, email, phone, name
            st.executeUpdate("" +
                    "begin;" +
                    "INSERT INTO person VALUES ('" + type + "', '" + password + "', '" + id + "', '" + email + "', '" + phoneNumber + "', '" + name + "');" +
                    "insert into adress values ('', '" + id + "', '" + street + "', '" + streetNumber + "', '" + floor + "', '" + zipCode + "');" +
                    "commit;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the journal of a case.
     * Adds strings with different information to the journal providing a timestamp and what is being written about.
     *
     * @param problemDescription A string containing information about an updated review of the description of the problem.
     * @param problemAssesment A string containing information about an updated review of the assesment of the problem.
     * @param toDo A string containing information about an updated review of what is to be done about the problem.
     * @param author A string containing the author.
     * @param journalNumber A string containing the journals number.
     */
    public void updateDatabase(String problemDescription, String problemAssesment,String toDo, String author, String journalNumber){
        try{
            Statement st = db.createStatement();
            st.execute(" insert into journal (timestamp, note, caseid, author) values ('" + new Date().toString() + "',  '"+ problemDescription + "','" + journalNumber + "','" + author + "');");
            st.execute(" insert into journal (timestamp, note, caseid, author) values ('" + new Date().toString() + "',  '" + problemAssesment + "','" + journalNumber + "','" + author + "');");
            st.execute(" insert into journal (timestamp, note, caseid, author) values ('" + new Date().toString() + "',  '" + toDo + "','" + journalNumber + "','" + author + "');");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(String id, String name, String phone, String email, String address, String floor, String zipCode){
        try {
            String[] streetSplit = address.split(" ");

            String street = "";
            for (String s: streetSplit) {
                if (streetSplit.length != 1) {
                    if (s == null ? streetSplit[streetSplit.length - 1] != null : !s.equals(streetSplit[streetSplit.length - 1])) {
                        street +=  s + " ";
                    }
                }
                else {
                    street = s;
                }
            }

            street = street.trim();

            String streetNumber = streetSplit[streetSplit.length - 1];

            Statement st = db.createStatement();
            st.executeUpdate("" +
                    "begin;" +
                    "update person set name = '" + name + "', phone = '" + phone + "', email = '" + email + "' where id = '" + id + "';" +
                    "update adress set street = '" + street + "', number = '" + streetNumber + "', floor = '" + floor + "', zipcode = '" + zipCode + "' where id = '" + id + "';" +
                    "commit;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCase(String caseID, String kin){
        try {
            Statement st = db.createStatement();
            st.executeUpdate("update sag set kin = '" + kin + "' where caseid = '" + caseID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logger(String timestamp, String change, String ID, String caseID) {
        try{
            Statement st = db.createStatement();
            st.execute("insert into log (timestamp, change, id, caseid) values ('" + new Date().toString() + "',  '" + change + "','" + ID + "','" + caseID + "');");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getKin(String caseID){
        try {
            Statement st = db.createStatement();
            st.executeQuery("select kin from sag where caseid = '" + caseID + "'");
            ResultSet rs = st.getResultSet();
            String result = "";
            while(rs.next()){
                System.out.println(rs.getString(1));
                result = rs.getString(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDoctor(String caseID){
        try {
            Statement st = db.createStatement();
            st.executeQuery("select consultant from sag where caseid = '" + caseID + "'");
            ResultSet rs = st.getResultSet();
            String result = "";
            while(rs.next()){
                System.out.println(rs.getString(1));
                result = rs.getString(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}




