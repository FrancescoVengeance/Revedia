package model;

import utilities.Pair;

public class BookReview extends Review
{
	private Pair<String, String> primaryKey; //first = user, second = book
	
	public void setPrimaryKey(Pair<String, String> primaryKey) { this.primaryKey = primaryKey; }
	public Pair<String, String> getPrimaryKey() { return primaryKey; }
}
