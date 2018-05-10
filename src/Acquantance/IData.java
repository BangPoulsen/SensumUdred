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
public interface IData {
    
    
    public void createCase(Case caseI);
    
    public void closeConnection();
    
    public ResultSet searchCase(String name);
    
    public void editCase();
    
    public void deleteInfo(String id);
    
    public boolean loginAttempt(String username, String userPassword);
    
    public String getId(String username);
    
    public ArrayList<String> getCaseIDList();
    
    public ResultSet getCitizenInfo(String id);
    
    public ResultSet getCitizenInfo();
    
    public String getCurrentUser();
    
    public void writeDate2file(long lockedDate);
    
    public long readDate();
    
    
    
    
}
