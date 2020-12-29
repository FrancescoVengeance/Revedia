package daoInterfaces;

import java.sql.SQLException;

import model.Album;

public interface AlbumDao 
{
	public Album getAlbum(Integer id) throws SQLException ;
}
