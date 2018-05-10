/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

import Business.Journal;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malte
 */
public class EditCaseController extends Application implements Initializable {
    @FXML
    private ListView JournalEntries;
    @FXML
    private TextArea EntryDescription;
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
    @FXML
    private Label chosen;
    @FXML
    private TextField responsible;
    @FXML
    private TextField txtFirstNamek;
    @FXML
    private TextField txtLastNamek;
    @FXML
    private TextField txtPhonek;
    @FXML
    private TextField txtEmailk;
    @FXML
    private TextField txtRoadNamek;
    @FXML
    private TextField txtFloork;
    @FXML
    private TextField txtZipCodek;
    @FXML
    private TextField txtIdk;
    @FXML
    private TextField txtFirstNamed;
    @FXML
    private TextField txtLastNamed;
    @FXML
    private TextField txtPhoned;
    @FXML
    private TextField txtEmaild;
    @FXML
    private TextField txtRoadNamed;
    @FXML
    private TextField txtFloord;
    @FXML
    private TextField txtZipCoded;
    @FXML
    private TextArea txtProblemDescription;
    @FXML
    private TextArea txtProblemAssesment;

    @FXML
    private Button ChooseFile;
    @FXML
    private TextArea txtToDo;

    private Button UploadFile;
    @FXML
    private Path to;
    @FXML
    private Path from;
    @FXML
    private File selectedFile;
    private DatabaseHandler dbh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtToDo.setWrapText(true);
        txtProblemAssesment.setWrapText(true);
        txtProblemDescription.setWrapText(true);
        dbh = new DatabaseHandler();
        String currentUser=dbh.getCurrentUser();
        responsible.setText(currentUser);



    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EditCase.fxml"));
        Scene scene = new Scene(root);
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
        Switch.switchWindow((Stage) CancelButton.getScene().getWindow(), new SearchCasePaneController());
    }

    @FXML
    private void SaveToDatabase(ActionEvent event) {

        String problemdescription = txtProblemDescription.getText();
        String problemAssesment = txtProblemAssesment.getText();
        String toDo = txtToDo.getText();
        String author= dbh.getCurrentUser();
        //String journalNumber=dbh.searchCase();
        //dbh.updateDatabase(problemdescription,problemAssesment,toDo,author,journalNumber);





        Switch.switchWindow((Stage) SaveButton.getScene().getWindow(), new MenuController());


    }

    @FXML
    void FileChooser(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vælg fil");
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            from=Paths.get(selectedFile.toURI());
            to=Paths.get("src/" + selectedFile.getName());
            Files.copy(from,to);
        }

    }
}