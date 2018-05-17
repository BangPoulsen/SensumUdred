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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorSearchCasePane extends Application implements Initializable{

	@FXML
	private AnchorPane searchCasePane;

	@FXML
	private Label sensumUdredLabel3;

	@FXML
	private Label searchCaseLabel;

	@FXML
	private TextField txtEnterCprNumber;

	@FXML
	private TextField txtEnterCaseNumber;

	@FXML
	private ListView<?> listViewCases;

	@FXML
	private Label showCasesLabel;

	@FXML
	private Button searchCaseOrCprButton;

	@FXML
	private Button editCaseButton;

	@FXML
	private Button mainMenuButton;

	@FXML
	private TextField txtEnterName;

	private DatabaseHandler dbh;

	@FXML
	void editCaseButton(ActionEvent event) {

	}

	@FXML
	void isEnterPressed(KeyEvent event) {

	}

	@FXML
	void mainMenuButton(ActionEvent event) {

	}

	@FXML
	void searchCaseOrCprButton(ActionEvent event) {

	}

	@FXML
	void txtEnterCaseNumber(ActionEvent event) {

	}

	@FXML
	void txtEnterCprNumber(ActionEvent event) {

	}

	@FXML
	void txtEnterName(ActionEvent event) {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("DoctorSearchCasePane.fxml"));
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
}
