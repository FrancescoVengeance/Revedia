package model;

public class Artist 
{
	private String name;
	private String biography;

	
	public Artist() {super();}
	
	public Artist(String name, String biography)
	{
		this.name = name;
		this.biography = biography;
	}
	
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getBiography() { return biography; }
	public void setBiography(String biography) { this.biography = biography; }
}
