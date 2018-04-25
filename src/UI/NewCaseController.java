/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;

import BL.Case;
import BL.Citizen;
import DL.DatabaseHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class NewCaseController extends Application implements Initializable {
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

    private DatabaseHandler dbh = new DatabaseHandler();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createCaseDoneButton.setDisable(true);
        // TODO
    }

    /**
     *  method that disables createCaseDonebutton if the form is empty
     */
    public void keyReleasedProberty(){
        String fn=txtFirstName.getText();
        String ln=txtLastName.getText();
        String cn=txtCprNumber.getText();
        String pn=txtPhoneNumber.getText();
        String ea=txtEmailAdress.getText();
        String rn=txtRoadName.getText();
        String fln=txtFloorNumber.getText();
        String zc=txtZipCode.getText();
        String jn=txtJournalNumber.getText();
        String en=txtEventuelNotes.getText();
        boolean isDisabled=(fn.isEmpty()|| fn.trim().isEmpty())||(ln.isEmpty()|| ln.trim().isEmpty())
            ||(cn.isEmpty()|| cn.trim().isEmpty())||(pn.isEmpty()|| pn.trim().isEmpty())||(ea.isEmpty()|| ea.trim().isEmpty())
            ||(rn.isEmpty()|| rn.trim().isEmpty())||(zc.isEmpty()|| zc.trim().isEmpty())
            ||(jn.isEmpty()|| jn.trim().isEmpty())||(en.isEmpty()|| en.trim().isEmpty());
            createCaseDoneButton.setDisable(isDisabled);

    }

    @FXML
    private void createCaseDoneButtonEvent(ActionEvent event) {
        Switch.switchWindow((Stage)createCaseDoneButton.getScene().getWindow(),new MenuController());
        String fullName = txtFirstName.getText() + " " + txtLastName.getText();

        String CPR = txtCprNumber.getText();

        String phoneNumber = txtPhoneNumber.getText();

        String email = txtEmailAdress.getText();

        String street = txtRoadName.getText();

        String[] streetN = street.split(" ");
        String streetNumber = streetN[streetN.length-1];

        String floor = txtFloorNumber.getText();

        String zipCode = txtZipCode.getText();

        String journalNumber = txtJournalNumber.getText();

        String eventuelNotes = txtEventuelNotes.getText();



        Citizen citizen = new Citizen(fullName, street, streetNumber, floor, zipCode, phoneNumber, email, CPR);

        Case caseCreated = new Case(citizen, journalNumber, eventuelNotes);



        dbh.createCase(caseCreated);
    }
    @FXML
    private void createCaseCancelButton (ActionEvent event) {
        Switch.switchWindow((Stage)createCaseCancelButton.getScene().getWindow(),new MenuController());

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NewCase.fxml"));

        Scene scene =new Scene (root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("newCase");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

    }
}
