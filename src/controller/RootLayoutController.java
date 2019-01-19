package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * The Class RootLayoutController.
 */
public class RootLayoutController {
	
	 /**
 	 * Handle exit.
 	 *
 	 * @param actionEvent the action event
 	 */
 	//Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
 
    /**
     * Handle help.
     *
     * @param actionEvent the action event
     */
    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("A JavaFX application to search and view movie information from TMDb made by Malin");
        alert.setContentText("Data provided by The Movie Database (TMDb).");
        alert.show();
    }
}
