package model;

public abstract class Review
{
	private String description;
	private short numberOfStars;
	
	public short getNumberOfStars() { return numberOfStars; }
	public void setNumberOfStars(short numberOfStars) { this.numberOfStars = numberOfStars; }
	
	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return this.description; }
}
