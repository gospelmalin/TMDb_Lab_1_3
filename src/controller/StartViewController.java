package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The Class StartViewController handles the start view where the user can choose
 * what to do.
 */
public class StartViewController {

    /** The select search movies button. */
    @FXML
    private Button selectSearchMoviesBtn;

    /** The select search people button. */
    @FXML
    private Button selectSearchPeopleBtn;
    

    /**
   * Initialize the controller class.
   */
  //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	
    }

    /**
     * Select search movies opens the Search movies view.
     *
     * @param event the event
     */
    @FXML
    private void selectSearchMovies(ActionEvent event) {
    	ViewController.activate("SearchMovieView");
    }

    /**
     * Select search people opens the Search people view.
     *
     * @param event the event
     */
    @FXML
    private void selectSearchPeople(ActionEvent event) {
    	ViewController.activate("SearchPeopleView");

    }
    
    /**
     * Select show popular movies opens the Show popular movies view.
     *
     * @param event the event
     */
    @FXML
    private void selectShowPopularMovies(ActionEvent event) {
       	ViewController.activate("ShowPopularMovies");
    }

}
