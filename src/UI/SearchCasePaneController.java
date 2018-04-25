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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
        primaryStage.setTitle("SearchCase");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    }
