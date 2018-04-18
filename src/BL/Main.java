package BL;

<<<<<<< HEAD
        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import javax.activation.DataHandler;
        import javax.xml.crypto.Data;
        import java.sql.*;
        import java.util.Scanner;


public class Main extends Application {
/*    public Main() throws SQLException {
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.run ();

    }
=======
import java.sql.*;
import java.util.Scanner;
import static javafx.application.Application.launch;
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

>>>>>>> 704cd6fb15992541d778573818e4cf108643b5ea
}






