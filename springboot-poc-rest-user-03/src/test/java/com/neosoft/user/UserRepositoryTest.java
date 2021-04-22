package com.neosoft.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.neosoft.user.entity.User;
import com.neosoft.user.repo.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UserRepository userRepository;

	public User getUser() {

		User user = new User();
		user.setAge(24);
		user.setFirstName("nilanchalap");
		user.setState("odisha");
		user.setUserId(2);
		return user;
	}

	@Test
	public void registerUser() {
		User user = new User();
		user.setAge(23);
		user.setFirstName("nilanchalap");
		user.setState("odisha");

		Integer id = testEntityManager.persistAndGetId(user, Integer.class);

		assertNotNull(id);

	}

	@Test
	public void removeUser() {

		User user1 = new User();
		user1.setAge(2);
		user1.setFirstName("nilanchalap");
		user1.setState("odisha");

		User user2 = new User();
		user2.setAge(23);
		user2.setFirstName("nilanchalap");
		user2.setState("odisha");

		User saveUser = testEntityManager.persist(user1);
		testEntityManager.persist(user2);

		testEntityManager.remove(saveUser);

		Iterable<User> allUserFromDb = userRepository.findAll();

		List<User> userlist = new ArrayList<>();
		for (User users : allUserFromDb) {
			userlist.add(users);
		}

		assertThat(userlist.size()).isEqualTo(1);

	}

	@Test
	public void fetchUser() {

		User user = new User();
		user.setAge(23);
		user.setCity("odisha");
		user.setEmail("nil@gmail.com");
		user.setFirstName("nilanchalap");
		user.setSurName("pradhan");
		// user.setUserId(1);
		testEntityManager.persist(user);

		User user1 = userRepository.findById(user.getUserId()).get();
		User user2 = userRepository.findUserBy("nilanchalap", "pradhan");

		assertNotNull(user);

		assertEquals(user1.getAge(), user.getAge());
		assertEquals(user2.getAge(), user.getAge());
	}

	@Test
	public void updateUser() {

		User user = new User();
		user.setAge(23);
		user.setFirstName("pradhan");
		user.setState("mah");

		Integer id = testEntityManager.persistAndGetId(user, Integer.class);

		User user1 = userRepository.findById(id).get();

		user1.setAge(33);

		User user4 = userRepository.save(user1);

		assertEquals(user4.getAge(), user1.getAge());

	}

}
