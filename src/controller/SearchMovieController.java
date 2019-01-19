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
 * The Class SearchMovieController handles what is related to the Search Movie view.
 */
public class SearchMovieController {

    /** The movie id column. */
    @FXML
    private AnchorPane movieIdCol;

    /** The movie title keyword text field. */
    @FXML
    private TextField movieTitleKeyword;

    /** The search movie button. */
    @FXML
    private Button searchMovieBtn;

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

    /** The movie details text area. */
    @FXML
    private TextArea movieDetailsTextArea;

    /** The movie title text field. */
    @FXML
    private TextField movieTitleTxt;

    /** The movie id text field. */
    @FXML
    private TextField movieIdTxt;

    /** The show movie details button. */
    @FXML
    private Button showMovieDetailsBtn;

    /** The home button. */
    @FXML
    private Button homeBtn;
    
    /** The movie. */
    Movie m;
    
    /** The jsonHandler. */
    JsonHandler jH;
    
    /** The resulting movies ArrayList. */
    ArrayList<Movie> resultingMovies;
    
    /** The resulting movie details ArrayList. */
    ArrayList<Movie> resultingMovieDetails;
    

    /**
     * Initialize the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	//System.out.println("SearchMovieController initiated!");
    	String details = "";
    	updateMovieDetailsTextArea(details);
    	// mouseclick eventhandler
    	movieTable.setOnMouseClicked(this::TableClicked);
    	// Match column with property
    	idCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
    	titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    	overviewCol.setCellValueFactory(new PropertyValueFactory<>("overview"));
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
     * Update movie details text area.
     *
     * @param result the result
     */
    private void updateMovieDetailsTextArea(String result) {
		movieDetailsTextArea.setText(result); 
	}

	/**
	 * Open start view.
	 *
	 * @param event the event
	 */
	@FXML
    private void openStartView(ActionEvent event) {
    	//System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }

    /**
     * Search movies.
     *
     * @param event the event
     */
    @FXML
    private void searchMovies(ActionEvent event) {
    	String titleKeyword = movieTitleKeyword.getText();
    	String message = null;
    	Util util = new Util();
    	titleKeyword = util.validateString(titleKeyword); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
		JsonHandler jH = new JsonHandler();
		ArrayList<Movie> movies = jH.createMovieArrayFromJsonString(titleKeyword); //1
		message = showMovies(movies);
    	// Get the result into the array list used to build the table
    	resultingMovies = jH.getMovies();
		// Update table
    	updateTable();
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
		return message;
		
	}

    /**
     * Show movie details.
     *
     * @param event the event
     */
    @FXML
    private void showMovieDetails(ActionEvent event) {
    	String idString = movieIdTxt.getText();
    	String message = null;
    	Util util = new Util();
    	idString = util.validateString(idString); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
    	JsonHandler h = new JsonHandler();
    	h.createMovieDetailsArrayFromJsonString(idString, resultingMovies);
    	//h.createMovieDetailsArrayFromJsonString(idString); 
		ArrayList<Movie> movieDetails = h.getMovieDetails(); 
		message = showMovies(movieDetails);
    	// Get the result into the array list used to build the table
		resultingMovieDetails = h.getMovieDetails();
		// Update table
		//	updateTable(); // Not in this version
    	movieDetailsTextArea.setText(h.getMovieDetails().toString()); 	
    }
    
  /**
   * Update table.
   */
  // Updating table with result from Db search
    private void updateTable() {
		ObservableList<Movie> list = FXCollections.observableArrayList(resultingMovies);
		movieTable.setItems((ObservableList<Movie>) list);
    }

}

