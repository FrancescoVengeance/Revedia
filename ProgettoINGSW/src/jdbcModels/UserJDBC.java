package jdbcModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import daoInterfaces.UserDao;
import model.User;

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
		try 
		{
			user = buildUser(result);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		statment.close();
		result.close();
		
		return user;
	}
	
	private static User buildUser(ResultSet result) throws Exception
	{
		String nick = result.getString("nickname");
		String firstName = result.getString("firstname");
		String lastName = result.getString("lastname");
		String mail = result.getString("mail");
		String permissions = result.getString("permissions");
		User user = User.class.getDeclaredConstructor().newInstance();
		user.setNickname(nick);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMail(mail);
		user.setPermissions(permissions);
		
		return user;
	}
}
