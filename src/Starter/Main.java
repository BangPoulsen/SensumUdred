package Starter;


import Acquantance.IBusiness;
import Acquantance.IData;
import Business.BusinessFacade;
import Data.DataFacade;
import Data.DatabaseHandler;
import UI.GUIController;
import Acquantance.IUI;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DatabaseHandler dbHandler = new DatabaseHandler();
        
        //Create instance of data interface
        IData data = new DataFacade();
        
        //Create instance of business interface
        IBusiness business = new BusinessFacade();
        
        //Inject the data layer into the business layer
        business.injectData(data);
        System.out.println("Data injected");
        
        //Create the presentation layer
        IUI ui = new GUIController();
        
        //Inject the business layer into the presentation layer
        ui.injectBusiness(business);
        System.out.println("Business injected");

        //Print to user 
        System.out.println("Ready to launch");
        
        //Start application throught the presentation layer   
        
        ui.startApplication();
        
    }
}
