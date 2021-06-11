package model;

public class Film {
	 
	int id;
	String title;
	int year;
	String director;
	String stars;
	String review;
	
	public Film(int id, String title, int year, String director, String stars, String review) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}
	   
	//Getters
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public int getYear() {
		return year;
	}
	public String getDirector() {
		return director;
	}
	public String getStars() {
		return stars;
	}
	public String getReview() {
		return review;
	}
	
	//Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	//puts all class variables into a string
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year
				+ ", director=" + director + ", stars=" + stars + ", review="
				+ review + "]";
	}   
}
