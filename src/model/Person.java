package model;

import java.util.ArrayList;

public class Person {
	private int id;
	private boolean adult;
	//private Movie knownForMovie;
	//private TV knownForTV;
	private String name;
	private double popularity;
	
	private ArrayList<Movie> knownForMovies = new ArrayList<Movie>();
	//private ArrayList<TV> knownForTVs = new ArrayList<TV>();

	// Person details
	private String birthday;
	private String deathday;
	private String gender;
	private String biography;
	
	public Person() {}
	
	

	public Person(int idIn, boolean adultIn, String nameIn, Double popularityIn) {
		id = idIn;
		adult=adultIn;
		name = nameIn;
		popularity = popularityIn;
	}
	
	public Person(int idIn, boolean adultIn, String nameIn, Double popularityIn, ArrayList<Movie> knownForMoviesIn) {
		id = idIn;
		adult=adultIn;
		name = nameIn;
		popularity = popularityIn;
		knownForMovies = knownForMoviesIn;
	}
	
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
	 * @param id
	 * @param adult
	 * @param name
	 * @param popularity
	 * @param knownForMovies
	 * @param birthday
	 * @param deathday
	 * @param gender
	 * @param biography
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the adult
	 */
	public boolean isAdult() {
		return adult;
	}

	/**
	 * @param adult the adult to set
	 */
	public void setAdult(boolean adult) {
		this.adult = adult;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the popularity
	 */
	public double getPopularity() {
		return popularity;
	}

	/**
	 * @param popularity the popularity to set
	 */
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	/**
	 * @return the knownForMovies
	 */
	public ArrayList<Movie> getKnownForMovies() {
		return knownForMovies;
	}

	/**
	 * @param knownForMovies the knownForMovies to set
	 */
	public void setKnownForMovies(ArrayList<Movie> knownForMovies) {
		this.knownForMovies = knownForMovies;
	}
	
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
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the deathday
	 */
	public String getDeathday() {
		return deathday;
	}

	/**
	 * @param deathday the deathday to set
	 */
	public void setDeathday(String deathday) {
		this.deathday = deathday;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}

	/**
	 * @param biography the biography to set
	 */
	public void setBiography(String biography) {
		this.biography = biography;
	}
}

