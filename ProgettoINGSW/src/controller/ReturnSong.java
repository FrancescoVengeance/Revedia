package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.DatabaseManager;
import jdbcModels.SongJDBC;

@Controller
public class ReturnSong
{
	@RequestMapping(value = "/Songs", method = RequestMethod.GET)
	public void returnSongs(HttpServletResponse response) throws IOException
	{

		SongJDBC songJDBC = DatabaseManager.getIstance().getDaoFactory().getSongJDBC();

		/*
		 * List<Song> songs = songJDBC.findAll(); if (songs.isEmpty()) {
		 * response.getWriter().println("No songs found!"); return; }
		 */

		/* JSON */

	}

}
