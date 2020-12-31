package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daoInterfaces.BookDao;
import model.Book;

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
		String query = "select title, numberOfPages, description, link, publishinghouse, artist, genre "
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
		String query = "select title, numberOfPages, description, link, publishinghouse, artist, genre "
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
		String query = "select title, numberOfPages, description, link, publishinghouse, artist, genre "
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
		// TODO Auto-generated method stub

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
		
		Book book = new Book();
		book.setTitle(title);
		book.setNumberOfPages(numOfPages);
		book.setDescription(description);
		book.setLink(link);
		book.setPublishingHouse(publishingHouse);
		book.getAutors().add(artist);
		book.getGenres().add(genre);
		
		return book;
	}
}