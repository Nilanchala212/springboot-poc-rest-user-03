package com.neosoft.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.user.controller.UserController;
import com.neosoft.user.entity.User;
import com.neosoft.user.exception.InvalidId;
import com.neosoft.user.response.UserResponse;
import com.neosoft.user.service.IUserService;
//this annotation bind the bridge between the springboot and junit.
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	// this class is used to test the controllers

	@MockBean
	private IUserService userService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void removUser() throws Exception {

		when(userService.removeUser(Mockito.anyInt())).thenReturn("user is deleted");

		RequestBuilder builder = MockMvcRequestBuilders.delete("/user/remove/hard/2")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		UserResponse userResponse = mapper.readValue(result.getResponse().getContentAsString(), UserResponse.class);
		assertEquals(userResponse.getStatus(), HttpStatus.OK);
		assertEquals(userResponse.getMessage(), "user is deleted");
	}

	@Test(expected = InvalidId.class)
	public void deleteuser() throws Throwable {

		String URI = "/user/remove/hard/2";

		when(userService.removeUser(Mockito.anyInt())).thenThrow(InvalidId.class);
		try {
			mockMvc.perform(MockMvcRequestBuilders.delete(URI)).andReturn();
		} catch (NestedServletException exception) {
			throw exception.getCause();
		}
	}

	@Test
	public void fetchUserById() throws Exception {

		String URI = "/user/fetchuserbyid/2";

		User user = new User();
		user.setAge(24);
		user.setCity("pune");

		when(userService.featchUserById(Mockito.anyInt())).thenReturn(user);

		RequestBuilder builder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		UserResponse userResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);

		HashMap<String, Object> user1 = (HashMap<String, Object>) userResponse.getBody();

		assertEquals("pune", user1.get("city"));
	}

	@Test
	public void registerUser() throws Exception {

		User user = new User();
		user.setAge(23);
		user.setCity("berhampur");
		user.setFirstName("raja");
		user.setSurName("swain");
		user.setPincode(765721L);

		ObjectMapper mapper = new ObjectMapper();

		String inputjosn = mapper.writeValueAsString(user);

		when(userService.userRegistration(user)).thenReturn("user registerd successfully!!");

		MvcResult mvcResult = mockMvc
				.perform(post("/user/registeruser").content(inputjosn).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();

		UserResponse dataResponse = mapper.readValue(result, UserResponse.class);

		assertEquals(HttpStatus.OK, dataResponse.getStatus());
		assertEquals("user registerd successfully!!", dataResponse.getMessage());
	}

	@Test
	public void updaeUser() throws Exception {

		User user = new User();
		user.setAge(23);
		user.setCity("odisha");
		user.setFirstName("nilanchala");
		user.setSurName("pradhan");
		user.setPincode(765764L);
		user.setUserId(5);
		user.setEmail("nil77888@gmail.com");
		ObjectMapper mapper = new ObjectMapper();

		String inputJson = mapper.writeValueAsString(user);
		when(userService.updateUser(user, 1)).thenReturn("updated");

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put("/user/update/5").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		   String content = mvcResult.getResponse().getContentAsString();
			UserResponse userResponse = mapper.readValue(content, UserResponse.class);

		//   assertEquals(HttpStatus.OK, dataResponse.getStatus());
		   assertEquals("updated", userResponse.getMessage());
	}


}
