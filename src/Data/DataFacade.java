/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Acquantance.IDataFacade;
import Business.Case;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author malte
 */
public class DataFacade implements IDataFacade {
    
    DatabaseHandler dbh = new DatabaseHandler();

    @Override
    public void createCase(Case caseI) {
         dbh.createCase(caseI);
    }

    @Override
    public void closeConnection() {
        dbh.closeConnection();
    }

    @Override
    public ResultSet searchCase(String name) {
        return dbh.searchCase(name);
    }

    @Override
    public void editCase() {
        editCase();
    }

    @Override
    public void deleteInfo(String id) {
        dbh.deleteInfo(id);
    }

    @Override
    public boolean loginAttempt(String username, String userPassword) {
        return dbh.loginAttempt(username, userPassword);
    }

    @Override
    public String getId(String username) {
        return dbh.getId(username);
    }

    @Override
    public ArrayList<String> getCIDList() {
        return dbh.getCIDList();
    }

    @Override
    public ResultSet getCitizenInfo(String id) {
        return dbh.getCitizenInfo(id);
    }

    @Override
    public ResultSet getCitizenInfo() {
        return dbh.getCitizenInfo();
    }

    @Override
    public String getCurrentUser() {
        return dbh.getCurrentUser();
    }

    @Override
    public void writeDate2file(long lockedDate) {
        dbh.writeDate2file(lockedDate);
    }

    @Override
    public long readDate() {
        return dbh.readDate();
    }

    
}