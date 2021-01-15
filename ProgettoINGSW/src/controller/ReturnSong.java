package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.DatabaseManager;
import jdbcModels.SongJDBC;
import model.Song;

@Controller
public class ReturnSong
{
	@RequestMapping(value = "/Songs", method = RequestMethod.GET)
	public void returnSongs(HttpServletResponse response) throws IOException, SQLException
	{

		SongJDBC songJDBC = DatabaseManager.getIstance().getDaoFactory().getSongJDBC();

		List<Song> songs = songJDBC.findAll();
		if (songs.isEmpty())
		{
			response.getWriter().println("No songs found!");
			return;
		}

		/* JSON */

	}

}
