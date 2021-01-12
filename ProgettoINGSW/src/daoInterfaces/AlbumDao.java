package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Album;
import model.AlbumReview;
import model.Movie;

public interface AlbumDao 
{
	public Album getAlbum(Integer id) throws SQLException;
	public ArrayList<Album> getAlbums(String name) throws SQLException;
	public void insertAlbum(Album album, String userNickname) throws SQLException;
	public ArrayList<AlbumReview> getReviews(Album album) throws SQLException;
	public ArrayList<Album> searchByKeyWords(String keyWords, int limit, int offset) throws SQLException;
}
