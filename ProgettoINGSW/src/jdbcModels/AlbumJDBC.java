package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import daoInterfaces.AlbumDao;
import database.DatabaseManager;
import model.Album;
import model.AlbumReview;
import model.Song;
import utilities.Pair;

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
		String query = "select album.albumid, name, numberofsongs, releasedate, label, users, artist_album.artist, album.rating "
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
		float rating = result.getFloat("rating");
		
		Album album = new Album();
		album.setId(albumId);
		album.setName(name);
		album.setNumberOfSongs(numberOfSongs);
		album.setReleaseDate(releaseDate);
		album.setLabel(label);
		album.setUser(user);
		album.setArtist(artist);
		album.setRating(rating);
		
		return album;
	}

	@Override
	public ArrayList<Album> getAlbums(String name) throws SQLException 
	{
		String query = "select album.albumid, name, numberofsongs, releasedate, label, users, artist_album.artist, album.rating "
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

	@Override
	public ArrayList<AlbumReview> getReviews(Album album) throws SQLException
	{
		String query = "select users, album, numberofStars, description "
				+ "from album_review "
				+ "and album = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setInt(1, album.getId());
		ResultSet result = statment.executeQuery();
		
		ArrayList<AlbumReview> reviews = new ArrayList<AlbumReview>();
		while(result.next())
		{
			reviews.add(buildReview(result));
		}
		
		result.close();
		statment.close();
		return reviews;
	}

	private AlbumReview buildReview(ResultSet result) throws SQLException
	{
		String user = result.getString("users");
		int album = result.getInt("album");
		short numberOfStars = result.getShort("numberOfStars");
		String description = result.getString("description");
		
		AlbumReview review = new AlbumReview();
		review.setPrimaryKey(new Pair<String, Integer>(user, album));
		review.setNumberOfStars(numberOfStars);
		review.setDescription(description);
				
		return review;
	}

	@Override
	public ArrayList<Album> searchByKeyWords(String keyWords, int limit, int offset) throws SQLException
	{
		String query = "select album.albumid, name, numberofsongs, releasedate, label, users, artist_album.artist, album.rating "
				+ "from album "
				+ "inner join artist_album "
				+ "on album.albumid = artist_album.album "
				+ "where name similar to ? "
				+ "limit ? offset ?";
	
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, keyWords);
		statment.setInt(2, limit);
		statment.setInt(3, offset);
	
		ResultSet result = statment.executeQuery();
		ArrayList<Album> albums = new ArrayList<Album>();
		
		while(result.next())
			albums.add(buildAlbum(result));
		
		result.close();
		statment.close();
		
		return albums;
	}

	@Override
	public void addReview(AlbumReview review) throws SQLException
	{
		String query = "insert into album_review(users, album, numberofstars, description) values(?,?,?,?)";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, review.getPrimaryKey().key);
		statment.setInt(2, review.getPrimaryKey().value);
		statment.setShort(3, review.getNumberOfStars());
		statment.setString(4, review.getDescription());
		
		statment.execute();
		statment.close();
	}

	@Override
	public void deleteReview(String nickname, int albumId) throws SQLException
	{
		String query = "delete from album_review where users = ? and album = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, nickname);
		statment.setInt(2, albumId);
		statment.execute();
		statment.close();
	}

	@Override
	public void updateReview(AlbumReview review) throws SQLException
	{
		String query = "update album_review set numberofStars = ?, description = ? "
					 + "where users = ? and album = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setShort(1, review.getNumberOfStars());
		statement.setString(2, review.getDescription());
		statement.setString(3, review.getPrimaryKey().key);
		statement.setInt(4, review.getPrimaryKey().value);
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public void updateAlbum(Album album) throws SQLException
	{
		String query = "update album set name  = ?, releasedate = ?, label = ? where albumid = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, album.getName());
		statement.setDate(2, album.getReleaseDate());
		statement.setString(3, album.getLabel());
		statement.setInt(4, album.getId());
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public void deleteAlbum(int id) throws SQLException
	{
		String query = "delete from album where albumid = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.execute();
		statement.close();
	}

	@Override
	public ArrayList<Song> getSongs(int id) throws SQLException
	{
		String query = "select album.albumid, song.name as songname, album.name as albumname,"
				+ " song.length, song.rating"
				+ " from song"
				+ " inner join album"
				+ " on song.album = album.albumid"
				+ " where albumid = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		ArrayList<Song> songs = new ArrayList<Song>();
		
		while(result.next())
		{
			String name = result.getString("songnname");
			int album = result.getInt("albumid");
			float length = result.getFloat("length");
			float rating = result.getFloat("rating");
			String albumname = result.getString("albumname");
			
			Song song = new Song();
			song.setName(name);
			song.setLength(length);
			song.setRating(rating);
			song.setAlbum(new Pair<Integer, String>(album, albumname));
			
			songs.add(song);
		}
		
		result.close();
		statement.close();
		return songs;
	}
}
