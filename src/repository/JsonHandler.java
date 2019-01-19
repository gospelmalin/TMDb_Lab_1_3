package repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import repository.TMDbClient;
import model.Movie;
import model.Person;


public class JsonHandler {
	
	public JsonHandler() {}
	
	ArrayList<Movie> movies = new ArrayList<Movie>();
	ArrayList<Movie> movieDetails = new ArrayList<Movie>();
	private ArrayList<Movie> popularMovies= new ArrayList<Movie>();
	private ArrayList<Person> persons = new ArrayList<Person>();
	private ArrayList<Person> selectedPersons = new ArrayList<Person>();

	/**
	 * @return the movies
	 */
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	/**
	 * @param movies the movies to set
	 */
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
		//TODO testar att l�gga till en film h�r ocks� s� att slutresultatet som visas verkligen �r fr�n fil
		//Movie movie =  new Movie(9,"9:e filmen �r tillagd i Handler");
    	//movies.add(movie);
    	//hit
		printMoviesToTextFile(movies);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Handler [movies=" + movies + "]";
	}
	
	/**
	 * @return the movieDetails
	 */
	public ArrayList<Movie> getMovieDetails() {
		return movieDetails;
	}

	/**
	 * @param movieDetails the movieDetails to set
	 */
	public void setMovieDetails(ArrayList<Movie> movieDetails) {
		this.movieDetails = movieDetails;
	}
	
	/**
	 * @return the popularMovies
	 */
	public ArrayList<Movie> getPopularMovies() {
		return popularMovies;
	}

	/**
	 * @param popularMovies the popularMovies to set
	 */
	public void setPopularMovies(ArrayList<Movie> popularMovies) {
		this.popularMovies = popularMovies;
	}

	/**
	 * @return the persons
	 */
	public ArrayList<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}
	
	/**
	 * @return the selectedPersons
	 */
	public ArrayList<Person> getSelectedPersons() {
		return selectedPersons;
	}

	/**
	 * @param selectedPersons the selectedPersons to set
	 */
	public void setSelectedPersons(ArrayList<Person> selectedPersons) {
		this.selectedPersons = selectedPersons;
	}

	/**
	 * Creates the movie array from json string brought from TMDb.
	 *
	 * @param query the query
	 * @return the array list
	 */
	public ArrayList<Movie> createMovieArrayFromJsonString(String query) {
		TMDbClient tc = new TMDbClient();
		String jsonString = tc.queryTMDbForMovies(query);
		JSONObject jsonObject = processJsonStringToJsonObject(jsonString);
		String arrayName = "results";
		JSONArray resultsArray = processJsonObjectToJsonArray(jsonObject, arrayName);
		movies = processJSONArrayAndCreateMovieArray(resultsArray);
		printMoviesToTextFile(movies); // TODO added to get data into text file
		return movies;	
	}
	
	/**
	 * Creates the most popular movies array from json string brought from TMDb.
	 *
	 * @param query the query
	 * @return the array list
	 */
	public ArrayList<Movie> createPopularMoviesArrayFromJsonString() {
		TMDbClient tc = new TMDbClient();
		String jsonString = tc.queryTMDbForPopularMovies();
		JSONObject jsonObject = processJsonStringToJsonObject(jsonString);
		String arrayName = "results";
		JSONArray resultsArray = processJsonObjectToJsonArray(jsonObject, arrayName);
		popularMovies = processJSONArrayAndCreateMovieArray(resultsArray);
		//printMoviesToTextFile(movies); // TODO added to get data into text file
		return popularMovies;	
	}
	
	/**
	 * Prints the movies.
	 */
	public void printMovies() {
		for (int i=0; i < movies.size(); i++) {
			System.out.println(movies.get(i));
		}	
	}

	/**
	 * Process json string to json object.
	 *
	 * @param s the s
	 * @return the JSON object
	 */
	private JSONObject processJsonStringToJsonObject(String s) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(s);
		} catch (JSONException e) {
			System.err.println("Oops! A JSONException occurred in processJsonStringToJsonObject : " + e.getMessage());
		}
		return jsonObject;
	}

	/**
	 * Process json object to json array.
	 *
	 * @param jsonObject the json object
	 * @param arrayName the array name
	 * @return the JSON array
	 */
	private JSONArray processJsonObjectToJsonArray(JSONObject jsonObject, String arrayName) {
		JSONArray jsonArray = null;
		try {
			jsonArray = jsonObject.getJSONArray(arrayName);
		} catch (JSONException e) {
			System.err.println("Oops! A JSONException occurred in processJsonObjectToJsonArray " + e.getMessage());
		}

		return jsonArray;
	}
	
	/**
	 * Process JSON array and create movie array.
	 *
	 * @param jsonArray the json array
	 * @return the array list
	 */
	private ArrayList<Movie> processJSONArrayAndCreateMovieArray(JSONArray jsonArray) {
		try {
			//Loop through the array and read all properties for the Movie object to be created. Finally add it to the array list.
			for (int i = 0; i < jsonArray.length(); i++) { 
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				int id = jsonObject.getInt("id");
				String title = jsonObject.getString("title");
				String overview = jsonObject.getString("overview");
				String releaseDate = jsonObject.optString("release_date", null);
				Double popularity = jsonObject.getDouble("popularity");
			//create Movie object
				Movie movie = new Movie(id, title, overview, releaseDate, popularity);
				//print movie
				//System.out.println(movie); // Used during development only
			//Add movie to movie array list
				movies.add(movie);
			}
		} catch (JSONException e) {
			System.err.println("Oops! A JSONException occurred in processJSONArrayAndCreateMovieArray: " + e.getMessage());
		  }
		
		return movies;
	}
	
	
	 private void printMoviesToTextFile(ArrayList<Movie> movies) {
	    	try {
	    		String str = "";
	    		for(int i=0; i<movies.size(); i++) {
		    		int id = movies.get(i).getId();
		    		String title = movies.get(i).getTitle();
		    		String overview = movies.get(i).getOverview();
		    		//Double popularity = movies.get(i).getPopularity();
		    		str = str + "MovieId: " + id + "\nTitle: " + title + "\nOverview: " + overview + "\n" +"\n";
	    		}
			    Path path = Paths.get("movies.txt");
			    byte[] strToBytes = str.getBytes();
			 
			    Files.write(path, strToBytes);
			 		 
			} catch (FileNotFoundException e) {
				System.err.println("Ouch, File not found. " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Ops! Writing to file did not turn out as expected. " + e.getMessage());
			}
	    }
	    
	    //This one works
		public String readMoviesFromTextFile() {
			Path path = Paths.get("movies.txt");
			String oneReadLine = null;
			try {
				String read = Files.readAllLines(path).get(0);
				System.out.println("Read movies.txt: " + read); //TODO TEMP
				oneReadLine = read;
			} catch (IOException e) {
				System.err.println("Ops! Reading from file did not work as expected. " + e.getMessage());
			}
			//return testText;
			return oneReadLine;
		}

		public ArrayList<Movie> createMovieDetailsArrayFromJsonString(String idString) {
			// Get data from movies arraylist for id = idString and add to movieDetails array
			int id = Integer.parseInt(idString);
			Movie detailedMovie = new Movie();
			for (int i=0; i< movies.size(); i++) {
				if(movies.get(i).getId() == id) {
					detailedMovie.setId(movies.get(i).getId());
					detailedMovie.setTitle(movies.get(i).getTitle());
					detailedMovie.setOverview(movies.get(i).getOverview());
					detailedMovie.setPopularity(movies.get(i).getPopularity());
					detailedMovie.setOriginalTitle(movies.get(i).getOriginalTitle());
					detailedMovie.setOriginalLanguage(movies.get(i).getOriginalLanguage());
					detailedMovie.setVoteCount(movies.get(i).getVoteCount());
					detailedMovie.setVoteAverage(movies.get(i).getVoteAverage());
					//TODO more details
					System.out.println("This is detailedMovie for id " + id + ": " + detailedMovie);
					//movieDetails.add(detailedMovie); //TODO h�r?
				}
			}
			// TODO Get further details from TMDb
			TMDbClient tc = new TMDbClient();
			String jsonString = tc.queryTMDbForMovieDetails(idString);
			System.out.println("This is the jsonString cretated when quering for movie details " + jsonString);
			JSONObject jsonObject = processJsonStringToJsonObject(jsonString);
			try {
				int budget = jsonObject.getInt("budget");
				String status = jsonObject.getString("status");
				String tagline = jsonObject.getString("tagline");
			
			// TODO l�gg till further details fr�n DMDb till movieDetailsArray
				detailedMovie.setBudget(budget);
				detailedMovie.setStatus(status);
				detailedMovie.setTagline(tagline);
				
			movieDetails.add(detailedMovie); 
			} catch (JSONException e) {
				System.err.println("Oops! A JSONException occurred in createMovieDetailsArrayFromJsonString: " + e.getMessage());
			}
			return movieDetails;
		}
		
		public ArrayList<Movie> createMovieDetailsArrayFromJsonString(String idString, ArrayList <Movie> movieList) {
			// Get data from movies arraylist for id = idString and add to movieDetails array
			int id = Integer.parseInt(idString);
			Movie detailedMovie = new Movie();
			for (int i=0; i< movieList.size(); i++) {
				if(movieList.get(i).getId() == id) {
					detailedMovie.setId(movieList.get(i).getId());
					detailedMovie.setTitle(movieList.get(i).getTitle());
					detailedMovie.setOverview(movieList.get(i).getOverview());
					detailedMovie.setPopularity(movieList.get(i).getPopularity());
					detailedMovie.setOriginalTitle(movieList.get(i).getOriginalTitle());
					detailedMovie.setOriginalLanguage(movieList.get(i).getOriginalLanguage());
					detailedMovie.setVoteCount(movieList.get(i).getVoteCount());
					detailedMovie.setVoteAverage(movieList.get(i).getVoteAverage());
					//TODO more details
					System.out.println("This is detailedMovie for id " + id + ": " + detailedMovie);
					//movieDetails.add(detailedMovie); //TODO h�r?
				}
			}
			// TODO Get further details from TMDb
			TMDbClient tc = new TMDbClient();
			String jsonString = tc.queryTMDbForMovieDetails(idString);
			System.out.println("This is the jsonString cretated when quering for movie details " + jsonString);
			JSONObject jsonObject = processJsonStringToJsonObject(jsonString);
			try {
				int budget = jsonObject.getInt("budget");
				String status = jsonObject.getString("status");
				String tagline = jsonObject.getString("tagline");
			
			// TODO l�gg till further details fr�n DMDb till movieDetailsArray
				detailedMovie.setBudget(budget);
				detailedMovie.setStatus(status);
				detailedMovie.setTagline(tagline);
				
			movieDetails.add(detailedMovie); 
			} catch (JSONException e) {
				System.err.println("Oops! A JSONException occurred in createMovieDetailsArrayFromJsonString: " + e.getMessage());
			}
			return movieDetails;
		}
		
		//Person related methods
		
		/**
		 * Creates the person array from json string brought from TMDb.
		 *
		 * @param name the query name
		 * @return the array list
		 */
		public ArrayList<Person> createPersonArrayFromJsonString(String name) {
			TMDbClient tc = new TMDbClient();
			String jsonString = tc.queryTMDbForPeople(name);
			//JSONObject jsonObject = processJsonStringToJsonObject(jsonString);
			persons = createPersonsFromJsonString(jsonString);
		//	System.out.println("createPersonArrayFromJsonString prints persons: " + persons);
			return persons ;
		}
	
		
		public ArrayList<Person> createPersonsFromJsonString(String s) {
			JSONObject jsonObject = processJsonStringToJsonObject(s);
			String arrayName = "results";
			JSONArray resultsArray = processJsonObjectToJsonArray(jsonObject, arrayName);
			ArrayList<Person> persons = processJSONArrayAndCreatePerson(resultsArray);
			return persons;	
			
		}
		
		private ArrayList<Person> processJSONArrayAndCreatePerson(JSONArray jsonArray) { 
			try {
				//Loop through the array and read all properties for the Person object to be created. Finally add it to the array list.
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					int id = jsonObject.getInt("id");
					boolean adult = jsonObject.getBoolean("adult");
					String name = jsonObject.getString("name");
					Double popularity = jsonObject.getDouble("popularity");
					
					JSONArray knownFor = jsonObject.getJSONArray("known_for");
					//ArrayList<Movie> knownForMovies = processJSONArrayAndCreateMovie(knownFor);
					ArrayList<Movie> moviesForPerson = new ArrayList<Movie>();
					for (int j = 0; j < knownFor.length(); j++) {
						JSONObject movieJsonObject = knownFor.getJSONObject(j);
						int movieId = movieJsonObject.getInt("id");
						String mediaType = movieJsonObject.getString("media_type");
						if (mediaType.equals("movie")) {
							String title = movieJsonObject.getString("title");
							String overview = movieJsonObject.getString("overview"); 
							String releaseDate = movieJsonObject.optString("release_date", null);
							Double moviePopularity = movieJsonObject.getDouble("popularity");
							//create Movie object
							Movie movie = new Movie(movieId, title, overview, releaseDate, moviePopularity);//TODO temp replaced by below
							//Movie movie = new Movie(movieId, title);
							//print movie
							//System.out.println(movie); // Used during development only
							//Add movie to movie array list
							//movies.add(movie); //TEMP TEST
							moviesForPerson.add(movie);
						}
	
					}
					ArrayList<Movie> knownForMovies = moviesForPerson;
					//System.out.println("These are known for movies: " + movies);
					System.out.println("These are known for movies: " + moviesForPerson);
					//create Person object
					//Person person = new Person(id, adult, name, popularity);
					Person person = new Person(id, adult, name, popularity, knownForMovies);
					//print person
				//	System.out.println(person); // Used during development only
					//Add person to person array list
					persons.add(person); 
				
				}
				System.out.println("These are in personsArray: " + persons);
			} catch (JSONException e) {
				System.err.println("Oops! A JSONException occurred: " + e.getMessage());
			  }
			return persons;
			
		}

		// Method to create selected person
		public Person createSelectedPersonFromJsonString(int idIn, ArrayList<Movie> knownForMovies) {
			System.out.println("id received by createSelectedPersonFromJsonString is " + idIn);
			System.out.println("KnownForMovies sent to createSelectedPersonFromJsonString were: " + knownForMovies);
			TMDbClient tc = new TMDbClient();
			String jsonString = tc.queryTMDbForPersonDetails(idIn, knownForMovies);
			JSONObject jsonObject = processJsonStringToJsonObject(jsonString);
			//Person person = null;
			Person person = new Person();
			try {
				int id = jsonObject.getInt("id");
				boolean adult = jsonObject.getBoolean("adult");
				String name = jsonObject.getString("name");
				Double popularity = jsonObject.getDouble("popularity");
				String birthday = jsonObject.optString("birthday", null);
				String deathday = jsonObject.optString("deathday", null);
				int genderIn = jsonObject.getInt("gender");
				String gender = null;
				switch (genderIn) {
				case 0: gender = "No information";	
					break;
				case 1: gender = "Female";
					break;
				case 2: gender = "Male";
					break;
				default:
					gender = "Invalid value";
					System.out.println("Invalid value");
					break;
				}	
				String biography = jsonObject.optString("biography", null);
				
				//create person object
				/*
				person = new Person(id, adult, name, popularity, 
						knownForMovies, gender, biography);
						*/
				
				person = new Person(id, adult, name, popularity, 
						knownForMovies,	birthday, deathday, gender, biography);
				
				//print person
				System.out.println(person); // Used during development only
				
				//Add person to person array list
				selectedPersons.add(person);
			} catch (JSONException e) {
				System.err.println("Oops! A JSONException occurred in method createSelectedPersonFromJsonString: " + e.getMessage());
			}
			return person; 	
			
		}

		
		
}
