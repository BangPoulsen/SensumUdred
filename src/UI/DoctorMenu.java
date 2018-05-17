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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class DoctorMenu extends Application implements Initializable {

	@FXML
	private Button searchCaseButtondoctor;

	@FXML
	private Button logoffButton;
	private DatabaseHandler dbh;
	@FXML
	void logoff(ActionEvent event) {
		dbh = new DatabaseHandler();
		dbh.logger( new Date().toString(), "Log off ", dbh.getCurrentUser(), null);
		Switch.switchWindow((Stage)logoffButton.getScene().getWindow(),new GUIController());
	}

	@FXML
	void searchCasedoctor(ActionEvent event) {
		Switch.switchWindow((Stage)searchCaseButtondoctor.getScene().getWindow(),new DoctorSearchCasePane());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("DoctorMenu.fxml"));

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
