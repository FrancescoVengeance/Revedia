package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Movie;

public interface MovieDao
{
	public Movie getMovie(String title) throws SQLException;
	public ArrayList<Movie> getMoviesByGenre(String genre) throws SQLException;
	public void insertMovie(Movie movie) throws SQLException;
	public void deleteMovie() throws SQLException;
	public void updateMovie() throws SQLException;
	public List<Movie> findAll() throws SQLException;
}
