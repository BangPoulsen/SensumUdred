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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.scene.control.ListView;
import javax.swing.JOptionPane;


public class Admin extends Application implements Initializable {



	@FXML
	private ChoiceBox<String> Choicebox;

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
        private ListView<String> txtListUsers;
        
        private DatabaseHandler dbh;
        
        @FXML
        private ListView<String> txtLog;

        
        /**
         * 
         * @param event 
         */
	@FXML
	void deleteUser(ActionEvent event) {
            
            if (txtListUsers.getSelectionModel().getSelectedItem() != null) {
                String personInfo = txtListUsers.getSelectionModel().getSelectedItem();
                
                String[] personInfoSplitted = personInfo.split(" ");
                
                String selectedID = personInfoSplitted[1].substring(0, personInfoSplitted[1].length() - 1);
                
                System.out.println("ID: " + selectedID);
                
                dbh.deleteInfo(selectedID);
                
                JOptionPane.showMessageDialog(null, "Bruger slettet");
                
                dbh.logger( new Date().toString(), "Deleted user", dbh.getCurrentUser(), null);
                
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(null, "Vælg en bruger");
            }
            
            

	}

	@FXML
	void logOff(ActionEvent event) {
            
            dbh.logger( new Date().toString(), "Log off ", dbh.getCurrentUser(), null);
            dbh.closeConnection();
            
            Switch.switchWindow((Stage)LogOff.getScene().getWindow(),new GUIController());

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
		Choicebox.getItems().removeAll(Choicebox.getItems());
		Choicebox.getItems().addAll("Sagsbehandler", "Læge", "Støtte");
		Choicebox.getSelectionModel().select("Sagsbehandler");
                
                dbh = new DatabaseHandler();
                
                loadUsers();

	}

    private void loadUsers() {
        //Search a case
        
        ResultSet results = dbh.getUsers();
        
        txtListUsers.getItems().clear();

        HashMap<String, String> hashMap = new HashMap<>();
        
        hashMap.put("name", "Navn");
        hashMap.put("id", "Bruger");
        hashMap.put("type", "Type");
        
        
        try {
            ResultSetMetaData rsmdt = results.getMetaData();
            String caseString;
            while (results.next()) {
                
                
                    caseString = hashMap.get(rsmdt.getColumnName(2)) + ": " + results.getString(2) + ", " + 
                                 hashMap.get(rsmdt.getColumnName(1)) + ": " + results.getString(1) + ", " +
                                 hashMap.get(rsmdt.getColumnName(3)) + ": " + results.getString(3);
                
                
                txtListUsers.getItems().add(caseString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void CreateUser(ActionEvent event) {
        
        String name = txtUserName.getText();
        String email = txtUserEmail.getText();
        String phoneNumber = txtUserPhone.getText();
        String id = txtUserId.getText();
        String password = txtUserPassword.getText();
        String type = Choicebox.getSelectionModel().getSelectedItem();
        
        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || id.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Udfyld alle felter.");
        } else {
            dbh.createUser(name, email, phoneNumber, "", "", "", id, password, type);
            
            JOptionPane.showMessageDialog(null, "Bruger oprettet!");
            
            dbh.logger( new Date().toString(), "Created user", dbh.getCurrentUser(), null);
            
            loadUsers();
            
            txtUserName.setText("");
            txtUserEmail.setText("");
            txtUserPhone.setText("");
            txtUserId.setText("");
            txtUserPassword.setText("");
        }
        
    }

    @FXML
    private void getLogsClicked(Event event) {
        
        
        ResultSet results = dbh.getLogs();
        
        txtLog.getItems().clear();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put ("caseid", "Sags ID");
        hashMap.put("id", "Navn");
        hashMap.put("change", "Ændring");
        hashMap.put("timestamp", "Tid");
        hashMap.put("lognumber", "Log nummer");
        
        
        try {
            ResultSetMetaData rsmdt = results.getMetaData();
            String logs;
            while (results.next()) {
                logs = "";
                for (int i = 1; i <= rsmdt.getColumnCount(); i++) {
                    logs = logs + hashMap.get(rsmdt.getColumnName(i)) + ": " + results.getString(i).trim() + "\t \t \t";
                }
                
                txtLog.getItems().add(logs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }



}


