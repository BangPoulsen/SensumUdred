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
import javafx.scene.control.TextArea;
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
    private Button backButton;
    @FXML
    private AnchorPane createCasePane;
    @FXML
    private Label menuSensumLabel2;
    @FXML
    private Label createCaseLabel;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtCprNumber;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmailAdress;
    @FXML
    private TextField txtRoadName;
    @FXML
    private TextField txtFloorNumber;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtJournalNumber;
    @FXML
    private TextArea txtEventuelNotes;
    @FXML
    private Button createCaseDoneButton;
    @FXML
    private Button createCaseCancelButton;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private AnchorPane loginPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        menuPane.setDisable(true);
        createCasePane.setDisable(true);
       

    }    
    
    @FXML
    public void Login(ActionEvent event) {
        
        if (!locked) {
            
            if (loginUsername.getText().equalsIgnoreCase("user") &&
                loginPassword.getText().equals("password")) {

                loginSensumLabel.setText("Logged in as " + getNameFromDatabase("12345678910"));
            
                menuPane.setDisable(false);
                createCasePane.setDisable(false);
            
                //removes and resets login screen
                
                loginPane.setVisible(false);
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
        
        menuPane.setDisable(true);
        createCasePane.setDisable(true);
        loginPane.setVisible(true);
    }

    @FXML
    private void exit(ActionEvent event) {
        
        Stage stage = (Stage) loginButton.getScene().getWindow();
        
        stage.close();
    }

    @FXML
    private void createCase(ActionEvent event) {
        
        menuPane.setVisible(false);

        
    }

    @FXML
    private void backToMenu(ActionEvent event) {
        
        //Stage stage = (Stage) backButton.getScene()
        menuPane.setVisible(true);
        
    }

}
