package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import database.DatabaseManager;
import jdbcModels.SongJDBC;
import model.Song;

@Controller
public class SearchBar
{

	// RICERCA CANZONE PER NOME

	@RequestMapping(value = "/tag della barra di ricerca", method = RequestMethod.GET)
	public Song SearchByName(ModelMap model, @RequestParam("/nome barra di ricerca") String name)
	{
		SongJDBC songJDBC = DatabaseManager.getIstance().getDaoFactory().getSongJDBC();
		Song song = songJDBC.findByPrimaryKey(name);

		return song;

	}

}