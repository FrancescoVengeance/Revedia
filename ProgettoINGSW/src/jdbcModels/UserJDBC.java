package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import daoInterfaces.UserDao;
import model.User;
import utilities.Permissions;

public class UserJDBC implements UserDao 
{
	private Connection connection;
	
	public UserJDBC(Connection connection) 
	{
		this.connection = connection;
	}
	
	
	@Override
	public User getUser(String nickname) throws SQLException 
	{
		String query = "select nickname, firstname, lastname, mail, permissions from users where nickname = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, nickname);
		
		ResultSet result = statment.executeQuery();
		result.next();
		
		User user = null;
		user = buildUser(result);
		
		statment.close();
		result.close();
		return user;
	}
	
	private static User buildUser(ResultSet result) throws SQLException
	{
		String nick = result.getString("nickname");
		String firstName = result.getString("firstname");
		String lastName = result.getString("lastname");
		String mail = result.getString("mail");
		Permissions permissions = Permissions.valueOf(result.getString("permissions"));
		User user = new User();
		user.setNickname(nick);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMail(mail);
		user.setPermissions(permissions);
		
		return user;
	}


	@Override
	public void updateUser(User user) throws SQLException 
	{
		String query = "update users set firstname = ?, lastname = ?, mail = ? where nickname = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, user.getFirstName());
		statment.setString(2, user.getLastName());
		statment.setString(3, user.getMail());
		statment.setString(4, user.getNickname());
		statment.executeUpdate();
		statment.close();
	}


	@Override
	public void insertUser(User user, String password) throws SQLException 
	{
		String query = "insert into users(nickname, firstname, lastname, passwd, mail, permissions)"
				+ "values (?,?,?,?,?,?)";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, user.getNickname());
		statment.setString(2, user.getFirstName());
		statment.setString(3, user.getLastName());
		statment.setString(4, password);
		statment.setString(5, user.getMail());
		statment.setString(6, Permissions.STANDARD.toString());
		statment.execute();
		statment.close();
	}


	@Override
	public void deleteUser(String nickname) throws SQLException 
	{
		String query = "delete from users where nickname = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, nickname);
		statement.execute();
		statement.close();
	}


	@Override
	public boolean changePassword(String oldPassword, String newPassword, String nickname, String mail) throws SQLException
	{
		if(validateLogin(oldPassword, nickname, mail))
		{
			String query = "update users set passwd = ? where nickname = ?";
			PreparedStatement statment = connection.prepareStatement(query);
			statment.setString(1, newPassword);
			statment.setString(2, nickname);
			statment.executeUpdate();
			statment.close();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void changePermissions(Permissions permissions, String nickname) throws SQLException 
	{
		String query = "update users set permissions = ? where nickname = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, permissions.toString());
		statment.setString(2, nickname);
		statment.executeUpdate();
		statment.close();
	}


	@Override
	public boolean validateLogin(String password, String nickname, String mail) throws SQLException 
	{
		String query = "select passwd from users where nickname = ? or mail = ?";
		PreparedStatement statment = connection.prepareStatement(query);
		statment.setString(1, nickname);
		statment.setString(2, mail);
		ResultSet result = statment.executeQuery();
		
		while(result.next())
		{
			if(result.getString("passwd").equals(password))
			{
				statment.close();
				result.close();
				return true;
			}
		}
		
		statment.close();
		result.close();
		return false;
	}
}
