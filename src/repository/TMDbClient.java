package repository;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


public class TMDbClient {
	/** The client. */
	private Client client;
	
	/** The api key. */
	private static String apiKey = "3f1f5058bb142276d833d126145a1e7f"; // TODO "getFromFile";
	
	/** The start part of the URL used to Search for movies in TMDB. */
	private static String searchMoviesUrlStart = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=";
	
	private static String searchPeopleUrlStart = "https://api.themoviedb.org/3/search/person?api_key=" + apiKey + "&query=";
	private static String urlMovieDetailsStart = "https://api.themoviedb.org/3/movie/";
	private static String urlPersonDetailsStart = "https://api.themoviedb.org/3/person/"; 
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
	 * @param query the query
	 * @return the string
	 */
	public String queryTMDbForMovieDetails(String query) {
		String URL = urlMovieDetailsStart + query + searchDetailsUrlEnd ;
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

