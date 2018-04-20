/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class NewCaseController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createCaseDoneButtonEvent(ActionEvent event) {
    }
    
}
