package model;

import java.util.ArrayList;

/**
 * The Class Movie for information about movies.
 */
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
	 *
	 * @param idIn the id in
	 * @param titleString the title string
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
	 * Gets the belongs to collection.
	 *
	 * @return the belongsToCollection
	 */
	public Object getBelongsToCollection() {
		return belongsToCollection;
	}

	/**
	 * Sets the belongs to collection.
	 *
	 * @param belongsToCollection the belongsToCollection to set
	 */
	public void setBelongsToCollection(Object belongsToCollection) {
		this.belongsToCollection = belongsToCollection;
	}

	/**
	 * Gets the original language.
	 *
	 * @return the originalLanguage
	 */
	public String getOriginalLanguage() {
		return originalLanguage;
	}

	/**
	 * Sets the original language.
	 *
	 * @param originalLanguage the originalLanguage to set
	 */
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	/**
	 * Gets the original title.
	 *
	 * @return the originalTitle
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * Sets the original title.
	 *
	 * @param originalTitle the originalTitle to set
	 */
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	/**
	 * Gets the release date.
	 *
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Sets the release date.
	 *
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Gets the vote count.
	 *
	 * @return the voteCount
	 */
	public int getVoteCount() {
		return voteCount;
	}

	/**
	 * Sets the vote count.
	 *
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * Gets the budget.
	 *
	 * @return the budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * Sets the budget.
	 *
	 * @param budget the budget to set
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the tagline.
	 *
	 * @return the tagline
	 */
	public String getTagline() {
		return tagline;
	}

	/**
	 * Sets the tagline.
	 *
	 * @param tagline the tagline to set
	 */
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	/**
	 * Gets the vote average.
	 *
	 * @return the voteAverage
	 */
	public double getVoteAverage() {
		return voteAverage;
	}

	/**
	 * Sets the vote average.
	 *
	 * @param voteAverage the voteAverage to set
	 */
	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}

	/**
	 * Gets the genres.
	 *
	 * @return the genres
	 */
	public ArrayList<Object> getGenres() {
		return genres;
	}

	/**
	 * Sets the genres.
	 *
	 * @param genres the genres to set
	 */
	public void setGenres(ArrayList<Object> genres) {
		this.genres = genres;
	}

	/**
	 * Gets the production companies.
	 *
	 * @return the productionCompanies
	 */
	public ArrayList<Object> getProductionCompanies() {
		return productionCompanies;
	}

	/**
	 * Sets the production companies.
	 *
	 * @param productionCompanies the productionCompanies to set
	 */
	public void setProductionCompanies(ArrayList<Object> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}
}
