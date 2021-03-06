package model;

import java.sql.Date;
import java.util.ArrayList;

public class Album 
{
	private int id = 0;
	private String name;
	private short numberOfSongs;
	private Date releaseDate;
	private String label;
	private String user;
	private String artist;
	private ArrayList<String> genres;
	private float rating;
	private Date postDate;
	

	public Album() { super(); }

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public short getNumberOfSongs() { return numberOfSongs; }
	public void setNumberOfSongs(short numberOfSongs) { this.numberOfSongs = numberOfSongs; }

	public Date getReleaseDate() { return releaseDate; }
	public void setReleaseDate(Date releaseDate2) { this.releaseDate = releaseDate2; }

	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }

	public String getUser() { return user; }
	public void setUser(String user) { this.user = user; }

	public String getArtist() { return artist; }
	public void setArtist(String artist) { this.artist = artist; }
	
	public ArrayList<String> getGenre() { return genres; }
	public void setGenre(ArrayList<String> genres) { this.genres = genres; }
	
	public float getRating() { return rating; }
	public void setRating(float rating) { this.rating = rating; }

	public Date getPostDate() { return postDate; }
	public void setPostDate(Date postDate) { this.postDate = postDate; }
}

/*create table if not exists album
(
	albumid serial primary key,
	name varchar(30) not null,
	numberOfSongs smallint not null,
	releaseDate date not null,
	label varchar(20) not null,
	users varchar(20) not null,
	rating real default 0,
	postdate date not null default current_date,
	foreign key(users) references users(nickname) on delete cascade
);*/