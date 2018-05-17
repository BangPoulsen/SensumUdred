package UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Switch {
    
        /**
         * Switch to a new pane
         * @param window the scene switched from.
         * @param app the targeted application to open.
         */
	public static void switchWindow(Stage window, Application app) {
		try {
			app.start(window);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
