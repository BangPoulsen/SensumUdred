/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DL.DatabaseHandler;
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

    private boolean locked = false;

    private DatabaseHandler dbh = new DatabaseHandler();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
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
            if (loginUsername.getText().equalsIgnoreCase("user")
                    && loginPassword.getText().equals("password")) {

                loginSensumLabel.setText("Logged in as 'Insert name of awesome person'");
                Switch.switchWindow((Stage) loginButton.getScene().getWindow(), new MenuController());
            } else {

                loginSensumLabel.setText("Login failed \t Tries left: " + tries);
                tries--;

                if (tries == 0) {
                    locked = true;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login attempts has been temporarily blocked. \t Please wait: " + "TimeLeft.Show()");
        }
    }
}
