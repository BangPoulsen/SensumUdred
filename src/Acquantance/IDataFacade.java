/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquantance;

import Business.Case;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author malte
 */
public interface IDataFacade {
    
    
    public void createCase(Case caseI);
    
    public void closeConnection();
    
    public ResultSet searchCase(String name);
    
    public ResultSet getTimeStamp(String caseID);
    
    public void editCase();
    
    public void deleteCase(String id);
    
    public boolean loginAttempt(String username, String userPassword);
    
    public String getId(String username);
    
    public ArrayList<String> getCIDList();
    
    public ResultSet getCitizenInfo(String id);
    
    public ResultSet getCitizenInfo();
    
    public String getCurrentUser();
    
    public void writeDate2file(long lockedDate);
    
    public long readDate();
    
    public String getUserInfo();
    
    
    
}
