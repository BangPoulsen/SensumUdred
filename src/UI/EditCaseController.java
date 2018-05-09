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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class EditCaseController extends Application implements Initializable {

    @FXML
    private AnchorPane SubTabPaneBorger;
    @FXML
    private Button CancelButton;
    @FXML
    private Button SaveButton;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtCPRNumber;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRoadName;
    @FXML
    private TextField txtFloor;
    @FXML
    private TextField txtZipCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtToDo.setWrapText(true);
        txtProblemAssesment.setWrapText(true);
        txtProblemDescription.setWrapText(true);    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EditCase.fxml"));
        Scene scene =new Scene (root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rediger sag");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    @FXML
    private void ReturnToSearch(ActionEvent event) {
        Switch.switchWindow((Stage)CancelButton.getScene().getWindow(),new SearchCasePaneController());
    }

    @FXML
    private void SaveToDatabase(ActionEvent event) {
        Switch.switchWindow((Stage) SaveButton.getScene().getWindow(), new MenuController());
    }
    @FXML
    void UploadToDatebase(ActionEvent event) {

    }
    @FXML
    void FileChooser(ActionEvent event) {

    }
