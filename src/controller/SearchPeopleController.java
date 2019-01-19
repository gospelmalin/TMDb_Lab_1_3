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
import model.Movie;
import model.Person;
import repository.JsonHandler;
import utility.Util;

/**
 * The Class SearchPeopleController handles what is related to the Search People view.
 */
public class SearchPeopleController {

    /** The person id text field. */
    @FXML
    private TextField personIdTextField;

    /** The person name text field. */
    @FXML
    private TextField personNameTextField;
    
    /** The biography text field. */
    @FXML
    private TextField biographyTxt;

    /** The show selected person button. */
    @FXML
    private Button showSelectedPersonBtn;

    /** The home button. */
    @FXML
    private Button homeBtn;

    /** The person table. */
    @FXML
    private TableView<Person> personTable;

    /** The person id column. */
    @FXML
    private TableColumn<Person, Integer> personIdColumn;

    /** The person name column. */
    @FXML
    private TableColumn<Person, String> personNameColumn;

    /** The person popularity column. */
    @FXML
    private TableColumn<Person, Double> personPopularityColumn;

    /** The refresh table button. */
    @FXML
    private Button refreshTableBtn;

    /** The id text field. */
    @FXML
    private TextField idTxt;

    /** The birthday text field. */
    @FXML
    private TextField birthdayTxt;

    /** The popularity text field. */
    @FXML
    private TextField popularityTxt;

    /** The gender text field. */
    @FXML
    private TextField genderTxt;

    /** The deathday text field. */
    @FXML
    private TextField deathdayTxt;

    /** The name text field. */
    @FXML
    private TextField nameTxt;

    /** The known for movies table. */
    @FXML
    private TableView<Movie> knownForMoviesTable;

    /** The movie id column. */
    @FXML
    private TableColumn<Movie, Integer> movieIdCol;

    /** The title column. */
    @FXML
    private TableColumn<Movie, String> titleCol;
    
    /** The name keyword. */
    @FXML
    private TextField nameKeyword;

    /** The search person button. */
    @FXML
    private Button searchPersonBtn;
    
    /** The known for movies text area. */
    @FXML
    private TextArea knownForMoviesTextArea;
    
    /** The Person. */
    Person p;
    
    /** The Movie. */
    Movie m;
    
    /** The JsonHandler. */
    JsonHandler jH;

	/** The resulting persons ArrayList. */
	ArrayList<Person> resultingPersons;
	
	/** The known for movies ArrayList. */
	ArrayList<Movie> knownForMovies = new ArrayList<Movie>();
    
    /**
 	 * Initialize the controller class.
 	 */
 	//This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	//System.out.println("SearchPersonController initiated!");
    	
    	// mouseclick eventhandler
    	personTable.setOnMouseClicked(this::TableClicked);
    	
    	// Match column with property
    	// personTable
    	personIdColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
    	personNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
    	personPopularityColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("popularity"));
    	// Known for movies text Area
    	String result = "";
    	updateKnownForMoviesTextArea(result);
    }
    
    /**
     * Update known for movies text area.
     *
     * @param result the result
     */
    private void updateKnownForMoviesTextArea(String result) {
    	knownForMoviesTextArea.setText(result);
	}

	/**
	 * Table clicked.
	 *
	 * @param event the event
	 */
	@FXML
    private void TableClicked(MouseEvent event) {
        p = personTable.getSelectionModel().getSelectedItem();
        personIdTextField.setText(String.valueOf(p.getId())); // Convert to String.
        personNameTextField.setText(p.getName());
    }
    
   

    /**
     * Open start view.
     *
     * @param event the event
     */
    @FXML
    void openStartView(ActionEvent event) {
    	//System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }

    /**
     * Refresh table.
     *
     * @param event the event
     */
    @FXML
    void refreshTable(ActionEvent event) {
    	updateTable();
    }

    /**
     * Show selected person.
     *
     * @param event the event
     */
    @FXML
    void showSelectedPerson(ActionEvent event) {
    	knownForMoviesTextArea.setText("");
    	ArrayList<Movie> knownForMovies = new ArrayList<Movie>();
    	int id = Integer.parseInt(personIdTextField.getText());
    	for (int i=0; i<resultingPersons.size(); i++) {
    		if (resultingPersons.get(i).getId() == id) {
    			knownForMovies = resultingPersons.get(i).getKnownForMovies();
    		}
    	}
    	String s="";
    	String idString = personIdTextField.getText();
    	System.out.println("selected id is: " + idString);
    	JsonHandler j = new JsonHandler();
    	Person selectedPerson = new Person();
    	selectedPerson = j.createSelectedPersonFromJsonString(id, knownForMovies);
    	//populate Person details text fields
    	idTxt.setText(String.valueOf(selectedPerson.getId()));
    	nameTxt.setText(selectedPerson.getName());
    	genderTxt.setText(selectedPerson.getGender());
    	birthdayTxt.setText(selectedPerson.getBirthday());
    	deathdayTxt.setText(selectedPerson.getDeathday());
    	popularityTxt.setText(String.valueOf(selectedPerson.getPopularity()));
    	biographyTxt.setText(selectedPerson.getBiography());
    	//populate knownForMoviesTable / Text area
    	knownForMovies = selectedPerson.getKnownForMovies();
    	//updateKnownForMoviesTable(); // Not in use in this version
    	for (int k=0; k<knownForMovies.size(); k++) {
    		String movieTitle = knownForMovies.get(k).getTitle();
    		s = s  + movieTitle + "\n"; 
    	}  	
    	knownForMoviesTextArea.setText(s);
    }
    
 /**
  * Update table.
  */
 // Updating table with result from Db search
    private void updateTable() {
		ObservableList<Person> list = FXCollections.observableArrayList(resultingPersons);
		personTable.setItems((ObservableList<Person>) list);
    }
    
    
    /**
     * Search person.
     *
     * @param event the event
     */
    @FXML
    void searchPerson(ActionEvent event) {
    	String name = nameKeyword.getText();
       	String message = null;
    	Util util = new Util();
    	name = util.validateString(name); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
		JsonHandler jH = new JsonHandler();
		ArrayList<Person> persons = jH.createPersonArrayFromJsonString(name); //1
		message = showPersons(persons);
    	// Get the result into the array list used to build the table
    	resultingPersons = jH.getPersons();
		// Update table
    	updateTable();
    }

    /**
	 * Show persons.
	 *
	 * @param persons the persons
	 * @return the string
	 */
	private String showPersons(ArrayList<Person> persons) {
		String message = "";			
		for(int i=0; i<persons.size(); i++) {
			int id = persons.get(i).getId();
			String name = persons.get(i).getName();
			double popularity = persons.get(i).getPopularity();
			message = message + "\nPerson id: " + id + "\nName: " + name + "\nPopularity: " + popularity + "\n";
		}
		return message;
	}
}
