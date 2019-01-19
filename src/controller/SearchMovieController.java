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

public class SearchMovieController {

    @FXML
    private AnchorPane movieIdCol;

    @FXML
    private TextField movieTitleKeyword;

    @FXML
    private Button searchMovieBtn;

    @FXML
    private TableView<Movie> movieTable;

    @FXML
    private TableColumn<Movie, Integer> idCol;
    
    @FXML
    private TableColumn<Movie, String> titleCol;

    @FXML
    private TableColumn<Movie, String> overviewCol;

    @FXML
    private TextArea movieDetailsTextArea;

    @FXML
    private TextField movieTitleTxt;

    @FXML
    private TextField movieIdTxt;

    @FXML
    private Button showMovieDetailsBtn;

    @FXML
    private Button homeBtn;
    
    Movie m;
    JsonHandler jH;
    ArrayList<Movie> resultingMovies;
    ArrayList<Movie> resultingMovieDetails;
    
    @FXML
    private void initialize () {
    	System.out.println("SearchMovieController initiated!");
    	String details = "x";
    	updateMovieDetailsTextArea(details);
    	// mouseclick eventhandler
    	movieTable.setOnMouseClicked(this::TableClicked);//TODO
    	// Match column with property
    	idCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
    	titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    	overviewCol.setCellValueFactory(new PropertyValueFactory<>("overview"));
    }
    
    @FXML
    private void TableClicked(MouseEvent event) {
        m = movieTable.getSelectionModel().getSelectedItem();
        movieIdTxt.setText(String.valueOf(m.getId())); // Convert to String.
        movieTitleTxt.setText(m.getTitle());
    }

    private void updateMovieDetailsTextArea(String result) {
    	
    	// TODO
    	
    	//String s = result; //alt2
		System.out.println("Test view controller updateResultTextArea prints result: " + result);
	//	System.out.println("getting text in textArea");
	//	movieDetailsTextArea.getText();
		System.out.println("Test view controller calling setTExt");
		movieDetailsTextArea.setText(result); //alt 1
	//	movieDetailsTextArea.setText(s);//alt2
		System.out.println("text set in textArea");
		movieDetailsTextArea.getText();
		System.out.println("Test view controller getting text in textArea");
		
	}

	@FXML
    void openStartView(ActionEvent event) {
    	System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }

    @FXML
    void searchMovies(ActionEvent event) {
    	System.out.println("searchMovies in SearchMovieController is called");
    	
    	String titleKeyword = movieTitleKeyword.getText();
    	System.out.println("This is titleKeyword entered in StartView search movie " + titleKeyword); //TODO TEMP
    	String message = null;
    	Util util = new Util();
    	titleKeyword = util.validateString(titleKeyword); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
		JsonHandler jH = new JsonHandler();
		ArrayList<Movie> movies = jH.createMovieArrayFromJsonString(titleKeyword); //1
		System.out.println("These are the movies in the array when recieved FROM jsonhandler: " + movies); //TODO TEMP
		//2:alternative way of getting the movie data:
		//jH.createMovieArrayFromJsonString(titleKeyword); 
		//movies = jH.getMovies(); 
		message = showMovies(movies);
		System.out.println("This is the result message from Movie search: \n" + message); //TODO TEMP
    	
    	
    	// TODO?
    	
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
		System.out.println("ShowMovies prints message: " + message); //TODO TEMP
		return message;
		
	}

    @FXML
    void showMovieDetails(ActionEvent event) {

    	System.out.println("showMovieDetails in SearchMovieController is called");
    	
    	String idString = movieIdTxt.getText();
    	System.out.println("This is selected id in searchmoviecontroller showMoviedetails " + idString); //TODO TEMP
    	String message = null;
    	Util util = new Util();
    	idString = util.validateString(idString); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
    	JsonHandler h = new JsonHandler();
    	h.createMovieDetailsArrayFromJsonString(idString); 
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
    	movieDetailsTextArea.getText(); // TODO is this needed?
    	
    	//movieDetailsTextArea.setText(s);
    	// TODO
    }
    
  // Updating table with result from Db search
    private void updateTable() {
		ObservableList<Movie> list = FXCollections.observableArrayList(resultingMovies);
		movieTable.setItems((ObservableList<Movie>) list);
    }

}

