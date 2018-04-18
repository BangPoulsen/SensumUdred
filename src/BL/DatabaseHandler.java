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
    
    public DatabaseHandler(){
        
    }
    
    public void run(){
        String url = "jdbc:postgresql://stampy.db.elephantsql.com:5432/pjgbvjcy";
        String username ="pjgbvjcy";
        String pasword = "eLDL8lqV2NwnApxtHn9DtBQorsPYEwls";
    
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try{
            db = DriverManager.getConnection(url, username, pasword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getName(String CPR){
        try {

			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT name FROM users WHERE CPRnummer = 'CPR'");

                            while (rs.next()) {
                                String name = rs.getString("CPRnummer");
                                System.out.println("Name gotten: " + name);
                                
                                return name;
                            }
                            
			} catch (Exception e) {
                            System.out.println(e);		
                        }
        return "Name not found";
    }
    
}
