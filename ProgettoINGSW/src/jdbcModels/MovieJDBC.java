package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import daoInterfaces.MovieDao;
import model.Movie;
import model.MovieReview;
import utilities.Pair;

public class MovieJDBC implements MovieDao
{
	
	private Connection connection;
	
	public MovieJDBC(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public Movie getMovie(String title) throws SQLException
	{
		String query = "select select title, length, description, link, users from movie where title = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, title);
		ResultSet result = statment.executeQuery();
		
		Movie movie = null;
		while(result.next())
			movie = buildMovie(result);
		
		result.close();
		statment.close();
		
		if(movie != null) 
			return movie;
		else
			throw new RuntimeException("Movie not found with this title");
	}

	@Override
	public ArrayList<Movie> getMoviesByGenre(String genre) throws SQLException
	{
		String query = "select distinct title, length, description, link, users "
				+ "from movie "
				+ "inner join genre_movie "
				+ "on movie.title = genre_movie.movie "
				+ "where genre_movie.genre = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, genre);
		ResultSet result = statment.executeQuery();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		while(result.next())
			movies.add(buildMovie(result));
		
		result.close();
		statment.close();
		
		if(movies.size() > 0) 
			return movies;
		else
			throw new RuntimeException("No movies found in this genre");
	}

	@Override
	public void insertMovie(Movie movie) throws SQLException
	{
		String query = "insert into movie(title, length, description, link, users) values(?,?,?,?,?)";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, movie.getTitle());
		statment.setFloat(2, movie.getLength());
		statment.setString(3, movie.getDescription());
		statment.setString(4, movie.getLink());
		statment.setString(5, movie.getUser());
		statment.execute();
		statment.close();
		
		query = "insert into genre_movie(genre, movie) values(?,?)";
		statment = connection.prepareStatement(query);
		for(String genre : movie.getGenres())
		{
			statment.setString(1, genre);
			statment.setString(2, movie.getTitle());
			statment.execute();
		}
		
		statment.close();
	}

	@Override
	public void deleteMovie() throws SQLException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMovie() throws SQLException
	{
		// TODO Auto-generated method stub

	}
	
	private Movie buildMovie(ResultSet result) throws SQLException
	{
		String title = result.getString("title");
		float length = result.getFloat("length");
		String description = result.getString("description");
		String link = result.getString("link");
		String user = result.getString("users");
		
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setLength(length);
		movie.setDescription(description);
		movie.setLink(link);
		movie.setUser(user);
		movie.setGenres(getGenres(title));
		
		return movie;
	}
	
	private ArrayList<String> getGenres(String title) throws SQLException
	{
		String query = "select genre from genre_movie where movie = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, title);
		ResultSet result = statment.executeQuery();
		
		ArrayList<String> genres = new ArrayList<String>();
		while(result.next())
			genres.add(result.getString("genre"));
		
		result.close();
		statment.close();
		
		return genres;
		
	}

	@Override
	public ArrayList<MovieReview> getReviews(Movie movie) throws SQLException
	{
		String query = "select users, movie, numberofstars, description "
				+ "from movie_review "
				+ "where movie = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, movie.getTitle());
		ResultSet result = statment.executeQuery();
		
		ArrayList<MovieReview> reviews = new ArrayList<MovieReview>();
		while(result.next())
		{
			reviews.add(buildReview(result));
		}
		
		result.close();
		statment.close();
		
		return reviews;
	}

	private MovieReview buildReview(ResultSet result) throws SQLException
	{
		String user = result.getString("users");
		String movie = result.getString("movie");
		short numberOfStars = result.getShort("numberofstars");
		String description = result.getString("description");
		
		MovieReview review = new MovieReview();
		review.setPrimaryKey(new Pair<String, String>(user, movie));
		review.setNumberOfStars(numberOfStars);
		review.setDescription(description);
		
		return review;
	}
}
