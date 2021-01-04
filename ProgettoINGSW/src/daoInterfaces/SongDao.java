package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Song;

public interface SongDao
{
	public ArrayList<Song> getSong(String name) throws SQLException;

	public Song getSong(String name, String artist);

	public Song findByPrimaryKey(String nome);

	public void insertSong(Song song, String userNickname) throws SQLException;

	public void updateSong(Song song) throws SQLException;

	public void deleteSong(Song song) throws SQLException;
}
