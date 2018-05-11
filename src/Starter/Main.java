package Starter;

import UI.GUIController;



public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        
        //Start application throught the presentation layer   
        
        GUIController ui = new GUIController();
        
        ui.startApplication();
        
    }
}
