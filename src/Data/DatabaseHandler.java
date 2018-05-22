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
import java.util.Random;
import java.util.Scanner;

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
            Statement st = db.createStatement();
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
            String jNumber = getRandomJID();
            Statement st = db.createStatement();
            st.executeUpdate("" +
                "begin; " +
                "insert into person (type, password, id, email, phone, name) values ('Borger', '" + password + "', '" + CPR + "', '" + email + "', '" + phoneNumber + "', '" + fullName + "'); " +
                "insert into adress (id, street, number, floor, zipcode) values ('" + CPR + "', '" + street + "', '" + streetNumber + "', '" + floor + "', '" + zipCode + "');" +
                "insert into sag (caseid, kin, support, consultant, responsible, citizen) values ('" + journalNumber + "', 'NULL', 'NULL', 'NULL', 'NULL', '" + CPR + "');" +
                "insert into journal (timestamp, note, caseid, author, jid) values ('" + new Date().toString() + "',  '" + note + "','" + journalNumber + "','" + author + "', '" + jNumber + "');" +
                "commit;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the established connection to the sql server.
     */
    public void closeConnection() {
        try {
            db.close();
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
                "delete from journal using sag where journal.caseid = sag.caseid and sag.citizen = '" + id + "'; " +
                "delete from sag where sag.citizen = '" + id + "'; " +
                "delete from person where person.id = '" + id + "'; " +
                "commit;");
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
            ResultSet rs = st.executeQuery("SELECT id FROM Person WHERE id = '" + username +
                    "' AND password = '" + userPassword + "';");

            while (rs.next()) {

                String id = rs.getString("id");

                user = id;

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    /**
     * Fetches a list of the current Users in the fatabase using an sql statement.
     *
     * @param type A String containing information pertaining as to what occupation the person has.
     * @return A resultset of users.
     */
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

    /**
     * Gets the logs written in the database.
     *
     * @return A resultset containing everything from the table log.
     */
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
     * @param caseID A string containing the case ID.
     */
    public void updateJournal(String problemDescription, String problemAssesment, String toDo, String author, String caseID){
        try{
            String jNumber = getRandomJID();
            Statement st = db.createStatement();
            st.execute(" insert into journal (timestamp, note, caseid, author, jID) values ('" + new Date().toString() + "',  'Problem: "+ problemDescription + "','" + caseID + "','" + author + "', '" + jNumber + "');");
            jNumber = getRandomJID();
            st.execute(" insert into journal (timestamp, note, caseid, author, jID) values ('" + new Date().toString() + "',  'Vurdering: " + problemAssesment + "','" + caseID + "','" + author + "', '" + jNumber + "');");
            jNumber = getRandomJID();
            st.execute(" insert into journal (timestamp, note, caseid, author, jID) values ('" + new Date().toString() + "',  'Indsats: " + toDo + "','" + caseID + "','" + author + "', '" + jNumber + "');");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to update a persons information within the database.
     *
     * @param id
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param floor
     * @param zipCode
     */
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

    /**
     * Attempts to update a case within the database. currently only affects its kin column.
     *
     * @param caseID
     * @param kin A string that contains a kins PersonID
     */
    public void updateCase(String caseID, String kin){
        try {
            Statement st = db.createStatement();
            st.executeUpdate("update sag set kin = '" + kin + "' where caseid = '" + caseID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs information about an event where things are changed in the system.
     *
     * @param timestamp
     * @param change A String containing the changes made.
     * @param ID A String containing PersonID
     * @param caseID
     */
    public void logger(String timestamp, String change, String ID, String caseID) {
        try{
            Statement st = db.createStatement();
            st.execute("insert into log (timestamp, change, id, caseid) values ('" + new Date().toString() + "',  '" + change + "','" + ID + "','" + caseID + "');");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a kin from a specific case.
     *
     * @param caseID
     * @return A string containing the PersonID of the kin.
     */
    public String getKin(String caseID){
        try {
            Statement st = db.createStatement();
            st.executeQuery("select kin from sag where caseid = '" + caseID + "'");
            ResultSet rs = st.getResultSet();
            String result = "";
            while(rs.next()){
                result = rs.getString(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets a doctor from a specific case.
     *
     * @param caseID
     * @return A string containing the PersonID of the doctor.
     */
    public String getDoctor(String caseID){
        try {
            Statement st = db.createStatement();
            st.executeQuery("select consultant from sag where caseid = '" + caseID + "'");
            ResultSet rs = st.getResultSet();
            String result = "";
            while(rs.next()){
                result = rs.getString(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getRandomJID() {
        Random random = new Random();
        int caseID = random.nextInt(99999 - 10000 + 1) + 10000;

        ArrayList<String> jIDs = new ArrayList<>();
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT jid FROM journal;");

            while (rs.next()) {
                String id = rs.getString("jid");
                jIDs.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String journalNumber = Integer.toString(caseID);

        while (jIDs.contains(journalNumber)) {
            caseID = random.nextInt(9999 - 1000 + 1) + 1000;
            journalNumber = Integer.toString(caseID);
        }
        return journalNumber;
    }

    public ResultSet getJournals(String caseID){
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT timestamp, caseid, author, note from journal where caseid = '" + caseID + "'");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}




