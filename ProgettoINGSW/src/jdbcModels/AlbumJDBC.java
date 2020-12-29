package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
}
