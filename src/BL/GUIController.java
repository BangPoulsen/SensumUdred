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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

        
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
    @FXML
    private Button exitButton;
    
    private int tries = 3;
    private boolean locked = false;
    @FXML
    private Button backToMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loginPage.setVisible(true);
        menuPage.setVisible(true);
        // TODO
       

    }    
    
    @FXML
    public void Login(ActionEvent event) {
        
        if (!locked) {
            
            if (loginUsername.getText().equalsIgnoreCase("user") &&
                loginPassword.getText().equals("password")) {

                loginSensumLabel.setText("Logged in as " + getNameFromDatabase("12345678910"));
            
            
            
                //removes and resets login screen
                loginPage.setVisible(false);
                loginUsername.setText("");
                loginPassword.setText("");
                loginSensumLabel.setText("Sensum Udred");
            
            
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

    private String getNameFromDatabase(String CPR) {
        //Return the name of a person in database
        DatabaseHandler dbh = new DatabaseHandler();
        return dbh.getCPR(CPR);


    }


    @FXML
    private void searchCase(ActionEvent event) {
    }

    @FXML
    private void logoff(ActionEvent event) {
        

        loginPage.setVisible(true);
    }

    @FXML
    private void exit(ActionEvent event) {
        
        Stage stage = (Stage) loginButton.getScene().getWindow();
        
        stage.close();
    }

    @FXML
    private void createCase(ActionEvent event) {
        
        menuPage.setVisible(false);

        
    }

    @FXML
    private void backToMenu(ActionEvent event) {
        
        menuPage.setVisible(true);
        
    }

}
