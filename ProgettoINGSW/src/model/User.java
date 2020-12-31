package model;

import utilities.Permissions;

public class User 
{
	private String nickname;
	private String firstName;
	private String lastName;
	private String mail;
	private Permissions permissions;
	
	public User() { super(); }
	
	public String getNickname() { return nickname; }
	public void setNickname(String nickname) { this.nickname = nickname; }
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getMail() { return mail; }
	public void setMail(String mail) { this.mail = mail; }

	public Permissions getPermissions() { return permissions; }
	public void setPermissions(Permissions permissions) { this.permissions = permissions; }
}


/*create table if not exists users
(
	nickname varchar(20) primary key,
	firstname varchar(15) not null,
	lastname varchar(15) not null,
	passwd character(16) not null,
	mail varchar(30) unique not null,
	permissions varchar(5) not null
);*/