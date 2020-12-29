package daoInterfaces;

import java.sql.SQLException;

import model.User;

public interface UserDao 
{
	public User getUser(String nickname) throws SQLException;
}
