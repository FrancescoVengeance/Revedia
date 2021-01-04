package jdbcModels;

public abstract class DAOFactory
{
	public static final int POSTGRESQL = 1;

	public static DAOFactory getDAOFactory(int whichFactory)
	{
		switch (whichFactory)
		{

			case POSTGRESQL:
				return new DBDAOFactory();
			default:
				return null;
		}
	}

	public abstract AlbumJDBC getAlbumJDBC();

	public abstract ArtistJDBC getArtistJDBC();

	public abstract BookJDBC getBookJDBC();

	public abstract SongJDBC getSongJDBC();

	public abstract UserJDBC getUserJDBC();

	public abstract DataSource getDataSource();
}
