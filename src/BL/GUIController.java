/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
        
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    public void Login(ActionEvent event) {
    
        if (loginUsername.getText().equals("user") && loginPassword.getText().equals("password")) {
            loginSensumLabel.setText("Logged in as + ");
        } else {
            loginSensumLabel.setText("Wrong username or password");
        }
            
            
    }
}
