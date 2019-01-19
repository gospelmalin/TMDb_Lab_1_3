package model;

import java.util.ArrayList;

/**
 * The Class Person is used to define a person object.
 */
public class Person {
	
	/** The id. */
	private int id;
	
	/** The adult. */
	private boolean adult;
	//private Movie knownForMovie;
	/** The name. */
	//private TV knownForTV;
	private String name;
	
	/** The popularity. */
	private double popularity;
	
	/** The known for movies. */
	private ArrayList<Movie> knownForMovies = new ArrayList<Movie>();
	//private ArrayList<TV> knownForTVs = new ArrayList<TV>();

	/** The birthday. */
	// Person details
	private String birthday;
	
	/** The deathday. */
	private String deathday;
	
	/** The gender. */
	private String gender;
	
	/** The biography. */
	private String biography;
	
	/**
	 * Instantiates a new person.
	 */
	public Person() {}
	
	

	/**
	 * Instantiates a new person.
	 *
	 * @param idIn the id in
	 * @param adultIn the adult in
	 * @param nameIn the name in
	 * @param popularityIn the popularity in
	 */
	public Person(int idIn, boolean adultIn, String nameIn, Double popularityIn) {
		id = idIn;
		adult=adultIn;
		name = nameIn;
		popularity = popularityIn;
	}
	
	/**
	 * Instantiates a new person.
	 *
	 * @param idIn the id in
	 * @param adultIn the adult in
	 * @param nameIn the name in
	 * @param popularityIn the popularity in
	 * @param knownForMoviesIn the known for movies in
	 */
	public Person(int idIn, boolean adultIn, String nameIn, Double popularityIn, ArrayList<Movie> knownForMoviesIn) {
		id = idIn;
		adult=adultIn;
		name = nameIn;
		popularity = popularityIn;
		knownForMovies = knownForMoviesIn;
	}
	
	/**
	 * Instantiates a new person.
	 *
	 * @param id the id
	 * @param adult the adult
	 * @param name the name
	 * @param popularity the popularity
	 * @param knownForMovies the known for movies
	 * @param gender the gender
	 * @param biography the biography
	 */
	public Person(int id, boolean adult, String name, double popularity, ArrayList<Movie> knownForMovies,
			String gender, String biography) {
		this.id = id;
		this.adult = adult;
		this.name = name;
		this.popularity = popularity;
		this.knownForMovies = knownForMovies;
		this.gender = gender;
		this.biography = biography;
	}

	/**
	 * Instantiates a new person.
	 *
	 * @param id the id
	 * @param adult the adult
	 * @param name the name
	 * @param popularity the popularity
	 * @param knownForMovies the known for movies
	 * @param birthday the birthday
	 * @param deathday the deathday
	 * @param gender the gender
	 * @param biography the biography
	 */
	public Person(int id, boolean adult, String name, double popularity, ArrayList<Movie> knownForMovies,
			String birthday, String deathday, String gender, String biography) {
		this.id = id;
		this.adult = adult;
		this.name = name;
		this.popularity = popularity;
		this.knownForMovies = knownForMovies;
		this.birthday = birthday;
		this.deathday = deathday;
		this.gender = gender;
		this.biography = biography;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Checks if is adult.
	 *
	 * @return the adult
	 */
	public boolean isAdult() {
		return adult;
	}

	/**
	 * Sets the adult.
	 *
	 * @param adult the adult to set
	 */
	public void setAdult(boolean adult) {
		this.adult = adult;
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the popularity.
	 *
	 * @return the popularity
	 */
	public double getPopularity() {
		return popularity;
	}

	/**
	 * Sets the popularity.
	 *
	 * @param popularity the popularity to set
	 */
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	/**
	 * Gets the known for movies.
	 *
	 * @return the knownForMovies
	 */
	public ArrayList<Movie> getKnownForMovies() {
		return knownForMovies;
	}

	/**
	 * Sets the known for movies.
	 *
	 * @param knownForMovies the knownForMovies to set
	 */
	public void setKnownForMovies(ArrayList<Movie> knownForMovies) {
		this.knownForMovies = knownForMovies;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*
	public String toString() {
		return "Person id: " + id + "\nName: " + name + "\nPopularity: " 
	+ popularity + "\nAdult: " + adult + "\n";
	}
	*/
	public String toString() {
		return "Person id: " + id + "\nName: " + name + "\nPopularity: " 
	+ popularity + "\nAdult: " + adult + "\nKnownForMovies" + knownForMovies + "\n";
	}
	

	/**
	 * Gets the birthday.
	 *
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Sets the birthday.
	 *
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets the deathday.
	 *
	 * @return the deathday
	 */
	public String getDeathday() {
		return deathday;
	}

	/**
	 * Sets the deathday.
	 *
	 * @param deathday the deathday to set
	 */
	public void setDeathday(String deathday) {
		this.deathday = deathday;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the biography.
	 *
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}

	/**
	 * Sets the biography.
	 *
	 * @param biography the biography to set
	 */
	public void setBiography(String biography) {
		this.biography = biography;
	}
}

