package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class RootLayoutController {
	
	 //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
 
    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("A JavaFX application to search and view movie information from TMDb made by Malin");
        alert.setContentText("Credit to TMDb"); //TODO
        alert.show();
    }
}
