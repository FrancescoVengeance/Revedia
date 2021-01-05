package model;

import utilities.Pair;

public class SongReview extends Review
{
	private String user;
	private Pair<String, Integer> songKey;
	
	
	public String getUser() { return user; }
	public void setUser(String user) { this.user = user; }
	
	public Pair<String, Integer> getSongKey() { return songKey; }
	public void setSongKey(Pair<String, Integer> songKey) { this.songKey = songKey; }
}
