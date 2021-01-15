package model;

import java.sql.Date;

public class Artist 
{
	private String name;
	private String biography;
	private Date postDate;

	
	public Artist() {super();}
	
	public Artist(String name, String biography, Date postDate)
	{
		this.name = name;
		this.biography = biography;
		this.postDate = postDate;
	}
	
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getBiography() { return biography; }
	public void setBiography(String biography) { this.biography = biography; }

	public Date getPostDate() { return postDate; }
	public void setPostDate(Date postDate) { this.postDate = postDate; }
}
