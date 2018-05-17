/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.DatabaseHandler;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class MenuController extends Application implements Initializable {

    @FXML
    private AnchorPane menuPane;
    @FXML
    private Label menuSensumLabel1;
    @FXML
    private Button createCaseButton;
    @FXML
    private Button searchCaseButton;
    @FXML
    private Button logoffButton;
    
    private DatabaseHandler dbh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //String[] currentUserInfo =
         
        //String type = currentUserInfo[1];
         
        //System.out.println(type);
        
        // TODO
    }    


    @FXML
    private void searchCase(ActionEvent event) {
        Switch.switchWindow((Stage)searchCaseButton.getScene().getWindow(),new SearchCasePaneController());
    }

    @FXML
    private void logoff(ActionEvent event) {
        dbh = new DatabaseHandler();
        dbh.logger( new Date().toString(), "Log off ", dbh.getCurrentUser(), null);

        Switch.switchWindow((Stage)logoffButton.getScene().getWindow(),new GUIController());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene =new Scene (root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    @FXML
    private void createCase1(ActionEvent event) {
        Switch.switchWindow((Stage)createCaseButton.getScene().getWindow(),new NewCaseController());
    }
}
