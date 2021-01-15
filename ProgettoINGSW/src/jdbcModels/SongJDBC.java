package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.SongDao;
import model.Song;
import utilities.Pair;

public class SongJDBC implements SongDao
{
	private DataSource dataSource;

	public SongJDBC()
	{
		super();
	}

	public SongJDBC(DataSource dataSource)
	{
		super();
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<Song> getSong(String name) throws SQLException
	{
		Connection connection = this.dataSource.getConnection();

		String query = "select album.albumid, song.name as songname, album.name as albumname, song.link, song.decription, song.users, song.length"
				+ " from song" + " inner join album" + " on song.album = album.albumid" + " where song.name = ?";

		ArrayList<Song> songs = new ArrayList<Song>();

		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, name);
		ResultSet result = statment.executeQuery();
		while (result.next())
		{
			songs.add(buildSong(result));
		}

		statment.close();
		result.close();
		connection.close();

		return songs;
	}

	@Override
	public Song findByPrimaryKey(String name, int albumKey) throws SQLException
	{
		Connection connection = this.dataSource.getConnection();

		String query = "select album.albumid, song.name as songname, album.name as albumname, song.link, song.decription, song.users, song.length"
				+ " from song" + " inner join album" + " on song.album = album.albumid"
				+ " where song.name = ? and song.album = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, name);
		statment.setInt(2, albumKey);
		ResultSet result = statment.executeQuery();

		connection.close();
		Song song = null;
		while (result.next())
		{
			song = buildSong(result);
		}

		if (song != null)
		{
			return song;
		} else
		{
			throw new RuntimeException("No song in this album with this name");
		}

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
		Connection connection = this.dataSource.getConnection();

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
		connection.close();
	}

	@Override
	public void updateSong(Song song) throws SQLException
	{
		Connection connection = this.dataSource.getConnection();

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
		connection.close();
	}

	@Override
	public void deleteSong(Song song) throws SQLException
	{
		Connection connection = this.dataSource.getConnection();

		String query = "delete from song where name = ? and album = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, song.getName());
		statment.setInt(2, song.getAlbum().key);
		statment.execute();
		statment.close();
		connection.close();
	}

	@Override
	public Song getSongByArtist(String name, String artist)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Song> findAll() throws SQLException
	{
		Connection connection = this.dataSource.getConnection();

		List<Song> songs = new ArrayList<>();

		Song song = null;

		String query = "select * from song";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery();

		while (result.next())
		{
			song = findByPrimaryKey(result.getString("name"), result.getInt("id"));
			songs.add(song);
		}

		result.close();
		connection.close();

		return songs;
	}
}
