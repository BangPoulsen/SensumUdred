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
public class GUIController implements Initializable {

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
    @FXML
    private AnchorPane menuPage;
    @FXML
    private Label menuSensumLabel1;
    @FXML
    private Button createCaseButton;
    @FXML
    private Button searchCaseButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
<<<<<<< HEAD
    }    
    
    
    
    @FXML
=======
    }


>>>>>>> 6f3e2cfaaf73fff0c180538c152fc0602b95886e
    public void Login(ActionEvent event) {

        if (loginUsername.getText().equalsIgnoreCase("user") &&
            loginPassword.getText().equals("password")) {

            loginSensumLabel.setText("Logged in as " + getNameFromDatabase("12345678910"));
        } else {
            loginSensumLabel.setText("Login failed");
        }

        System.out.println("Hello");
    }

    private String getNameFromDatabase(String CPR) {
        //Return the name of a person in database
        DatabaseHandler dbh = new DatabaseHandler();
        return dbh.getCPR(CPR);


    }

}
