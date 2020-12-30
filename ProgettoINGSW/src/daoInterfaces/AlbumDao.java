package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Album;

public interface AlbumDao 
{
	public Album getAlbum(Integer id) throws SQLException;
	public ArrayList<Album> getAlbums(String name) throws SQLException;
	public void insertAlbum(Album album, String userNickname) throws SQLException;
}
