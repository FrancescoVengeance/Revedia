package model;

import utilities.Pair;

public class MovieReview extends Review
{
	private Pair<String, String> primaryKey;
	
	public void setPrimaryKey(Pair<String, String> primaryKey) { this.primaryKey = primaryKey; }
	public Pair<String, String> getPrimaryKey() { return primaryKey; }
}
