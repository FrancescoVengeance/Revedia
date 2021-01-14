package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.DatabaseManager;
import jdbcModels.MovieJDBC;

@Controller
public class ReturnFilms
{
	@RequestMapping(value = "/Movies", method = RequestMethod.GET)
	public void returnSongs(HttpServletResponse response) throws IOException
	{

		MovieJDBC movieJDBC = DatabaseManager.getIstance().getDaoFactory().getMovieJDBC();

		/*
		 * List<Movie> movies = movieJDBC.findAll(); if (movies.isEmpty()) {
		 * response.getWriter().println("No films found!"); return; }
		 */

		/* JSON */

	}

}
