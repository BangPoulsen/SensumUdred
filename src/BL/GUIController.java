/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
        
/**
 * FXML Controller class
 *
 * @author Simon
 */
public class GUIController extends Application implements Initializable {

    @FXML
    private AnchorPane frontPage;
    @FXML
    private AnchorPane loginPage;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoffButton;
    @FXML
    private Label loginSensumLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    
    String url ="jdbc:postresql://stampy.db.elephantsql.com:5432/pjgbvjcy";
    String username ="pjgbvjcy";
    String pasword = "eLDL8lqV2NwnApxtHn9DtBQorsPYEwls";
    
    try{
        Connection db = DriverManager.getConnection(url, username, pasword);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
        launch(args);
        
        
    }
    public void Login(ActionEvent event) {
    
        if (loginUsername.getText().equals("user") && loginPassword.getText().equals("password")) {
            loginSensumLabel.setText("Logged in as + ");
        } else {
            loginSensumLabel.setText("Wrong username or password");
        }
            
            
    }
}
