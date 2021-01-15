package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.DatabaseManager;
import jdbcModels.BookJDBC;
import model.Book;

@Controller
public class ReturnBooks
{

	@RequestMapping(value = "/Boooks", method = RequestMethod.GET)
	public void returnBooks(HttpServletResponse response) throws IOException, SQLException
	{

		BookJDBC bookJDBC = DatabaseManager.getIstance().getDaoFactory().getBookJDBC();

		List<Book> books = bookJDBC.findAll();
		if (books.isEmpty())
		{
			response.getWriter().println("No books found!");
			return;
		}

		/* JSON */

	}

}
