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

public class SearchPeopleController {

    @FXML
    private TextField personIdTextField;

    @FXML
    private TextField personNameTextField;
    
    @FXML
    private TextField biographyTxt;

    @FXML
    private Button showSelectedPersonBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Integer> personIdColumn;

    @FXML
    private TableColumn<Person, String> personNameColumn;

    @FXML
    private TableColumn<Person, Double> personPopularityColumn;

    @FXML
    private Button refreshTableBtn;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField birthdayTxt;

    @FXML
    private TextField popularityTxt;

    @FXML
    private TextField genderTxt;

    @FXML
    private TextField deathdayTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TableView<Movie> knownForMoviesTable;

    @FXML
    private TableColumn<Movie, Integer> movieIdCol;

    @FXML
    private TableColumn<Movie, String> titleCol;
    
    @FXML
    private TextField nameKeyword;

    @FXML
    private Button searchPersonBtn;
    
    @FXML
    private TextArea knownForMoviesTextArea;
    
    Person p;
    Movie m;
    JsonHandler jH;

	ArrayList<Person> resultingPersons;
	ArrayList<Movie> knownForMovies;
    
    @FXML
    private void initialize () {
    	System.out.println("SearchPersonController initiated!");
    	
    	// mouseclick eventhandler
    	personTable.setOnMouseClicked(this::TableClicked);
    	
    	// Match column with property
    	// personTable
    	personIdColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
    	personNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
    	personPopularityColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("popularity"));
    	
    	String result = "x";
    	updateKnownForMoviesTextArea(result);
    }
    
    private void updateKnownForMoviesTextArea(String result) {
    	knownForMoviesTextArea.setText(result); //alt 1
	//	resultTextArea.setText(s);//alt2
    	//resultTextArea.setText(h.getMovies().toString());
		System.out.println("text set in textArea");
		knownForMoviesTextArea.getText();
		
	}

	@FXML
    private void TableClicked(MouseEvent event) {
        p = personTable.getSelectionModel().getSelectedItem();
        personIdTextField.setText(String.valueOf(p.getId())); // Convert to String.
        personNameTextField.setText(p.getName());
    }
    
   

    @FXML
    void openStartView(ActionEvent event) {
    	System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }

    @FXML
    void refreshTable(ActionEvent event) {
    	updateTable();
    }

    @FXML
    void showSelectedPerson(ActionEvent event) {
    	System.out.println("resultingPersons size when showSelectedPerson starts: " + resultingPersons.size());
    	System.out.println("Detta är resultingPersons: " + resultingPersons);
    	int id = Integer.parseInt(personIdTextField.getText());
    	for (int i=0; i<resultingPersons.size(); i++) {
    		if (resultingPersons.get(i).getId() == id) {
    			knownForMovies = resultingPersons.get(i).getKnownForMovies();
    			System.out.println("knownForMovies sent as indata were: " + knownForMovies);
    		}
    	}
    	
    	String idString = personIdTextField.getText();
    	System.out.println("selected id is: " + idString);
    	//int i = resultingPersons.indexOf(id);
    	//System.out.println("i is " + i);
    	//knownForMovies = resultingPersons.get(i).getKnownForMovies(); //TODO
    	//System.out.println("knownForMovies sent as indata were: " + knownForMovies);
    	//knownForMovies = null;
    	JsonHandler j = new JsonHandler();
    	Person selectedPerson = new Person();
    	selectedPerson = j.createSelectedPersonFromJsonString(id, knownForMovies);
    	System.out.println("This is selected person: " + selectedPerson);
    	//populate Person details textfields
    	idTxt.setText(String.valueOf(selectedPerson.getId()));
    	nameTxt.setText(selectedPerson.getName());
    	genderTxt.setText(selectedPerson.getGender());
    	birthdayTxt.setText(selectedPerson.getBirthday());
    	deathdayTxt.setText(selectedPerson.getDeathday());
    	popularityTxt.setText(String.valueOf(selectedPerson.getPopularity()));
    	biographyTxt.setText(selectedPerson.getBiography());
    	//populate knownForMoviesTable
    	knownForMovies = selectedPerson.getKnownForMovies();
    	//updateKnownForMoviesTable();
    	knownForMoviesTextArea.setText(knownForMovies.toString());
    	
    }
    
 // Updating table with result from Db search
    private void updateTable() {
		ObservableList<Person> list = FXCollections.observableArrayList(resultingPersons);
		personTable.setItems((ObservableList<Person>) list);
    }
    
    
    @FXML
    void searchPerson(ActionEvent event) {
    	System.out.println("searchPerson in SearchPersonController is called");
    	
    	String name = nameKeyword.getText();
    	System.out.println("This is the name entered in Search People enter name " + nameKeyword); //TODO TEMP
    	String message = null;
    	Util util = new Util();
    	name = util.validateString(name); // Validation of entered data	
		// Creating instance of JsonHandler for parsing the JSON data
		JsonHandler jH = new JsonHandler();
		ArrayList<Person> persons = jH.createPersonArrayFromJsonString(name); //1
		System.out.println("These are the persons in the array when recieved FROM jsonhandler: " + persons);
		//jH.createMovieArrayFromJsonString(titleKeyword); 
		//movies = jH.getMovies(); 
		message = showPersons(persons);
		System.out.println("This is the result message from Persons search: \n" + message); //TODO TEMP
    	
    	
    	// TODO?
    	
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
		System.out.println("ShowPersons prints message: " + message); //TODO TEMP
		return message;
	}
}
