package com.neosoft.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.neosoft.user.entity.User;
import com.neosoft.user.exception.InvalidId;
import com.neosoft.user.repo.UserRepository;
import com.neosoft.user.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public void registeruser() {

		User user = new User();
		user.setFirstName("Ganesh");
		user.setSurName("Pradhan");
		user.setPincode(686868L);
		user.setEmail("ga770977@gmail.com");

		Mockito.when(userRepository.save(user)).thenReturn(user);

		String actualMessage = userService.userRegistration(user);
		assertEquals("user registerd successfully!!", actualMessage);
	}

	@Test
	public void fetchuserById() {

		User user = new User();
		user.setCountry("India");
		user.setEmail("ga770977@gmail.com");
		user.setUserId(1);

		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

		User actualUser = userService.featchUserById(1);

		assertThat(actualUser).isEqualTo(user);

	}

	@Test

	public void removeUser() {
		User user = new User();
		user.setUserId(1);

		Mockito.doNothing().when(userRepository).deleteById(user.getUserId());

		String message = userService.removeUser(user.getUserId());

		assertThat(message).isEqualTo("user is deleted");

	}

	@Test(expected = InvalidId.class)
	public void removeUserById() {

		userService.removeUser(0);

	}

	@Test
	public void updateUser() {

		User user = new User();
		user.setFirstName("Ganesh");
		user.setSurName("Pradhan");
		user.setPincode(686868L);
		user.setEmail("ga770977@gmail.com");
		user.setAge(55);
		user.setUserId(6);

		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

		user.setAge(23);
		Mockito.when(userRepository.save(user)).thenReturn(user);

		String msg = userService.updateUser(user, user.getUserId());
		assertEquals("updated", msg);

	}

}
