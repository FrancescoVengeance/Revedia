package daoInterfaces;

import java.sql.SQLException;

import model.Artist;

public interface ArtistDao
{
	public Artist getArtist(String name) throws SQLException;
	public void insertArtist(Artist artist) throws SQLException;
	public void insertArtist(String name, String biography) throws SQLException;
	public void updateArtist(Artist artist) throws SQLException;
	public void deleteArtist(String name) throws SQLException;
}
