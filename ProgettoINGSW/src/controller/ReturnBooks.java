package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.DatabaseManager;
import jdbcModels.BookJDBC;

@Controller
public class ReturnBooks
{

	@RequestMapping(value = "/Boooks", method = RequestMethod.GET)
	public void returnBooks(HttpServletResponse response) throws IOException
	{

		BookJDBC bookJDBC = DatabaseManager.getIstance().getDaoFactory().getBookJDBC();

		/*
		 * List<Book> books = bookJDBC.findAll(); if (books.isEmpty()) {
		 * response.getWriter().println("No books found!"); return; }
		 */

		/* JSON */

	}

}
