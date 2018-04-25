package BL;


import DL.DatabaseHandler;
import UI.GUIController;

public class Main {
    

        


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        System.out.println("Opening connection");
        dbHandler.run();
        System.out.println("Connection opened");
        GUIController guiController = new GUIController();
        guiController.StartApplication();
        dbHandler.closeConnection();
        System.out.println("Closed connection");
    }
    

}






