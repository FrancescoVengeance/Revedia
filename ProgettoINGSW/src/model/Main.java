package model;

import java.sql.SQLException;

import jdbcModels.DAOFactory;
import jdbcModels.UserJDBC;

public class Main
{
	public static void main(String[] args) throws SQLException
	{
		User user = new User("G", "name", "sur", "mail");

		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UserJDBC userJDBC = factory.getUserJDBC();

		userJDBC.insertUser(user, "pwd");

	}
}
