package model;

import java.sql.SQLException;

import database.DatabaseManager;

public class Main 
{
	public static void main(String[] args) 
	{
		try 
		{
			User user = DatabaseManager.getInstance().getUserDao().getUser("francesco");
			System.out.println(user.getMail() + " " + user.getFirstName());
		} 
		catch (SQLException e) 
		{
			System.out.println("L'utente non esiste");
			e.printStackTrace();
		}
	}
}
