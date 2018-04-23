/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    @FXML
    private AnchorPane frontPage;
    @FXML
    private Label menuSensumLabel1;
    
    private DatabaseHandler dbh;
    @FXML
    private TextField txtStreetName;
    @FXML
    private TextField txtstreetNumber;
    @FXML
    private Label SearchCaseLabel;
    @FXML
    private TextField txtInsertCaseNr;
    @FXML
    private Button searchCaseButtonDone;
    @FXML
    private TextField txtInsertCprNumber;
    @FXML
    private AnchorPane showCasesPane;
    @FXML
    private ListView<?> casesListView;
    @FXML
    private Button editCaseButton;
    @FXML
    private Button deleteCaseButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dbh = new DatabaseHandler();
        // TODO
        createCasePane.setVisible(false);
        showCasesPane.setVisible(false);
       

    }    
    
    @FXML
    public void Login(ActionEvent event) {
        
        if (!locked) {
            
            if (loginUsername.getText().equalsIgnoreCase("user") &&
                loginPassword.getText().equals("password")) {

                loginSensumLabel.setText("Logged in as " + getNameFromDatabase("12345678910"));
            
                menuPane.setDisable(false);
                createCasePane.setDisable(false);
                showCasesPane.setVisible(false);
            
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
        //TODO fix dbh.getCPR() and return dbh.getCPR(CPR) instead
        return "Test";


    }


    @FXML
    private void searchCase(ActionEvent event) {
        
        showCasesPane.setVisible(true);
        loginPane.setVisible(false);
        menuPane.setVisible(false);
        
    }


    @FXML
    private void showCasesPane(ActionEvent event) {
        
        
        
        
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
        createCasePane.setVisible(true);

        
    }

    @FXML
    private void backToMenu(ActionEvent event) {
        
        //Stage stage = (Stage) backButton.getScene()
        menuPane.setVisible(true);
        
    }

    @FXML
    private void createCaseDoneButtonEvent(ActionEvent event) {
        
        String fullName = txtFirstName.getText() + " " + txtLastName.getText();
        String CPR = txtCprNumber.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmailAdress.getText();
        String street = txtStreetName.getText();
        String streetNumber = txtstreetNumber.getText();
        String floor = txtFloorNumber.getText();
        String zipCode = txtZipCode.getText();
        String journalNumber = txtJournalNumber.getText();
        String eventuelNotes = txtEventuelNotes.getText();

        Citizen citizen = new Citizen(fullName, street, streetNumber, floor, zipCode, phoneNumber, email, CPR);
        Case caseCreated = new Case(citizen, journalNumber, eventuelNotes);
        
        dbh.createCase(caseCreated);
        
        
     
        
        
    }

    @FXML
    private void createCaseCancelButton(ActionEvent event) {
        createCasePane.setVisible(false);
        menuPane.setVisible(true);
        
        
    }

    @FXML
    private void isEnterPressed(KeyEvent event) {
        
        if (event.getCode()== KeyCode.ENTER) {
            if (!locked) {
                
                if (loginUsername.getText().equalsIgnoreCase("user")
                        && loginPassword.getText().equals("password")) {
                    
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
        
    }

}
