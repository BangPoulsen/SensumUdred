/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DL.DatabaseHandler;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

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
    private ListView<?> listViewCases;
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
     */
    @FXML
    private void mainMenuButton (ActionEvent event) {
        Switch.switchWindow((Stage)this.mainMenuButton.getScene().getWindow(),new MenuController());

    
    }

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

    @FXML
    private void searchCaseOrCprButton(ActionEvent event) {
        
        //Search a case
        dbh.searchCase();
        
    }

    @FXML
    private void editCaseButton(ActionEvent event) {
        
        dbh.editCase();
        
    }

    @FXML
    private void deleteCaseButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Slet sag");
        alert.setContentText("Er du sikker på at du vil slette? \n (Dette valg kan ikke fortrydes.)");
        ButtonType buttonTypeOne=new ButtonType("SLET");
        ButtonType buttonTypeTwo=new ButtonType("Annuller");

        alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        
        dbh.deleteCase();
        
    }

    @FXML
    private void txtEnterName(ActionEvent event) {
        
    }

    @FXML
    private void txtEnterCprNumber(ActionEvent event) {
    }

    @FXML
    private void txtEnterCaseNumber(ActionEvent event) {
    }

    }
        
