package BL;

import java.sql.*;
import java.util.Scanner;
import static javafx.application.Application.launch;

import com.sun.org.apache.bcel.internal.generic.Select;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;


public class Main extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.run();

    
        launch(args);
    }

}






