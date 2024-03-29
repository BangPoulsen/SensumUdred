/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.DatabaseHandler;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class ViewCaseController extends Application implements Initializable {
    @FXML
    private AnchorPane createCasePane;
    @FXML
    private Label createCaseLabel;
    @FXML
    private Button logoffButton;
    @FXML
    private Button viewSelectedNote;
    @FXML
    private Label sensumUdredLabel4;
    @FXML
    private Label txtFirstName;
    @FXML
    private Label txtLastName;
    @FXML
    private Label txtCprNumber;
    @FXML
    private Label txtPhoneNumber;
    @FXML
    private Label txtEmailAdress;
    @FXML
    private Label txtRoadName;
    @FXML
    private Label txtFloorNumber;
    @FXML
    private Label txtZipCode;
    @FXML
    private Label txtJournalNumber;
    @FXML
    private ListView<String> txtViewNotes;
    @FXML
    private Label showCasesLabel;
    @FXML
    private AnchorPane showNoteInCasesPane;
    @FXML
    private Label showNoteAuthorLabel;
    @FXML
    private TextArea txtNoteInCases;
    @FXML
    private Button closeNoteButton;

    private DatabaseHandler dbh;
    private ResultSet journal;

    /**
     * Initializes the controller class and displays the information of the current user.
     * @param url a URL.
     * @param rb A ResourceBundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbh = new DatabaseHandler();
        ResultSet info = dbh.getCitizenInfo();
        String userInfo = "";
        try {
            ResultSetMetaData rsmdt = info.getMetaData();
            while(info.next()){
                for (int i = 1; i <= rsmdt.getColumnCount(); i++) {
                    userInfo = userInfo + info.getString(i) + ", ";
                    System.out.println(userInfo);
                }
            }

            String[] CitizenInfo = userInfo.split(", ");
            String caseid = CitizenInfo[0];
            String fullName = CitizenInfo[1];
            String id = CitizenInfo[2];
            String mobileNumber = CitizenInfo[3];
            String email  = CitizenInfo[4];
            String roadName = CitizenInfo[5] + " " + CitizenInfo[6];
            String floor = CitizenInfo[7];
            String zipcode = CitizenInfo[8];
            String[] fullNameSplit = fullName.split(" ");
            System.out.println(fullName);

            if(fullNameSplit.length > 2){
                String firstName = "";
                for(int i = 0; i < fullNameSplit.length-2; i++){
                    firstName += fullNameSplit[i] + " ";
                }
                firstName += fullNameSplit[fullNameSplit.length-2];
                txtFirstName.setText("Fornavn: " + firstName);
                txtLastName.setText("Efternavn: " + fullNameSplit[fullNameSplit.length-1]);
            } else if(fullNameSplit.length == 2) {
                txtFirstName.setText("Fornavn: " + fullNameSplit[0]);
                txtLastName.setText("Efternavn: " + fullNameSplit[1]);
            } else {
                txtFirstName.setText("Fornavn: " + fullNameSplit[0]);
            }
            txtCprNumber.setText("Personnummer: " + id);
            txtFloorNumber.setText("Etage: " + floor);
            txtJournalNumber.setText("Sagsnummer: " + caseid);
            txtZipCode.setText("Postnummer: " + zipcode);
            txtPhoneNumber.setText("Telefon: " + mobileNumber);
            txtEmailAdress.setText("Email: " + email);
            txtRoadName.setText("Vejnavn: " + roadName);

            journal = dbh.getJournal(id);
            String timestamp = "";
            while (journal.next()){
                timestamp = journal.getString(2);
                txtViewNotes.getItems().add(timestamp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Logs the current user off.
     * @param event Called from the UI.
     */
    @FXML
    private void LogOffEvent(ActionEvent event) {
        Switch.switchWindow((Stage)logoffButton.getScene().getWindow(),new GUIController());
    }
    
    /**
     * Opens the GUI of the viewCaseController.
     * @param primaryStage The primary stage of the application.
     * @throws Exception Throws an exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ViewCase.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ViewCase");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    /**
     * Closes the note of a case.
     * @param event Called from the UI.
     */
    @FXML
    private void closeNoteButtonHandler(ActionEvent event) {
        showNoteInCasesPane.setVisible(false);
    }

    @FXML
    private void keyReleasedProberty(ActionEvent event){

    }
    
    /**
     * Views a selected note from the list. 
     * @param event Called from the UI.
     */
    @FXML
    private void ViewNoteEventHandler(ActionEvent event) {
        if (txtViewNotes.getSelectionModel().getSelectedItem()!= null) {
            showNoteInCasesPane.setVisible(true);
            int index = txtViewNotes.getSelectionModel().getSelectedIndex();
            try {
                if (journal.absolute(index + 1)) {
                    showNoteAuthorLabel.setText(journal.getString(3));
                    txtNoteInCases.setText(journal.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vælg et notat.");
        }
    }
}