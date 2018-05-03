/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import DL.DatabaseHandler;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
    private ListView<?> txtViewNotes;
    @FXML
    private Label showCasesLabel;
    @FXML
    private AnchorPane showNoteInCasesPane;
    @FXML
    private Label showNoteAuthorLabel;
    @FXML
    private TextField txtNoteInCases;
    @FXML
    private Button closeNoteButton;

    private DatabaseHandler dbh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dbh = new DatabaseHandler();
        ResultSet info = dbh.getCitizenInfo();
        
        String userInfo;
        
        try {
            while(info.next()){
                userInfo = "";
                for (int i = 1; i <= rsmdt.getColumnCount(); i++) {
                    userInfo += hashMap.get(rsmdt.getColumnName(i)) + ": " + results.getString(i) + ", ";
                }
                caseString.trim();
                caseString = caseString.substring(0, caseString.length()-2);
                listViewCases.getItems().add(caseString);
            }
            
            /*sag.caseid,
            person.name,
            person.id,
            person.phone,
            person.email,
            adress.street,
            adress.number,
            adress.floor,
            adress.zipcode*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void LogOffEvent(ActionEvent event) {
        Switch.switchWindow((Stage)logoffButton.getScene().getWindow(),new GUIController());
    }

    @FXML
    private void keyReleasedProberty(KeyEvent event) {
    }


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

    @FXML
    private void closeNoteButtonHandler(ActionEvent event) {
        
        showNoteInCasesPane.setVisible(false);
        
        
    }

    @FXML
    private void ViewNoteEventHandler(ActionEvent event) {
        
        showNoteInCasesPane.setVisible(true);
    }

}