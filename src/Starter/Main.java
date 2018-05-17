package Starter;

import UI.GUIController;



public class Main {

    /**
     * The main method, starts the application by turning on the UI.
     *
     * @param args
     */
    public static void main(String[] args) {
        //Starts application through the presentation layer
        
        GUIController ui = new GUIController();
        
        ui.startApplication();
        
    }
}
