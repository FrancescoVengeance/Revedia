package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import daoInterfaces.SongDao;
import model.Song;
import utilities.Pair;

public class SongJDBC implements SongDao 
{
	private Connection connection;
	
	public SongJDBC(Connection connection) 
	{
		this.connection = connection;
	}
	
	@Override
	public ArrayList<Song> getSong(String name) throws SQLException 
	{
		String query = "select album.albumid, song.name as songname, album.name as albumname, song.link, song.decription, song.users, song.length"
				+ " from song"
				+ " inner join album"
				+ " on song.album = album.albumid";
		
		ArrayList<Song> songs = new ArrayList<Song>();
		
		PreparedStatement statment = connection.prepareStatement(query);
		ResultSet result = statment.executeQuery();
		while(result.next())
		{
			try 
			{
				songs.add(buildSong(result));
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		statment.close();
		result.close();
		return songs;
	}

	@Override
	public Song getSong(String name, String artist) 
	{
		String query = "";
		return null;
	}
	
	private static Song buildSong(ResultSet result) throws Exception
	{
		String songName = result.getString("songname");
		String albumName = result.getString("albumname");
		int albumid = result.getInt("albumid");
		String link = result.getString("link");
		String description = result.getString("decription");
		String user = result.getString("users");
		float length = result.getFloat("length");
		
		Song song = Song.class.getDeclaredConstructor().newInstance();
		song.setName(songName);
		song.setAlbum(new Pair<Integer, String>(albumid, albumName));
		song.setLink(link);
		song.setDescription(description);
		song.setUser(user);
		song.setLength(length);
		return song;
	}
}
