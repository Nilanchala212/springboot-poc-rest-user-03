package com.neosoft.user.service;

import java.util.List;

import javax.naming.InvalidNameException;

import com.neosoft.user.entity.User;
import com.neosoft.user.exception.InvalidUser;

public interface IUserService {
	
	public String userRegistration(User user) throws InvalidUser;

	public void updateUser(int id, User user);

	public User findUserBy(String firstName, String lastname);

	public User featchUser(String firstname);

	public List<User> findBy(String firstname, String surname, Long pincode) throws InvalidNameException;

	public String updateUser(User user, int userid);

	public String removeUser(int userId);

	public List<User> featchUserByDob();

	public List<User> featchUserByDoj();

	public int softDelete(int id);

	public User featchUserById(int id);

	public List<User> featchAllUser();

}
