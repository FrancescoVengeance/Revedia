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
			songs.add(buildSong(result));
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
	
	private static Song buildSong(ResultSet result) throws SQLException
	{
		String songName = result.getString("songname");
		String albumName = result.getString("albumname");
		int albumid = result.getInt("albumid");
		String link = result.getString("link");
		String description = result.getString("decription");
		String user = result.getString("users");
		float length = result.getFloat("length");
		
		Song song = new Song();
		song.setName(songName);
		song.setAlbum(new Pair<Integer, String>(albumid, albumName));
		song.setLink(link);
		song.setDescription(description);
		song.setUser(user);
		song.setLength(length);
		return song;
	}

	@Override
	public void insertSong(Song song, String userNickname) throws SQLException 
	{
		String query = "insert into song(name, album, link, decription, users, length) values (?,?,?,?,?,?) ";
		
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, song.getName());
		statment.setInt(2, song.getAlbum().key);
		statment.setString(3, song.getLink());
		statment.setString(4, song.getDescription());
		statment.setString(5, userNickname);
		statment.setFloat(6, song.getLength());
		
		statment.execute();
		statment.close();
	}

	@Override
	public void updateSong(Song song) throws SQLException 
	{
		String query = "update song set album = ?, link = ?, decription = ?, length = ? where name = ? and album = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setInt(1, song.getAlbum().key);
		statment.setString(2, song.getLink());
		statment.setString(3, song.getDescription());
		statment.setFloat(4, song.getLength());
		statment.setString(5, song.getName());
		statment.setInt(6, song.getAlbum().key);
		
		statment.executeUpdate();
		statment.close();
	}

	@Override
	public void deleteSong(Song song) throws SQLException 
	{
		String query = "delete from song where name = ? and album = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, song.getName());
		statment.setInt(2, song.getAlbum().key);
		statment.execute();
		statment.close();
	}
}
