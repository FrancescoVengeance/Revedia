package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Song;

public interface SongDao 
{
	public ArrayList<Song> getSong(String name) throws SQLException ;
	public Song getSong(String name, String artist);
}
