package BL;

        import java.sql.*;
        import java.util.Scanner;


public class Main {
    public Main() throws SQLException {
    }


    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    String url ="jdbc:postresql://stampy.db.elephantsql.com:5432/pjgbvjcy";
    String username ="pjgbvjcy";
    String pasword = "eLDL8lqV2NwnApxtHn9DtBQorsPYEwls";

    Connection db = DriverManager.getConnection(url, username, pasword);
    //for testing
    //Caseworker cw=new Caseworker("ole", "månen", "112", "cirkus@blabla.dk");
}



