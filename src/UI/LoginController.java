/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Date;

import Data.DatabaseHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


import javax.swing.*;
import javax.xml.crypto.Data;

/**
     * FXML Controller class
     *
     * @author malte
     */
    public class LoginController extends Application implements Initializable {

    private Stage stage;

    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginSensumLabel;

    private int tries = 3;

    
    private int secondsWait = 120;
    
    private DatabaseHandler dbh = new DatabaseHandler();
    
    private long lockedDate;
    
    
    private boolean locked = isLocked();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lockedDate = dbh.readDate();
    }

    @FXML
    public void Login(ActionEvent event) throws IOException {
        loginMethod();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @FXML
    private void isEnterPressed(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER) {
            loginMethod();
        }
    }

    private void loginMethod() {
        if (!locked) {
            
            String username = loginUsername.getText();
            String password = loginPassword.getText();

            if (dbh.loginAttempt(username, password)) {
                System.out.println(dbh.getCurrentUser());
                switch (dbh.getId(username)){
                    case "Borger":
                        Switch.switchWindow((Stage) loginButton.getScene().getWindow(), new ViewCaseController());
                        break;
                    case "Sagsbehandler":
                        Switch.switchWindow((Stage) loginButton.getScene().getWindow(), new MenuController());
                        break;
                    case "admin":
                        Switch.switchWindow((Stage) loginButton.getScene().getWindow(), new Admin());
                        break;
                }
            } else {
                tries--;

                if (tries == 0) {
                    lockedDate = new Date().getTime();
                    dbh.writeDate2file(lockedDate);
                    locked = true;
                }
                
                loginSensumLabel.setText("Login failed \t Tries left: " + tries);
            }
        } else {
            if (isLocked()) {
                locked = false;
            } else  {
                
                int timeLeft = secondsWait - getSecondsPassed();
                
                JOptionPane.showMessageDialog(null, "Login attempts has been temporarily blocked. \t Please wait : " + timeLeft + " seconds.");
            }
        }
    }
    
    private int getSecondsPassed(){
            lockedDate = dbh.readDate();            
            
            secondsWait = 120;
            
            long currentDate = new Date().getTime();
            
            long timePassed = currentDate - lockedDate;
            
            return (int)(timePassed / 1000);
    }

    private boolean isLocked() {
        
            lockedDate = dbh.readDate();            
            
            secondsWait = 120;
            
            long currentDate = new Date().getTime();
            
            long timePassed = currentDate - lockedDate;
            
            int secondsPassed = (int)(timePassed / 1000);
            
            return secondsPassed > secondsWait;
    }
}
