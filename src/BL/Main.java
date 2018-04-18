package BL;

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
}






