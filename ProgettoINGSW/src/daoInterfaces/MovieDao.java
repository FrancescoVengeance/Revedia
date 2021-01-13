package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.AlbumReview;
import model.BookReview;
import model.Movie;
import model.MovieReview;
import model.Song;

public interface MovieDao
{
	public Movie getMovie(String title) throws SQLException;
	public ArrayList<Movie> getMoviesByGenre(String genre) throws SQLException;
	
	public void insertMovie(Movie movie) throws SQLException;
	public void deleteMovie() throws SQLException;
	public void updateMovie() throws SQLException;
	
	public ArrayList<MovieReview> getReviews(Movie movie) throws SQLException;
	public void addReview(MovieReview review) throws SQLException;
	public void deleteReview(String nickname, String title) throws SQLException;
	public void updateReview(MovieReview review) throws SQLException;
	
	public ArrayList<Movie> searchByKeyWords(String keyWords, int limit, int offset) throws SQLException;
}
