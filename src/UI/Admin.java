package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Admin extends Application implements Initializable {



	@FXML
	private ChoiceBox<?> Choicebox;

	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtUserEmail;

	@FXML
	private TextField txtUserPhone;

	@FXML
	private TextField txtUserId;

	@FXML
	private TextField txtUserPassword;

	@FXML
	private Button CreateUser;

	@FXML
	private Button DeleteUser;

	@FXML
	private Button LogOff;

	@FXML
	void CreateUSer(ActionEvent event) {

	}

	@FXML
	void deleteUser(ActionEvent event) {

	}

	@FXML
	void logOff(ActionEvent event) {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
		Scene scene =new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Opret bruger");
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


