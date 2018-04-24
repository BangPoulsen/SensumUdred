package BL;


import UI.GUIController;

public class Main {
    

        


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.run();
       GUIController guiController = new GUIController();
       guiController.StartApplication();

    

    }
    

}






