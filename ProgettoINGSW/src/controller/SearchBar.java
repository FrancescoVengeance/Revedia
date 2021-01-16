package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import database.DatabaseManager;
import jdbcModels.BookJDBC;
import jdbcModels.MovieJDBC;
import jdbcModels.SongJDBC;
import model.Book;
import model.Movie;
import model.Song;

@Controller
public class SearchBar
{

	// RICERCA CANZONE PER NOME

	@RequestMapping(value = "/tag della barra di ricerca", method = RequestMethod.GET)
	public ArrayList<Song> SongByName(ModelMap model, @RequestParam("/nome barra di ricerca") String name)
			throws SQLException
	{

		SongJDBC songJDBC = DatabaseManager.getIstance().getDaoFactory().getSongJDBC();
		ArrayList<Song> song = songJDBC.getSong(name);

		return song;

	}

	// RICERCA Film per Titolo

	@RequestMapping(value = "/tag della barra di ricerca", method = RequestMethod.GET)
	public Movie MovieByTitle(ModelMap model, @RequestParam("/nome barra di ricerca") String title) throws SQLException
	{

		MovieJDBC movieJDBC = DatabaseManager.getIstance().getDaoFactory().getMovieJDBC();
		Movie movie = movieJDBC.findByPrimaryKey(title);

		return movie;

	}

	// RICERCA Libro per titolo

	@RequestMapping(value = "/tag della barra di ricerca", method = RequestMethod.GET)
	public Book BookByTitle(ModelMap model, @RequestParam("/nome barra di ricerca") String title) throws SQLException
	{

		BookJDBC bookJDBC = DatabaseManager.getIstance().getDaoFactory().getBookJDBC();
		Book book = bookJDBC.getBook(title);

		return book;
	}

}