package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import daoInterfaces.AlbumDao;
import model.Album;

public class AlbumJDBC implements AlbumDao 
{
	private Connection connection;
	
	public AlbumJDBC(Connection connection) 
	{
		this.connection = connection;
	}
	
	@Override
	public Album getAlbum(Integer id) throws SQLException 
	{
		String query = "select album.albumid, name, numberofsongs, releasedate, label, users, artist_album.artist "
				+ "from album "
				+ "inner join artist_album "
				+ "on album.albumid = artist_album.album "
				+ "where album.albumid = ?";
		
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setInt(1, id);
		ResultSet result = statment.executeQuery();
		result.next();
		
		Album album = buildAlbum(result);
		
		result.close();
		statment.close();
		return album;
	}

	private static Album buildAlbum(ResultSet result) throws SQLException
	{
		int albumId = result.getInt("albumid");
		String name = result.getString("name");
		short numberOfSongs = result.getShort("numberofsongs");
		Date releaseDate = result.getDate("releasedate");
		String label = result.getString("label");
		String user = result.getString("users");
		String artist = result.getString("artist");
		
		Album album = new Album();
		album.setId(albumId);
		album.setName(name);
		album.setNumberOfSongs(numberOfSongs);
		album.setReleaseDate(releaseDate);
		album.setLabel(label);
		album.setUser(user);
		album.setArtist(artist);
		
		return album;
	}

	@Override
	public ArrayList<Album> getAlbums(String name) throws SQLException 
	{
		String query = "select album.albumid, name, numberofsongs, releasedate, label, users, artist_album.artist "
				+ "from album "
				+ "inner join artist_album "
				+ "on album.albumid = artist_album.album "
				+ "where name = ?";
		
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, name);
		ResultSet result = statment.executeQuery();
		
		ArrayList<Album> albums = new ArrayList<Album>();
		while(result.next())
		{
			albums.add(buildAlbum(result));
		}
		
		result.close();
		statment.close();
		return albums;
	}

	@Override
	public void insertAlbum(Album album, String userNickname) throws SQLException 
	{
		String query = "insert into album(name,releaseDate, label, users) values (?,?,?,?)";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, album.getName());
		statment.setDate(2, album.getReleaseDate());
		statment.setString(3, album.getLabel());
		statment.setString(4, userNickname);
		statment.execute();
		statment.close();
	}
}
