package model;

import java.util.ArrayList;

import java.sql.*;


public class FilmDAO {

	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;

	//database connection details
	String user = "user";
	String password = "userpassword";
	String url = "jdbc:google:mysql://handy-tiger-298217:europe-west1:filminfo/filmdb";

	private void openConnection(){
		// loading driver for mysql
		try{
			Class.forName("com.mysql.jdbc.GoogleDriver");
		} catch(Exception e) {
			System.out.println(e);
		}

		try{
			// connecting to database
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();

		} catch(SQLException se) {
			System.out.println(se); 
		}	   
	}

	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
		Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thisFilm;		
	}

	//returns all records
	public ArrayList<Film> getAllFilms(){

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "SELECT * FROM films";

			System.out.println(selectSQL);

			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while(rs1.next()){
				oneFilm = getNextFilm(rs1);
				System.out.println(oneFilm.toString());
				allFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();

		} catch(SQLException se) { 
			System.out.println(se); 
		}

		return allFilms;
	}

	//returns all cases where the given string is in the title
	public ArrayList<Film> getFilmByTitle(String title){

		openConnection();
		ArrayList<Film> matchingFilms = new ArrayList<Film>();

		// Create select statement and execute it
		try{
			String selectSQL = "SELECT * FROM films WHERE title LIKE '%"+title+"%'";
			
			System.out.println(selectSQL);

			ResultSet rs1 = stmt.executeQuery(selectSQL);

			// Retrieve the results
			while(rs1.next()){
				oneFilm = getNextFilm(rs1);
				System.out.println(oneFilm.toString());
				matchingFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();

		} catch(SQLException se) {
			System.out.println(se); 
		}

		return matchingFilms;
	}


	//inserts a film into the films table - returns 1 if successful / 0 if failed
	public int insertFilm(Film f) {

		openConnection();

		//get variables from film object, no id as it the PK and set to auto-increment
		String title = f.getTitle();
		int year = f.getYear();
		String director = f.getDirector();
		String stars = f.getStars();
		String review = f.getReview();

		//create SQL statement and run it
		try {
			String insertSQL = "INSERT INTO films(title,year,director,stars,review) "
					+ "VALUES (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = conn.prepareStatement(insertSQL);
			preparedStmt.setString(1, title);
			preparedStmt.setInt(2, year);
			preparedStmt.setString(3, director);
			preparedStmt.setString(4, stars);
			preparedStmt.setString(5, review);

			System.out.println(insertSQL);
			
			preparedStmt.execute();

			stmt.close();
			closeConnection();

			return 1;

		} catch(SQLException se) {
			System.out.println(se);
			return 0;
		}
	}

	//deletes a film from the films table - returns 1 if successful / 0 if failed
	public int deleteFilm(int id) {
		openConnection();

		try {
			String deleteSQL = "DELETE FROM films WHERE id = ?";

			//using prepared statement to prevent sql injection
			PreparedStatement preparedStmt = conn.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, id);

			System.out.println(deleteSQL);
			
			preparedStmt.execute();

			stmt.close();
			closeConnection();

			return 1;

		} catch(SQLException se) {
			System.out.println(se);
			return 0;
		}
	}

	//updates a film from the films table - returns 1 if successful / 0 if failed
	public int updateFilm(Film f) {
		openConnection();

		int id = f.getId();
		String title = f.getTitle();
		int year = f.getYear();
		String director = f.getDirector();
		String stars = f.getStars();
		String review = f.getReview();

		try {
			String updateSQL = "UPDATE films "
							 + "SET title = ?, year = ?, director = ?, stars = ?, review = ? WHERE id = ?";

			//using prepared statement to prevent sql injection
			PreparedStatement preparedStmt = conn.prepareStatement(updateSQL);
			preparedStmt.setString(1, title);
			preparedStmt.setInt(2, year);
			preparedStmt.setString(3, director);
			preparedStmt.setString(4, stars);
			preparedStmt.setString(5, review);
			preparedStmt.setInt(6, id);
			
			System.out.println(updateSQL);

			preparedStmt.execute();

			stmt.close();
			closeConnection();

			return 1;

		} catch(SQLException se) {
			System.out.println(se);
			return 0;
		}
	}

}

























































