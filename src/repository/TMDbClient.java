package repository;

import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import model.Movie;
import utility.PropertiesReader;


/**
 * The Class TMDbClient handles the communication with The Movie Database.
 */
public class TMDbClient {
	/** The client. */
	private Client client;
	
	/** The properties reader. */
	static PropertiesReader pr = new PropertiesReader();
	
	/** The api key. */
	private static String apiKey = pr.getApiKeyProperty();
	
	// URL (parts) as static strings for ease of use and more readable code.
	/** The start part of the URL used to Search for movies in TMDB. */
	private static String searchMoviesUrlStart = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=";
	
	/** The url to get popular movies. */
	private static String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&page=1";
	
	/** The start part of the search people url. */
	private static String searchPeopleUrlStart = "https://api.themoviedb.org/3/search/person?api_key=" + apiKey + "&query=";
	
	/** The start part of the movie details url. */
	private static String urlMovieDetailsStart = "https://api.themoviedb.org/3/movie/";
	
	/** The start part of the get person details url. */
	private static String urlPersonDetailsStart = "https://api.themoviedb.org/3/person/"; 
	
	/** The ending part of the search details url. */
	private static String searchDetailsUrlEnd = "?api_key=" + apiKey;
	
	/**
	 * Instantiates a new TMDB client.
	 */
	// constructor
	public TMDbClient() {
		client = ClientBuilder.newClient();
	}
	
	
	/**
	 * Query TMDb for movies.
	 *
	 * @param query the query
	 * @return the string
	 */
	public String queryTMDbForMovies(String query) {
		String URL = searchMoviesUrlStart + query;
		TMDbClient tc = new TMDbClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = tc.client
				.target(URL)
				.request(MediaType.APPLICATION_JSON)
				.get(string); // get the JSON representation
		//print the JSON representation
		//System.out.println("This is the JSON string recieved: " + s); // Kept for reference only
		return s;
	}
	
	/**
	 * Query TMDb for movie details.
	 *
	 * @param query the query (id as string)
	 * @return the string
	 */
	public String queryTMDbForMovieDetails(String query) {
		String URL = urlMovieDetailsStart + query + searchDetailsUrlEnd;
		TMDbClient tc = new TMDbClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = tc.client
				.target(URL)
				.request(MediaType.APPLICATION_JSON)
				.get(string); // get the JSON representation
		//print the JSON representation
		//System.out.println("This is the JSON string recieved: " + s); // Kept for reference only
		return s;
	}

	/**
	 * Query TMDb for popular movies.
	 *
	 * @return the string
	 */
	public String queryTMDbForPopularMovies() {
		String URL = popularMoviesUrl;
		TMDbClient tc = new TMDbClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = tc.client
				.target(URL)
				.request(MediaType.APPLICATION_JSON)
				.get(string); // get the JSON representation
		//print the JSON representation
		System.out.println("This is the JSON string recieved: " + s); // Kept for reference only
		return s;
	}
	
	/**
	 * Query TMDb for people search.
	 *
	 * @param query the query
	 * @return the string
	 */
	public String queryTMDbForPeople(String query) {
		String URL = searchPeopleUrlStart + query;
		TMDbClient tc = new TMDbClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = tc.client
				.target(URL)
				.request(MediaType.APPLICATION_JSON)
				.get(string); // get the JSON representation
		//print the JSON representation
		System.out.println("This is the JSON string recieved: " + s); // Kept for reference only
		return s;
	}
	
	/**
	 * Query TMDb for person details.
	 *
	 * @param id the id
	 * @param knownForMovies the known for movies array
	 * @return the string
	 */
	public String queryTMDbForPersonDetails(int id, ArrayList<Movie> knownForMovies) {
		String URL = urlPersonDetailsStart + id + searchDetailsUrlEnd;
		TMDbClient tc = new TMDbClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = tc.client
				.target(URL)
				.request(MediaType.APPLICATION_JSON)
				.get(string); // get the JSON representation
		//print the JSON representation
		//System.out.println("This is the JSON string recieved: " + s); // Kept for reference only
		return s;
	}
}

