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


public class JsonHandler {
	
	public JsonHandler() {}
	
	ArrayList<Movie> movies = new ArrayList<Movie>();
	ArrayList<Movie> movieDetails = new ArrayList<Movie>();
	private ArrayList<Movie> popularMovies= new ArrayList<Movie>();

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
		//TODO testar att lägga till en film här också så att slutresultatet som visas verkligen är från fil
		//Movie movie =  new Movie(9,"9:e filmen är tillagd i Handler");
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
				String releaseDate = jsonObject.getString("release_date");
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
					//movieDetails.add(detailedMovie); //TODO här?
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
			
			// TODO lägg till further details från DMDb till movieDetailsArray
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
					//movieDetails.add(detailedMovie); //TODO här?
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
			
			// TODO lägg till further details från DMDb till movieDetailsArray
				detailedMovie.setBudget(budget);
				detailedMovie.setStatus(status);
				detailedMovie.setTagline(tagline);
				
			movieDetails.add(detailedMovie); 
			} catch (JSONException e) {
				System.err.println("Oops! A JSONException occurred in createMovieDetailsArrayFromJsonString: " + e.getMessage());
			}
			return movieDetails;
		}
	
	
}
