package daoInterfaces;

import java.sql.SQLException;

import model.User;
import utilities.Permissions;

public interface UserDao 
{
	public User getUser(String nickname) throws SQLException;
	public void updateUser(User user) throws SQLException;
	public void insertUser(User user, String password) throws SQLException;
	public void deleteUser(String nickname) throws SQLException;
	public boolean changePassword(String oldPassword, String newPassword, String nickname, String mail) throws SQLException;
	public void changePermissions(Permissions permissions, String nickname) throws SQLException;
	public boolean validateLogin(String password, String nickname, String mail) throws SQLException;
	
	//alter table libro, film, album, song aggiungere la data di inserimento
	
//	select * 
//	from song
//	where song.name similar to '%(^z|t)%';
}
