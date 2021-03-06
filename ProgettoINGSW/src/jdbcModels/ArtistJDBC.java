package jdbcModels;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import daoInterfaces.ArtistDao;
import model.Artist;

public class ArtistJDBC implements ArtistDao 
{
	private Connection connection;
	
	public ArtistJDBC(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public Artist findByPrimaryKey(String name) throws SQLException
	{
		String query = "select name, biography, postdate from artist where name = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, name);
		ResultSet result = statment.executeQuery();
		
		Artist artist = null;
		while(result.next())
		{
			artist = new Artist();
			String artistName = result.getString("name");
			String biography = result.getString("biography");
			Date postDate = result.getDate("postdate");
			
			artist.setName(artistName);
			artist.setBiography(biography);
			artist.setPostDate(postDate);
		}
		
		statment.close();
		result.close();
		if(artist != null)
			return artist;
		
		throw new RuntimeException("Empty result");
	}

	@Override
	public void insertArtist(Artist artist) throws SQLException 
	{
		String query = "insert into artist(name, biography) values(?,?)";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, artist.getName());
		statment.setString(2, artist.getBiography());
		statment.execute();
		statment.close();
	}
	
	@Override
	public void insertArtist(String name, String biography) throws SQLException 
	{
		String query = "insert into artist(name, biography) values(?,?)";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, name);
		statment.setString(2, biography);
		statment.execute();
		statment.close();
		
	}

	@Override
	public void deleteArtist(String name) throws SQLException 
	{
		String query = "delete from artist where name = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, name);
		statment.execute();
		statment.close();
	}

	

	@Override
	public void updateArtist(Artist artist) throws SQLException 
	{
		String query = "update artist set biography = ? where name = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, artist.getBiography());
		statment.setString(2, artist.getName());
		statment.executeUpdate();
		statment.close();
	}

}
