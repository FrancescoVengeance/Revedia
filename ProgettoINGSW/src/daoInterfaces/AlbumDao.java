package daoInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Album;
import model.AlbumReview;
import model.Song;

public interface AlbumDao 
{
	public Album getAlbum(Integer id) throws SQLException;
	public ArrayList<Album> getAlbums(String name) throws SQLException;
	public void insertAlbum(Album album, String userNickname) throws SQLException;
	public List<Album> findAll() throws SQLException;
	
	public void updateAlbum(Album album) throws SQLException;
	public void deleteAlbum(int id) throws SQLException;
	public ArrayList<Song> getSongs(int id) throws SQLException;
	
	public ArrayList<AlbumReview> getReviews(Album album) throws SQLException;
	public void addReview(AlbumReview review) throws SQLException;
	public void deleteReview(String nickname, int albumId) throws SQLException;
	public void updateReview(AlbumReview review) throws SQLException;
	
	public ArrayList<Album> searchByKeyWords(String keyWords, int limit, int offset) throws SQLException;
}
