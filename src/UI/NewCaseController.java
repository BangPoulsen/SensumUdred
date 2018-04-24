/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class NewCaseController extends Application implements Initializable {

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
        Switch.switchWindow((Stage)createCaseDoneButton.getScene().getWindow(),new MenuController());
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
