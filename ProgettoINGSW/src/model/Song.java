package model;

import utilities.Pair;

public class Song 
{
	private String name;
	private Pair<Integer, String> album;
	private float length;
	private String link;
	private String description;
	private String user;
	//aggiungere genere
	
	public Song() {}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Pair<Integer, String> getAlbum() { return album; }
	public void setAlbum(Pair<Integer, String> album) { this.album = album; }

	public float getLength() { return length; }
	public void setLength(float length) { this.length = length; }

	public String getLink() { return link; }
	public void setLink(String link) { this.link = link; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public String getUser() { return user; }
	public void setUser(String user) { this.user = user; }
}