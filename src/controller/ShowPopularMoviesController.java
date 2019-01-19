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

public class ShowPopularMoviesController {

    @FXML
    private AnchorPane movieIdCol;

    @FXML
    private TableView<Movie> movieTable;

    @FXML
    private TableColumn<Movie, Integer> idCol;

    @FXML
    private TableColumn<Movie, String> titleCol;

    @FXML
    private TableColumn<Movie, String> overviewCol;

    @FXML
    private TableColumn<Movie, Double> popularityCol;

    @FXML
    private TextArea movieDetailsTextArea;

    @FXML
    private TextField movieIdTxt;
    
    @FXML
    private TextField movieTitleTxt;

    @FXML
    private Button showMovieDetailsBtn;

    @FXML
    private Button homeBtn;
    
    @FXML
    private Button refreshPopularBtn;
    
    Movie m;
    JsonHandler jH;
    ArrayList<Movie> resultingMovies;
    ArrayList<Movie> resultingMovieDetails;
    
    @FXML
    private void initialize () {
    	System.out.println("ShowPopularMovieController initiated!");
    	
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
    
    @FXML
    private void TableClicked(MouseEvent event) {
        m = movieTable.getSelectionModel().getSelectedItem();
        movieIdTxt.setText(String.valueOf(m.getId())); // Convert to String.
        movieTitleTxt.setText(m.getTitle());
    }

    @FXML
    void openStartView(ActionEvent event) {
    	System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }

    @FXML
    void showMovieDetails(ActionEvent event) {
    	System.out.println("showMovieDetails in ShowPopularMovieController is called");
    	
    	String idString = movieIdTxt.getText();
    	System.out.println("This is selected id in showPopularmoviecontroller showMoviedetails " + idString); //TODO TEMP
    	String message = null;
    	Util util = new Util();
    	idString = util.validateString(idString); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
    	JsonHandler h = new JsonHandler();
    	h.createMovieDetailsArrayFromJsonString(idString, resultingMovies);
    	//h.createMovieDetailsArrayFromJsonString(idString); 
		ArrayList<Movie> movieDetails = h.getMovieDetails(); 
		System.out.println("These are the movie details for the selected movie when recieved FROM jsonhandler: " + movieDetails); //TODO TEMP
		//
		message = showMovies(movieDetails);
		System.out.println("This is the result message from Movie Details search: \n" + message); //TODO TEMP
    	
    	
    	// TODO?
    	
    	// Get the result into the array list used to build the table
		resultingMovieDetails = h.getMovieDetails();
		// Update table
    //	updateTable();
    	
    	
    	movieDetailsTextArea.setText(h.getMovieDetails().toString());
    //	movieDetailsTextArea.getText(); // TODO is this needed?
    	
    	//movieDetailsTextArea.setText(s);
    	// TODO
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
	
	
	 
 // Updating table with result from Db search
	private void updateTable() {
    	jH = new JsonHandler();
		resultingMovies = jH.createPopularMoviesArrayFromJsonString();
		ObservableList<Movie> list = FXCollections.observableArrayList(resultingMovies);
		movieTable.setItems((ObservableList<Movie>) list);
    }
    
 private void updateMovieDetailsTextArea(String result) {
		movieDetailsTextArea.setText(result); ;		
	}

}

