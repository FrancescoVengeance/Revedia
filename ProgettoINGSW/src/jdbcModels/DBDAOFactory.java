package jdbcModels;

public class DBDAOFactory extends DAOFactory
{
	private static DataSource dataSource;

	static
	{
		try
		{
			Class.forName("org.postgresql.Driver").newInstance();

			dataSource = new DataSource("indirizzo", "id", "pwd"); // METTERE SERVER AWS

		} catch (Exception e)
		{
			System.err.println("PostgresDAOFactory.class: failed to load PostGress JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	public static void setDataSource(DataSource dataSource)
	{
		DBDAOFactory.dataSource = dataSource;
	}

	@Override
	public AlbumJDBC getAlbumJDBC()
	{
		return new AlbumJDBC(dataSource);
	}

	@Override
	public ArtistJDBC getArtistJDBC()
	{
		return new ArtistJDBC(dataSource);
	}

	@Override
	public BookJDBC getBookJDBC()
	{
		return new BookJDBC(dataSource);
	}

	@Override
	public SongJDBC getSongJDBC()
	{
		return new SongJDBC(dataSource);
	}

	@Override
	public UserJDBC getUserJDBC()
	{
		return new UserJDBC(dataSource);
	}

	@Override
	public DataSource getDataSource()
	{
		return dataSource;
	}

}
