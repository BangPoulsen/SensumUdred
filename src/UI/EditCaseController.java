/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

import Data.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javax.swing.JOptionPane;

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
    private Path to;
    private Path from;
    private File selectedFile;
    private DatabaseHandler dbh;
    private String CPR;
    private String caseID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbh = new DatabaseHandler();
        txtToDo.setWrapText(true);
        txtProblemAssesment.setWrapText(true);
        txtProblemDescription.setWrapText(true);
        String currentUser=dbh.getCurrentUser();
        responsible.setText(currentUser);

        caseID = null;
        CPR = null;
        File file = new File("selectedCase");

        try {
            FileInputStream input = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            caseID = reader.readLine();
            CPR = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setCitizen();
        setKin();
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

    /**
     * returns you to the last window, does this by calling it directly.
     * @param event
     */
    @FXML
    private void ReturnToSearch(ActionEvent event) {
        Switch.switchWindow((Stage) CancelButton.getScene().getWindow(), new SearchCasePaneController());
    }

    /**
     * Saves various information about a case from text boxes to the database, and updates the information all the right places in it.
     * @param event
     */
    @FXML
    private void SaveToDatabase(ActionEvent event) {

        if (!txtProblemDescription.getText().isEmpty() && !txtProblemAssesment.getText().isEmpty() && !txtToDo.getText().isEmpty() ) {
            String problemdescription = txtProblemDescription.getText();
            String problemAssesment = txtProblemAssesment.getText();
            String toDo = txtToDo.getText();
            String author = dbh.getCurrentUser();
            dbh.updateJournal(problemdescription,problemAssesment,toDo,author, caseID);

            ResultSet kin = dbh.getUsers("Pårørende");

            Boolean kinExist = false;

            try{
                String name = txtFirstNamek.getText() + " " + txtLastNamek.getText();
                while (kin.next()){
                    System.out.println(kin.getString(2));
                    if(kin.getString(2).equals(txtIdk.getText())){
                        //todo update database
                        kinExist = true;
                        dbh.updatePerson(txtIdk.getText(), name, txtPhonek.getText(), txtEmailk.getText(), txtRoadNamek.getText(), txtFloork.getText(), txtZipCodek.getText());
                    }
                }
                if(!kinExist){
                    dbh.createUser(name, txtEmailk.getText(), txtPhonek.getText(), txtRoadNamek.getText(), txtFloork.getText(),txtZipCodek.getText(), txtIdk.getText(), "kin", "Pårørende");
                    dbh.updateCase(caseID, txtIdk.getText());
                }

                name = txtFirstName.getText() + " " + txtLastName.getText();
                dbh.updatePerson(txtCPRNumber.getText(), name, txtPhone.getText(), txtEmail.getText(), txtRoadName.getText(), txtFloor.getText(), txtZipCode.getText());
            } catch (SQLException e){
                e.printStackTrace();
            }
            
            dbh.logger( new Date().toString(), "Save to case ", dbh.getCurrentUser(), caseID);

            Switch.switchWindow((Stage) SaveButton.getScene().getWindow(), new MenuController());
            
        } else {
            String missing = "Felter mangler: ";
            
            if (txtProblemDescription.getText().isEmpty()) {
                missing += "problembeskrivelse, ";
            }
            
            if(txtProblemAssesment.getText().isEmpty()){
                missing+= "vurdering, ";
            } 
            
            if (txtToDo.getText().isEmpty()){
                missing += "indsats.";
            }
            
            JOptionPane.showMessageDialog(null, missing);
        }
    }

    /**
     * Copies a selected file to the source directory.
     * @param event
     * @throws IOException
     */
    @FXML
    void FileChooser(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vælg fil");
        selectedFile = fileChooser.showOpenDialog(SaveButton.getScene().getWindow());

        if (selectedFile != null) {
            from=Paths.get(selectedFile.toURI());
            to=Paths.get("src/" + selectedFile.getName());
            Files.copy(from,to);
        }

    }

    @FXML
    private void contactInformationClicked(Event event) {

    }

    /**
     * gets information about a person using their cpr, prints it to the relevant textboxes.
     */
    private void setCitizen(){
        ResultSet info = dbh.getCitizenInfo(CPR);

        try {
            ResultSetMetaData rsmdt = info.getMetaData();
            String[] citizenInfo = new String[rsmdt.getColumnCount()];

            while(info.next()){
                for (int i = 1; i <= rsmdt.getColumnCount(); i++) {
                    citizenInfo[i-1] = info.getString(i);
                    System.out.println(rsmdt.getColumnName(i));
                }
            }

            // String caseid = CitizenInfo[0];
            String fullName = citizenInfo[1];
            String id = citizenInfo[2];
            String mobileNumber = citizenInfo[3];
            String email  = citizenInfo[4];
            String roadName = citizenInfo[5] + " " + citizenInfo[6];
            String floor = citizenInfo[7];
            String zipcode = citizenInfo[8];

            String[] fullNameSplit = fullName.split(" ");

            if(fullNameSplit.length > 2){
                String firstName = "";
                for(int i = 0; i < fullNameSplit.length-2; i++){
                    firstName += fullNameSplit[i] + " ";
                }
                firstName += fullNameSplit[fullNameSplit.length-2];
                txtFirstName.setText(firstName);
                txtLastName.setText(fullNameSplit[fullNameSplit.length-1]);
            } else if(fullNameSplit.length == 2) {
                txtFirstName.setText(fullNameSplit[0]);
                txtLastName.setText(fullNameSplit[1]);
            } else {
                txtFirstName.setText(fullNameSplit[0]);
            }
            txtCPRNumber.setText(id);
            txtFloor.setText(floor);
            txtZipCode.setText(zipcode);
            txtPhone.setText(mobileNumber);
            txtEmail.setText(email);
            txtRoadName.setText(roadName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets information about a person using their cpr, prints it to the relevant textboxes.
     */
    private void setKin(){
        String id = dbh.getKin(caseID);
        if(id.equals("NULL")){
            return;
        }
        ResultSet info = dbh.getInfo(id);

        try {
            ResultSetMetaData rsmdt = info.getMetaData();
            String[] citizenInfo = new String[rsmdt.getColumnCount()];

            while(info.next()){
                for (int i = 1; i <= rsmdt.getColumnCount(); i++) {
                    if(info.getString(i) != null){
                        citizenInfo[i-1] = info.getString(i);
                        System.out.println(rsmdt.getColumnName(i));
                    } else {
                        citizenInfo[i-1] = "";
                    }
                }
            }

            // String caseid = CitizenInfo[0];
            String fullName = citizenInfo[0];
            //String id = citizenInfo[2];
            String mobileNumber = citizenInfo[2];
            String email  = citizenInfo[3];
            String roadName = citizenInfo[4] + " " + citizenInfo[5];
            String floor = citizenInfo[6];
            String zipcode = citizenInfo[7];

            String[] fullNameSplit = fullName.split(" ");

            if(fullNameSplit.length > 2){
                String firstName = "";
                for(int i = 0; i < fullNameSplit.length-2; i++){
                    firstName += fullNameSplit[i] + " ";
                }
                firstName += fullNameSplit[fullNameSplit.length-2];
                txtFirstNamek.setText(firstName);
                txtLastNamek.setText(fullNameSplit[fullNameSplit.length-1]);
            } else if(fullNameSplit.length == 2) {
                txtFirstNamek.setText(fullNameSplit[0]);
                txtLastNamek.setText(fullNameSplit[1]);
            } else if (fullNameSplit.length != 0) {
                txtFirstNamek.setText(fullNameSplit[0]);
            }
            txtIdk.setText(id);
            txtFloork.setText(floor);
            txtZipCodek.setText(zipcode);
            txtPhonek.setText(mobileNumber);
            txtEmailk.setText(email);
            txtRoadNamek.setText(roadName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}