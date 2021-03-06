package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Movie;
import model.MovieReview;

public interface MovieDao
{
	public Movie findByPrimaryKey(String title) throws SQLException;
	public ArrayList<Movie> getMoviesByGenre(String genre) throws SQLException;
	
	public void insertMovie(Movie movie) throws SQLException;
	public void deleteMovie(String title) throws SQLException;
	public void updateMovie(Movie movie) throws SQLException;
	
	public ArrayList<MovieReview> getReviews(String title) throws SQLException;
	public void addReview(MovieReview review) throws SQLException;
	public void deleteReview(String nickname, String title) throws SQLException;
	public void updateReview(MovieReview review) throws SQLException;
	
	public ArrayList<Movie> searchByKeyWords(String keyWords, int limit, int offset) throws SQLException;
}
