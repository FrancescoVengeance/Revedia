package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Album;
import model.Song;

public interface SongDao 
{
	public ArrayList<Song> getSong(String name) throws SQLException;
	public Song getSongByArtist(String name, String artist);
	public void insertSong(Song song, String userNickname) throws SQLException;
	public void updateSong(Song song) throws SQLException;
	public void deleteSong(Song song) throws SQLException;
	public Song findByPrimaryKey(String name, int albumKey) throws SQLException;
	public List<Song> findAll() throws SQLException;
}
