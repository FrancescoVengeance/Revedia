package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Album;
import model.AlbumReview;
import model.Book;
import model.BookReview;

public interface BookDao
{
	public Book getBook(String title) throws SQLException;
	public ArrayList<Book> getBooksByPublisher(String publisher) throws SQLException;
	public ArrayList<Book> getBooksByArtist(String artist) throws SQLException;
	
	public void updateBook(Book book) throws SQLException;
	public void insertBook(Book book) throws SQLException;
	public void deleteBook(Book book) throws SQLException;
	
	public ArrayList<BookReview> getReviews(Book book) throws SQLException;
	public void addReview(BookReview review) throws SQLException;
	public void deleteReview(String nickname, String title) throws SQLException;
	public void updateReview(BookReview review) throws SQLException;
	
	public ArrayList<Book> searchByKeyWords(String keyWords, int limit, int offset) throws SQLException;
}
