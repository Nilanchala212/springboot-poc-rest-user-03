package com.neosoft.user.serviceimpl;

import java.util.List;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.user.entity.User;
import com.neosoft.user.exception.InvalidId;
import com.neosoft.user.exception.InvalidUser;
import com.neosoft.user.repo.UserRepository;
import com.neosoft.user.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String userRegistration(User user) throws InvalidUser {
		if (user == null || user.getFirstName() == null || user.getPincode() == null || user.getSurName() == null) {
			throw new InvalidUser("User should not be null");
		} else {
			user.setDeleted(false);
			userRepository.save(user);
			return "User Register Successfully!!";
		}

	}

	@Override
	public void updateUser(int id, User user) {

	}

	@Override
	public User findUserBy(String firstName, String lastname) {
		User findUserBy = userRepository.findUserBy(firstName, lastname);
		System.out.println(findUserBy);
		return findUserBy;
	}

	@Override
	public User featchUser(String firstname) {
		return userRepository.featchUserByName(firstname);
	}

	@Override
	public List<User> findBy(String firstname, String surname, Long pincode) throws InvalidNameException {
		if (firstname == null && surname == null && pincode == null) {
			throw new InvalidNameException("Should not be null");

		} else {
			return userRepository.fetchBy(firstname, surname, pincode);
		}

	}

	@Override
	public String updateUser(User user, int userid) {
		User user1 = userRepository.findById(userid).get();
		user1.setCity(user.getCity());
		user1.setPincode(user.getPincode());
		user1.setAge(user.getAge());
		user1.setEmail(user.getEmail());
		user1.setFirstName(user.getFirstName());
		user1.setDesg(user.getDesg());
		user1.setState(user.getState());
		user1.setSurName(user.getSurName());
		user1.setCountry(user.getCountry());
		user1.setDob(user.getDob());
		user1.setDoj(user.getDoj());
		userRepository.save(user1);
		return "Updated";
	}

	@Override
	public String removeUser(int userId) {
		if (userId == 0) {
			throw new InvalidId("Id should not be null");
		} else {
			userRepository.deleteById(userId);
			return "User is Deleted";
		}
	}

	@Override
	public List<User> featchUserByDob() {
		return userRepository.featchUsersByDob();
	}

	@Override
	public List<User> featchUserByDoj() {
		return userRepository.featchUserByDoj();
	}

	@Override
	public int softDelete(int id) {
		if (id == 0) {
			throw new InvalidId("Id should not be null");
		} else {
			userRepository.softDelete(id);
			return 1;
		}
	}

	@Override
	public User featchUserById(int id) {
		if (id == 0) {
			throw new InvalidId("Id should not be null");
		} else {
			return userRepository.findById(id).get();
		}
	}

	@Override
	public List<User> featchAllUser() {
		return userRepository.findAll();
	}


}
