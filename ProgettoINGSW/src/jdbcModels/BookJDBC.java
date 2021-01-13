package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daoInterfaces.BookDao;
import model.Book;
import model.BookReview;
import utilities.Pair;

public class BookJDBC implements BookDao
{
	private Connection connection;
	
	public BookJDBC(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public Book getBook(String title) throws SQLException
	{
		String query = "select title, numberOfPages, description, link, publishinghouse, artist, genre, rating "
				+ "from book "
				+ "inner join artist_book on artist_book.book = book.title "
				+ "inner join genre_book on genre_book.book = book.title "
				+ "where title = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, title);
		ResultSet result = statment.executeQuery();
		
		Book book = null;
		while(result.next())
			book = buildBook(result);
		
		result.close();
		statment.close();
		
		if(book != null) return book;
		else
			throw new RuntimeException("No books avaible with this title");
	}

	@Override
	public ArrayList<Book> getBooksByPublisher(String publisher) throws SQLException
	{
		String query = "select title, numberOfPages, description, link, publishinghouse, artist, genre, rating "
				+ "from book "
				+ "inner join artist_book on artist_book.book = book.title "
				+ "inner join genre_book on genre_book.book = book.title "
				+ "where publishinghouse = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, publisher);
		ResultSet result = statment.executeQuery();
		ArrayList<Book> books = new ArrayList<Book>();
		
		while(result.next())
			books.add(buildBook(result));
		
		result.close();
		statment.close();
		
		if(books.size() > 0) return books;
		else
			throw new RuntimeException("No books avaible with this publisher");
	}

	@Override
	public ArrayList<Book> getBooksByArtist(String artist) throws SQLException
	{
		String query = "select title, numberOfPages, description, link, publishinghouse, artist, genre, rating "
				+ "from book "
				+ "inner join artist_book on artist_book.book = book.title "
				+ "inner join genre_book on genre_book.book = book.title "
				+ "where artist = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, artist);
		ResultSet result = statment.executeQuery();
		ArrayList<Book> books = new ArrayList<Book>();
		
		while(result.next())
			books.add(buildBook(result));
		
		result.close();
		statment.close();
		
		if(books.size() > 0) return books;
		else
			throw new RuntimeException("No books avaible with this artist");
	}

	@Override
	public void updateBook(Book book) throws SQLException
	{
		String query = "update book set numberofpages = ?, description = ?, link = ?, publishinghouse = ? where title = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setShort(1, book.getNumberOfPages());
		statment.setString(2, book.getDescription());
		statment.setString(3, book.getLink());
		statment.setString(4, book.getPublishingHouse());
		statment.setString(5, book.getTitle());
		statment.executeUpdate();
		statment.close();
	}

	@Override
	public void insertBook(Book book) throws SQLException
	{
		String query = "insert into book(title, numberofpages,description,link,publishingHouse,users) values(?,?,?,?,?)";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, book.getTitle());
		statment.setShort(2, book.getNumberOfPages());
		statment.setString(3, book.getDescription());
		statment.setString(4, book.getLink());
		statment.setString(5, book.getPublishingHouse());
		statment.setString(6, book.getUser());
		statment.execute();
		statment.close();
		
		query = "insert into artist_book(artist, book) values(?,?)";
		statment = connection.prepareStatement(query);
		for(String artist : book.getAutors())
		{
			statment.setString(1, artist);
			statment.setString(2, book.getTitle());
			statment.execute();
		}
		statment.close();
		
		query = "insert into genre_book(genre, book) values (?,?)";
		statment = connection.prepareStatement(query);
		for(String genre : book.getGenres())
		{
			statment.setString(1, genre);
			statment.setString(2, book.getTitle());
			statment.execute();
		}
		statment.close();
	}

	@Override
	public void deleteBook(Book book) throws SQLException
	{
		// TODO Auto-generated method stub

	}
	
	private static Book buildBook(ResultSet result) throws SQLException
	{
		String title = result.getString("title");
		short numOfPages = result.getShort("numberofpages");
		String description = result.getString("description");
		String link = result.getString("link");
		String publishingHouse = result.getString("publishinghouse");
		String artist = result.getString("artist");
		String genre = result.getString("genre");
		float rating = result.getFloat("rating");
		
		Book book = new Book();
		book.setTitle(title);
		book.setNumberOfPages(numOfPages);
		book.setDescription(description);
		book.setLink(link);
		book.setPublishingHouse(publishingHouse);
		book.getAutors().add(artist);
		book.getGenres().add(genre);
		book.setRating(rating);
		
		return book;
	}

	@Override
	public ArrayList<BookReview> getReviews(Book book) throws SQLException
	{
		String query = "select users, book, numberofstars, description "
				+ "from book_review "
				+ "where book = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, book.getTitle());
		ResultSet result = statment.executeQuery();
		
		ArrayList<BookReview> reviews = new ArrayList<BookReview>();
		while(result.next())
		{
			reviews.add(buildReview(result));
		}
		
		statment.close();
		result.close();
		
		return reviews;
	}

	private BookReview buildReview(ResultSet result) throws SQLException
	{
		String user = result.getString("users");
		String book = result.getString("book");
		short numberOfStars = result.getShort("numberofstars");
		String description = result.getString("description");
		
		BookReview review = new BookReview();
		review.setPrimaryKey(new Pair<String, String>(user,book));
		review.setDescription(description);
		review.setNumberOfStars(numberOfStars);
		
		return review;
	}

	@Override
	public ArrayList<Book> searchByKeyWords(String keyWords, int limit, int offset) throws SQLException
	{
		String query = "select title, numberOfPages, description, link, publishinghouse, artist, genre, rating "
				+ "from book "
				+ "inner join artist_book on artist_book.book = book.title "
				+ "inner join genre_book on genre_book.book = book.title "
				+ "where title similar to ? "
				+ "limit ? offset ?";
		
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, keyWords);
		statment.setInt(2, limit);
		statment.setInt(3, offset);
	
		ResultSet result = statment.executeQuery();
		
		ArrayList<Book> books = new ArrayList<Book>();
		while(result.next())
			books.add(buildBook(result));
		
		result.close();
		statment.close();
		
		return books;
	}

	@Override
	public void addReview(BookReview review) throws SQLException
	{
		String query = "insert into book_review(users,book,numberofstars,description) values(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, review.getPrimaryKey().key);
		statement.setString(2, review.getPrimaryKey().value);
		statement.setShort(3, review.getNumberOfStars());
		statement.setString(4, review.getDescription());
		statement.execute();
		statement.close();
	}

	@Override
	public void deleteReview(String nickname, String title) throws SQLException
	{
		String query = "delete from book_review where users = ? and book = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, nickname);
		statment.setString(2, title);
		statment.execute();
		statment.close();
	}

	@Override
	public void updateReview(BookReview review) throws SQLException
	{
		String query = "update book_review set numberofstars = ?, description = ? "
					 + "where users = ? and book = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setShort(1, review.getNumberOfStars());
		statment.setString(2, review.getDescription());
		statment.setString(3, review.getPrimaryKey().key);
		statment.setString(4, review.getPrimaryKey().value);
		statment.executeUpdate();
		statment.close();
	}
}
