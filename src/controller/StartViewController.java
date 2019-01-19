package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartViewController {

    @FXML
    private Button selectSearchMoviesBtn;

    @FXML
    private Button selectSearchPeopleBtn;
    
  //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	
    }

    @FXML
    void selectSearchMovies(ActionEvent event) {
    	System.out.println("Search Movie View should open"); //TODO TEMP
    	ViewController.activate("SearchMovieView");
    }

    @FXML
    void selectSearchPeople(ActionEvent event) {
    	System.out.println("Search People View should open"); //TODO TEMP
    //	ViewController.activate("SearchPeopleView");

    }

}
