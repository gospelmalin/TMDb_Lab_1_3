package model;

import java.util.ArrayList;

public class Movie {
	
	/** The id. */
	private int id;
	
	/** The adult. */
	private boolean adult; // added in preparation for further development
	
	/** The belongs to collection. */
	private Object belongsToCollection; // added in preparation for further development
	
	/** The original language. */
	private String originalLanguage; // added in preparation for further development
	
	/** The original title. */
	private String originalTitle; // added in preparation for further development
	
	/** The title. */
	private String title;
	
	/** The overview. */
	private String overview;
	
	/** The release date. */
	private String releaseDate; //TODO date?
	
	/** The popularity. */
	private double popularity;
	
	/** The vote count. */
	private int voteCount; // added in preparation for further development
	
	/** The budget. */
	private int budget;
	
	/** The status. */
	private String status;
	
	/** The tagline. */
	private String tagline;
	
	/** The vote average. */
	private double voteAverage; // added in preparation for further development
	
	/** The genres. */
	private ArrayList<Object> genres = new ArrayList<Object>(); //(object: id & name), added in preparation for further development
	
	/** The production companies. */
	private ArrayList<Object> productionCompanies = new ArrayList<Object>(); //(object id name originCountry), added in preparation for further development

	/**
	 * Instantiates a new movie.
	 *
	 * @param idIn the id in
	 * @param titleString the title string
	 * @param overviewString the overview string
	 * @param releaseDateString the release date string
	 * @param popularityString the popularity string
	 */
	public Movie(int idIn, String titleString, String overviewString, String releaseDateString, Double popularityString) {
		id = idIn;
		title = titleString;
		overview = overviewString;
		releaseDate = releaseDateString;
		popularity = popularityString;
	}
	
	/**
	 * Instantiates a new movie.
	 */
	public Movie() {}
	
	/**
	 * Instantiates a new movie.
	 */
	public Movie(int idIn, String titleString) {
		id = idIn;
		title = titleString;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Movie id: " + id + "\nTitle: " + title + "\nOverview: " + overview + "\nRelease date: " + releaseDate + "\nPopularity: " + popularity + "\n";
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the overview.
	 *
	 * @return the overview
	 */
	public String getOverview() {
		return overview;
	}

	/**
	 * Sets the overview.
	 *
	 * @param overview the overview to set
	 */
	public void setOverview(String overview) {
		this.overview = overview;
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
	 * @return the belongsToCollection
	 */
	public Object getBelongsToCollection() {
		return belongsToCollection;
	}

	/**
	 * @param belongsToCollection the belongsToCollection to set
	 */
	public void setBelongsToCollection(Object belongsToCollection) {
		this.belongsToCollection = belongsToCollection;
	}

	/**
	 * @return the originalLanguage
	 */
	public String getOriginalLanguage() {
		return originalLanguage;
	}

	/**
	 * @param originalLanguage the originalLanguage to set
	 */
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	/**
	 * @return the originalTitle
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * @param originalTitle the originalTitle to set
	 */
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the voteCount
	 */
	public int getVoteCount() {
		return voteCount;
	}

	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * @return the budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the tagline
	 */
	public String getTagline() {
		return tagline;
	}

	/**
	 * @param tagline the tagline to set
	 */
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	/**
	 * @return the voteAverage
	 */
	public double getVoteAverage() {
		return voteAverage;
	}

	/**
	 * @param voteAverage the voteAverage to set
	 */
	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}

	/**
	 * @return the genres
	 */
	public ArrayList<Object> getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(ArrayList<Object> genres) {
		this.genres = genres;
	}

	/**
	 * @return the productionCompanies
	 */
	public ArrayList<Object> getProductionCompanies() {
		return productionCompanies;
	}

	/**
	 * @param productionCompanies the productionCompanies to set
	 */
	public void setProductionCompanies(ArrayList<Object> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}
}
