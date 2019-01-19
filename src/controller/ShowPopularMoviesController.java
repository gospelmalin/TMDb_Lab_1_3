package controller;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Movie;
import repository.JsonHandler;
import utility.Util;

/**
 * The Class ShowPopularMoviesController handles anything 
 * related to the Show Popular Movies view.
 */
public class ShowPopularMoviesController {

    /** The movie id column. */
    @FXML
    private AnchorPane movieIdCol;

    /** The movie table. */
    @FXML
    private TableView<Movie> movieTable;

    /** The id column. */
    @FXML
    private TableColumn<Movie, Integer> idCol;

    /** The title column. */
    @FXML
    private TableColumn<Movie, String> titleCol;

    /** The overview column. */
    @FXML
    private TableColumn<Movie, String> overviewCol;

    /** The popularity column. */
    @FXML
    private TableColumn<Movie, Double> popularityCol;

    /** The movie details text area. */
    @FXML
    private TextArea movieDetailsTextArea;

    /** The movie id text field. */
    @FXML
    private TextField movieIdTxt;
    
    /** The movie title text field. */
    @FXML
    private TextField movieTitleTxt;

    /** The show movie details button. */
    @FXML
    private Button showMovieDetailsBtn;

    /** The home button. */
    @FXML
    private Button homeBtn;
    
    
    /** The m - movie. */
    Movie m;
    
    /** The JsonHandler. */
    JsonHandler jH;
    
    /** The resulting movies ArrayList. */
    ArrayList<Movie> resultingMovies;
    
    /** The resulting movie details ArrayList. */
    ArrayList<Movie> resultingMovieDetails;
    
    /**
     * Initializing the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    //	System.out.println("ShowPopularMovieController initiated!");
    	String details = "";
    	updateMovieDetailsTextArea(details);
    	
    	// mouseclick eventhandler
    	movieTable.setOnMouseClicked(this::TableClicked);
    	// Match column with property
    	idCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
    	titleCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
    	overviewCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("overview"));
    	popularityCol.setCellValueFactory(new PropertyValueFactory<Movie, Double>("popularity"));
    	updateTable();
    }
    
    /**
     * Table clicked.
     *
     * @param event the event
     */
    @FXML
    private void TableClicked(MouseEvent event) {
        m = movieTable.getSelectionModel().getSelectedItem();
        movieIdTxt.setText(String.valueOf(m.getId())); // Convert to String.
        movieTitleTxt.setText(m.getTitle());
    }

    /**
     * Open start view.
     *
     * @param event the event
     */
    @FXML
    void openStartView(ActionEvent event) {
    	System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }

    /**
     * Show movie details in text area.
     *
     * @param event the event
     */
    @FXML
    void showMovieDetails(ActionEvent event) {
    	String idString = movieIdTxt.getText();
    	String message = null;
    	Util util = new Util();
    	idString = util.validateString(idString); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
    	JsonHandler h = new JsonHandler();
    	h.createMovieDetailsArrayFromJsonString(idString, resultingMovies);
		ArrayList<Movie> movieDetails = h.getMovieDetails(); 
		message = showMovies(movieDetails);
    	// Get the result into the array list used to build the table (currently not used)
		resultingMovieDetails = h.getMovieDetails();
    	movieDetailsTextArea.setText(h.getMovieDetails().toString());
    }
    
    /**
	 * Show movies.
	 *
	 * @param movies the movies
	 * @return the string
	 */
	private String showMovies(ArrayList<Movie> movies) {
		String message = "";			
		for(int i=0; i<movies.size(); i++) {
			int id = movies.get(i).getId();
			String title = movies.get(i).getTitle();
			String overview = movies.get(i).getOverview();
			double popularity = movies.get(i).getPopularity();
			message = message + "\nMovie id: " + id + "\nTitle: " + title + "\nOverview: " + overview + "\nPopularity: " + popularity + "\n";
		}
		System.out.println("ShowMovies prints message: " + message); //TODO TEMP
		return message;
		
	}
	
 
 /**
  * Update table.
  */
 // Updating table with result from Db search
	private void updateTable() {
    	jH = new JsonHandler();
		resultingMovies = jH.createPopularMoviesArrayFromJsonString();
		ObservableList<Movie> list = FXCollections.observableArrayList(resultingMovies);
		movieTable.setItems((ObservableList<Movie>) list);
    }
    
 /**
  * Update movie details text area.
  *
  * @param result the result
  */
 private void updateMovieDetailsTextArea(String result) {
		movieDetailsTextArea.setText(result); ;		
	}

}

