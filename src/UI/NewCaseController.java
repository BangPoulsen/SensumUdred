/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import BL.Case;
import BL.Citizen;
import DL.DatabaseHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class NewCaseController extends Application implements Initializable {
    @FXML
    private AnchorPane createCasePane;


    @FXML
    private Label createCaseLabel;

    private TextField txtauthor;
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
    @FXML
    private TextField txtAuthor;
    @FXML
    private Label sensumUdredLabel4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }

    /**
     *  method that disables createCaseDonebutton if the form is empty
     */
    @FXML
    public void keyReleasedProberty(){
        
    }


    @FXML
    private void createCaseDoneButtonEvent(ActionEvent event) {
        
        String fn=txtFirstName.getText();
        String ln=txtLastName.getText();
        String cn=txtCprNumber.getText();
        String pn=txtPhoneNumber.getText();
        String ea=txtEmailAdress.getText();
        String rn=txtRoadName.getText();
        String zc=txtZipCode.getText();
        String jn=txtJournalNumber.getText();
        String en=txtEventuelNotes.getText();
        String au=txtAuthor.getText();
        
        boolean isDisabled=(fn.isEmpty()|| fn.trim().isEmpty())||(ln.isEmpty()|| ln.trim().isEmpty())
            ||(cn.isEmpty()|| cn.trim().isEmpty())||(pn.isEmpty()|| pn.trim().isEmpty())||(ea.isEmpty()|| ea.trim().isEmpty())
            ||(rn.isEmpty()|| rn.trim().isEmpty())||(zc.isEmpty()|| zc.trim().isEmpty())
            ||(jn.isEmpty()|| jn.trim().isEmpty())||(en.isEmpty()|| en.trim().isEmpty()) ||(au.isEmpty()|| au.trim().isEmpty());
        
        if (!isDisabled) {
            Switch.switchWindow((Stage) createCaseDoneButton.getScene().getWindow(), new MenuController());
            
            Random random = new Random();
            int caseID = random.nextInt(9999 - 1000 + 1) + 1000;
            ArrayList<String> caseIDs = dbh.getCIDList();
            System.out.println(caseID);
            String journalNumber = Integer.toString(caseID);
            
            while (caseIDs.contains(journalNumber)) {
                caseID = random.nextInt(9999 - 1000 + 1) + 1000;
                journalNumber = Integer.toString(caseID);
            }
            
            String fullName = txtFirstName.getText() + " " + txtLastName.getText();
            
            String CPR = txtCprNumber.getText();
            
            String phoneNumber = txtPhoneNumber.getText();
            
            String email = txtEmailAdress.getText();
            
            String[] streetSplit = txtRoadName.getText().split(" ");
            
            String street = "";
            for (String s: streetSplit
                    ) {
                if (streetSplit.length != 1) {
                    if (s != streetSplit[streetSplit.length - 1]) {
                        street = street + s + " ";
                    }
                }
                else {
                    street = s;
                }
            }
            
            street = street.trim();
            
            String streetNumber = streetSplit[streetSplit.length - 1];
            
            String floor = txtFloorNumber.getText();
            
            String zipCode = txtZipCode.getText();
            
            String password = txtJournalNumber.getText();
            
            String eventuelNotes = txtEventuelNotes.getText();
            
            String author = dbh.getCurrentUser();
            
            Citizen citizen = new Citizen(fullName, password, street, streetNumber, floor, zipCode, phoneNumber, email, CPR);
            
            Case caseCreated = new Case(citizen, journalNumber, eventuelNotes, author);
            
            dbh.createCase(caseCreated);
        } else {
            
            String isEmpty = "";
            
            if (fn.isEmpty()) isEmpty += "Fornavn, ";
            if (ln.isEmpty()) isEmpty += "Efteravn, ";
            if (cn.isEmpty()) isEmpty += "CPR nummer, ";
            if (pn.isEmpty()) isEmpty += "Tlf. nummer, ";
            if (ea.isEmpty()) isEmpty += "Email, ";
            if (rn.isEmpty()) isEmpty += "Vejnavn, ";
            if (zc.isEmpty()) isEmpty += "Postnummer, ";
            if (jn.isEmpty()) isEmpty += "Jorunalnummer, ";
            if (en.isEmpty()) isEmpty += "Notat, ";
            if (au.isEmpty()) isEmpty += "Forfatter, ";
                
            
            
            JOptionPane.showMessageDialog(null, "Udfyld venligst: " + isEmpty);
        }
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
        primaryStage.setTitle("Opret sag");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }
}
