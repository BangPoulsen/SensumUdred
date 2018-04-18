/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
        
/**
 * FXML Controller class
 *
 * @author Simon
 */
public class GUIController implements Initializable {

    @FXML
    private AnchorPane FrontPage;
    @FXML
    private AnchorPane LoginPage;
    @FXML
    private TextField LoginUsername;
    @FXML
    private PasswordField LoginPassword;
    @FXML
    private Button LoginButton;
    @FXML
    private Button LogoffButton;
    @FXML
    private Label LoginSensumLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
