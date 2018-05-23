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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Simon
 */
public class SearchCasePaneController extends Application implements Initializable {

    @FXML
    private AnchorPane searchCasePane;
    @FXML
    private Label sensumUdredLabel3;
    @FXML
    private Label searchCaseLabel;
    @FXML
    private ListView<String> listViewCases;
    @FXML
    private Label showCasesLabel;
    @FXML
    private Button searchCaseOrCprButton;
    @FXML
    private Button editCaseButton;
    @FXML
    private Button deleteCaseButton;
    @FXML
    private TextField txtEnterCprNumber;
    @FXML
    private TextField txtEnterCaseNumber;
    @FXML
    private Button mainMenuButton;
    
    private DatabaseHandler dbh;
    @FXML
    private TextField txtEnterName;
    

    /**
     * Initializes the controller class.
     * @throws java.lang.Exception
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SearchCasePane.fxml"));
        Scene scene =new Scene (root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Søg sag");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbh = new DatabaseHandler();
    }
    
    /**
     * Switches pane back to the main menu.
     * @param event called from the UI.
     */
    @FXML
    private void mainMenuButton (ActionEvent event) {
        Switch.switchWindow((Stage) this.mainMenuButton.getScene().getWindow(), new MenuController());
    }

    /**
     * Calls searchCaseMethod();
     * @param event called from the UI.
     */
    @FXML
    private void searchCaseOrCprButton(ActionEvent event) {
        SearchCaseMethod();
    }

    /**
     * Saves the selected case and switches to the EditCaseController.
     * @param event called from the UI.
     */
    @FXML
    private void editCaseButton(ActionEvent event) {
        if (listViewCases.getSelectionModel().getSelectedItem() != null) {
            String id = listViewCases.getSelectionModel().getSelectedItems().toString();
            String[] IDs = id.split(" ");
            String caseID = IDs[2].substring(0, IDs[2].length() - 1);
            String cpr = IDs[4].substring(0, IDs[4].length() - 1);

            try {
                FileOutputStream outputStream = new FileOutputStream("selectedCase");
                byte[] strBytes = caseID.getBytes();
                byte[] strBytes2 = cpr.getBytes();
                outputStream.write(strBytes);
                outputStream.write('\n');
                outputStream.write(strBytes2);
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            dbh.logger( new Date().toString(), "Edit case ", dbh.getCurrentUser(), caseID);
            Switch.switchWindow((Stage)this.mainMenuButton.getScene().getWindow(),new EditCaseController());
        } else {
            JOptionPane.showMessageDialog(null, "Vælg en sag at redigere");
        }
    }

    /**
     * 
     * @param event called from the UI.
     */
    @FXML
    private void deleteCaseButton(ActionEvent event) {
        if (!listViewCases.getSelectionModel().getSelectedItems().isEmpty()) {
            
            Alert alert = new Alert(AlertType.NONE);
            alert.setTitle("Slet sag");
            alert.setContentText("Er du sikker på at du vil slette? \n (Dette valg kan ikke fortrydes.)");
            
            ButtonType buttonTypeOne = new ButtonType("SLET");
            ButtonType buttonTypeTwo = new ButtonType("Annuller");
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == buttonTypeOne) {
                String id = listViewCases.getSelectionModel().getSelectedItems().toString();
                String[] caseID = id.split(" ");
                String finalID = caseID[4].substring(0, caseID[4].length() - 1);
                dbh.deleteInfo(finalID);
                dbh.logger( new Date().toString(), "Sag slettet", dbh.getCurrentUser(), finalID);
                SearchCaseMethod();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingen sag valgt");
        }
    }

    @FXML
    private void txtEnterName(ActionEvent event){

    }

    @FXML
    private void txtEnterCprNumber(ActionEvent event){

    }

    @FXML
    private void txtEnterCaseNumber(ActionEvent event){

    }
    
    /**
     * Searches cases based on what's written in search field and displays them on the list.
     */
    private void SearchCaseMethod() {
        //Search a case
        ResultSet results = dbh.searchCase(txtEnterName.getText());
        listViewCases.getItems().clear();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put ("caseid", "Sags ID");
        hashMap.put("name", "Navn");
        hashMap.put("citizen", "CPR");
        
        
        try {
            ResultSetMetaData rsmdt = results.getMetaData();
            String caseString;
            while (results.next()) {
                caseString = "";
                for (int i = 1; i <= rsmdt.getColumnCount(); i++) {
                    caseString = caseString + hashMap.get(rsmdt.getColumnName(i)) + ": " + results.getString(i) + ", ";
                }
                caseString.trim();
                caseString = caseString.substring(0, caseString.length()-2);
                listViewCases.getItems().add(caseString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls searchCaseMethod() when enter is pressed.
     * @param event 
     */
    @FXML
    private void isEnterPressed(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            SearchCaseMethod();
        }
    }
}
        
