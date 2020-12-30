package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import daoInterfaces.AlbumDao;
import daoInterfaces.ArtistDao;
import daoInterfaces.SongDao;
import daoInterfaces.UserDao;
import jdbcModels.AlbumJDBC;
import jdbcModels.ArtistJDBC;
import jdbcModels.SongJDBC;
import jdbcModels.UserJDBC;

public class DatabaseManager 
{
	private static DatabaseManager instance = null;
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost/ProgettoINGSW";
	private final String user = "postgres";
	private final String password = "postgres";
	private Connection connection;
	
	private DatabaseManager()
	{
		try 
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public final static DatabaseManager getInstance()
	{
		if(instance == null)
			instance = new DatabaseManager();
		
		return instance;
	}
	
	public UserDao getUserDao()
	{
		return new UserJDBC(connection);
	}
	
	public SongDao getSongDao()
	{
		return new SongJDBC(connection);
	}
	
	public AlbumDao getAlbumDao()
	{
		return new AlbumJDBC(connection);
	}
	
	public ArtistDao getArtistDao()
	{
		return new ArtistJDBC(connection);
	}
}
