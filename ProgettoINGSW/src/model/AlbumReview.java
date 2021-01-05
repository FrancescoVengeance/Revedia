package model;

import utilities.Pair;

public class AlbumReview extends Review
{
	private Pair<String, Integer> primaryKey;
	
	public void setPrimaryKey(Pair<String, Integer> primaryKey) { this.primaryKey = primaryKey; }
	public Pair<String, Integer> getPrimaryKey() { return primaryKey; }
}
